package com.example.smarttask.controller;

import com.example.smarttask.dto.ProjectDTO;
import com.example.smarttask.dto.response.ProjectResponseDTO;
import com.example.smarttask.entity.Project;
import com.example.smarttask.service.ProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/projects")
public class ProjectController {
    private final ProjectService projectService;

    @GetMapping("getAllProjects")
    public List<Project> getAllProjects(){
        return projectService.getAllProjects();
    }

    @PostMapping("saveProject")
    public ProjectResponseDTO createProject(@RequestBody ProjectDTO project){
        return projectService.createProject(project);
    }
}
