package com.example.smarttask.controller;

import com.example.smarttask.dto.TaskDTO;
import com.example.smarttask.dto.response.TaskResponseDTO;
import com.example.smarttask.service.TaskService;
import com.example.smarttask.service.TaskSimulationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/tasks")
public class TaskController {
    private final TaskService taskService;
    private final TaskSimulationService simulationService;

    @GetMapping("getAllTasks")
    public List<TaskResponseDTO> getAllTasks() {
        return taskService.getAllTasks();
    }

    @PostMapping("saveTask")
    public TaskResponseDTO createTask(@RequestBody TaskDTO task) {
        return taskService.createTask(task);
    }

    @PostMapping("/simulation")
    public String simulateTask(@RequestBody List<Long> taskIds) {
        simulationService.simulateTaskExecution(taskIds);
        return "Task simulation started for task Ids: " + taskIds;
    }
    @GetMapping("simulation/status")
    public Map<Long, String> getTaskSimulationStatus() {
        return simulationService.getTaskSimulationStatus();
    }
}
