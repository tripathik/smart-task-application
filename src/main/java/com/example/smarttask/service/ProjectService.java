package com.example.smarttask.service;

import com.example.smarttask.dto.ProjectDTO;
import com.example.smarttask.dto.response.ProjectResponseDTO;
import com.example.smarttask.entity.Project;
import com.example.smarttask.entity.User;
import com.example.smarttask.exception.ResourceNotFoundException;
import com.example.smarttask.repository.ProjectRepository;
import com.example.smarttask.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProjectService {
    private final ProjectRepository projectRepository;
    private final UserRepository userRepository;

    public ProjectResponseDTO createProject(ProjectDTO projectDTO){
        User manager = userRepository.findById(projectDTO.getManagerId())
                .orElseThrow(()->new ResourceNotFoundException("Manager not found."));

        Project project = Project.builder()
                .name(projectDTO.getName())
                .manager(manager)
                .build();

        Project savedProject = projectRepository.save(project);

        return ProjectResponseDTO.builder()
                .id(savedProject.getId())
                .name(savedProject.getName())
                .managerId(manager.getId())
                .managerUsername(manager.getUsername())
                .build();
    }
    public List<Project> getAllProjects(){
        return projectRepository.findAll();
    }
}
