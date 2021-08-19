package com.example.task.complaintservice.controller;

import com.example.task.complaintservice.Dto.ComplaintDto;
import com.example.task.complaintservice.service.ComplaintService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping
public class ComplaintController {
    @Autowired
    private ComplaintService complaintService;

    @GetMapping("/complaint/{userId}/{complaintId}")
    public ResponseEntity<?> getComplaint(@PathVariable(value = "userId") Long userId, @PathVariable(value = "complaintId") Long complaintId){
        return new ResponseEntity<>(complaintService.getComplaint(userId,complaintId), HttpStatus.OK);
    }

    @GetMapping("/complaints/{userId}")
    public ResponseEntity<?> getComplaints(@PathVariable Long userId){
        return new ResponseEntity<>(complaintService.getComplaints(userId),HttpStatus.OK);
    }

    @PostMapping("complaint")
    public ResponseEntity<?> createComplaint(@PathVariable Long userId, @RequestBody ComplaintDto complaintDto){
        return new ResponseEntity<>(complaintService.createComplaint(userId,complaintDto),HttpStatus.OK);
    }

}
