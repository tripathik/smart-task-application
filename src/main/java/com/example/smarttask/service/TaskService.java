package com.example.smarttask.service;

import com.example.smarttask.dto.TaskDTO;
import com.example.smarttask.dto.response.TaskResponseDTO;
import com.example.smarttask.entity.Project;
import com.example.smarttask.entity.Task;
import com.example.smarttask.entity.User;
import com.example.smarttask.exception.ResourceNotFoundException;
import com.example.smarttask.repository.ProjectRepository;
import com.example.smarttask.repository.TaskRepository;
import com.example.smarttask.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskService {
    private final TaskRepository taskRepository;
    private final ProjectRepository projectRepository;
    private final UserRepository userRepository;

    public List<TaskResponseDTO> getAllTasks() {
        List<Task> tasks = taskRepository.findAll();

        return tasks.stream().map(task -> TaskResponseDTO.builder()
                .id(task.getId())
                .title(task.getTitle())
                .status(task.getStatus())
                .projectId(task.getProject().getId())
                .projectName(task.getProject().getName())
                .assignedToUserId(task.getAssignedTo().getId())
                .assignedToUsername(task.getAssignedTo().getUsername())
                .build()
        ).toList();
    }
    public TaskResponseDTO createTask(TaskDTO taskDTO){
        Project project = projectRepository.findById(taskDTO.getProjectId())
                .orElseThrow(() -> new ResourceNotFoundException("Project not found"));

        User assignedTo = userRepository.findById(taskDTO.getAssignedToUserId())
                .orElseThrow(() -> new ResourceNotFoundException("Assigned User not found"));

        Task task = Task.builder()
                .title(taskDTO.getTitle())
                .status(taskDTO.getStatus())
                .assignedTo(assignedTo)
                .project(project)
                .build();
        Task savedTask = taskRepository.save(task);

        return TaskResponseDTO.builder()
                .id(savedTask.getId())
                .title(savedTask.getTitle())
                .status(savedTask.getStatus())
                .projectId(project.getId())
                .projectName(project.getName())
                .assignedToUserId(assignedTo.getId())
                .assignedToUsername(assignedTo.getUsername())
                .build();
    }
    public Task getTaskById(Long id){

        return taskRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No Task details found with Id: ".formatted(id)));
    }
}
