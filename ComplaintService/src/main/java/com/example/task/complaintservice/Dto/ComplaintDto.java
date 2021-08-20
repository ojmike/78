package com.example.task.complaintservice.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ComplaintDto {
    private String title;
    private String message;
    private String status;
}
