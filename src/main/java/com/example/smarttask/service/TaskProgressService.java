package com.example.smarttask.service;

import com.example.smarttask.util.constant.enums.TaskStatus;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
@Service
public class TaskProgressService {
    private final Map<Long, TaskStatus> taskStatusMap = new ConcurrentHashMap<>();

    public void updateStatus(Long taskId, TaskStatus status){
        taskStatusMap.put(taskId, status);
    }

    public TaskStatus getStatus(Long taskId){
        return taskStatusMap.getOrDefault(taskId, TaskStatus.NOT_STARTED);
    }
}
