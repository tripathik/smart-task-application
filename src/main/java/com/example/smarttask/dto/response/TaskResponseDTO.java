package com.example.smarttask.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TaskResponseDTO {
    private Long id;
    private String title;
    private String status;
    private Long projectId;
    private String projectName;
    private Long assignedToUserId;
    private String assignedToUsername;
}
