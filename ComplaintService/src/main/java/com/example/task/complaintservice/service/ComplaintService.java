package com.example.task.complaintservice.service;

import com.example.task.complaintservice.Dto.ComplaintDto;
import com.example.task.complaintservice.model.Complaint;

import java.util.List;

public interface ComplaintService {
    Complaint getComplaint(Long userId, Long complaintId);
    List<Complaint> getComplaints(Long userId);
    Complaint createComplaint(Long userId, ComplaintDto complaintDto);
    Complaint updateComplaint(Long userId, Long commentId, ComplaintDto complaintDto);
    Complaint setComplaint(Complaint complaint, ComplaintDto complaintDto);
}
