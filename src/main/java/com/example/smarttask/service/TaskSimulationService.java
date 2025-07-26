package com.example.smarttask.service;

import com.example.smarttask.entity.Task;
import com.example.smarttask.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Service
@Slf4j
@RequiredArgsConstructor
public class TaskSimulationService {
    private final TaskService taskService;
    private final TaskRepository taskRepository;
    private final Map<Long, String> taskStatusMap = new ConcurrentHashMap<>();

    @Value("${thread.threadPoolSize}")
    private int threadPoolSize;

    public void simulateTaskExecution(List<Long> taskIds) {
        ExecutorService executorService = Executors.newFixedThreadPool(threadPoolSize);

        List<CompletableFuture<Void>> futures = taskIds.stream()
                .map(taskId -> CompletableFuture.runAsync(()-> processTask(taskId), executorService))
                .toList();

        CompletableFuture.allOf(futures.toArray(new CompletableFuture[0]))
                .thenRun(()-> log.info("All tasks processed."))
                .exceptionally(ex-> {
                    log.error("Error occurred while processing the tasks");
                    return null;
                });

        executorService.shutdown();
    }

    private void processTask(Long id) {
        taskStatusMap.put(id, "IN-PROGRESS");
        try {
            Task task = taskService.getTaskById(id);
            log.info("Processing task ID: {}", id);
            Thread.sleep(2000);

            task.setStatus("COMPLETED");
            taskRepository.save(task);
            taskStatusMap.put(id, "COMPLETED");

            log.info("Completed processing for Task ID: {}", id);
        }catch (InterruptedException ex){
            Thread.currentThread().interrupt();
            taskStatusMap.put(id, "FAILED");
            log.error("Task interrupted: {}", id, ex);
        }
        catch (Exception exception){
            Thread.currentThread().interrupt();
            taskStatusMap.put(id, "FAILED");
            log.error("Task processing interrupted for Task ID: {}", id);
        }
    }

    public Map<Long, String> getTaskSimulationStatus() {
        return new ConcurrentHashMap<>(taskStatusMap);
    }
}
