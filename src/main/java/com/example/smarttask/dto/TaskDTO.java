package com.example.smarttask.dto;

import lombok.Data;

@Data
public class TaskDTO {
    private String title;
    private String status;
    private Long projectId;
    private Long assignedToUserId;
}
