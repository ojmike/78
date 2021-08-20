package com.example.task.complaintservice.service;

import com.example.task.complaintservice.Dto.ComplaintDto;
import com.example.task.complaintservice.model.Complaint;
import org.springframework.http.server.reactive.ServerHttpRequest;

import javax.servlet.ServletException;
import java.io.IOException;
import java.util.List;

public interface ComplaintService {
    Complaint getComplaint(Long userId, Long complaintId) throws ServletException, IOException;
    List<Complaint> getComplaints(Long userId) throws ServletException, IOException;
    Complaint createComplaint(Long userId,ComplaintDto complaintDto);
    Complaint updateComplaint(Long userId,Long commentId, ComplaintDto complaintDto);
    Complaint setComplaint(Long userId,Complaint complaint, ComplaintDto complaintDto);
}
