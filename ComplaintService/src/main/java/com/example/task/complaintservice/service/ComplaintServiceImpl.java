package com.example.task.complaintservice.service;

import com.example.task.complaintservice.Dto.ComplaintDto;
import com.example.task.complaintservice.exception.ApiRequestException;
import com.example.task.complaintservice.model.Complaint;
import com.example.task.complaintservice.repository.ComplaintRepository;
import io.jsonwebtoken.Jwts;
import org.apache.http.HttpHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Service;

import javax.servlet.ServletException;
import javax.transaction.Transactional;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
@Service
public class ComplaintServiceImpl implements ComplaintService{

    @Autowired
    private ComplaintRepository complaintRepository;


    @Override
    public Complaint getComplaint(Long userId,Long complaintId) throws ServletException, IOException {
        Optional<Complaint> optionalComplaint =  complaintRepository.findByIdAndAndUserId(complaintId,userId);
        if(optionalComplaint.isEmpty()){
            throw new ApiRequestException("Comment does not exist");
        }
        return optionalComplaint.get();
    }

    @Override
    public List<Complaint> getComplaints(Long userId) throws ServletException, IOException {
        List<Complaint> complaintList = complaintRepository.findByUserId(userId);
        if(complaintList.isEmpty()){
            throw new ApiRequestException("No complaint by user presently");
        }
        return complaintList;
    }

    @Override
    public Complaint createComplaint(Long userId,ComplaintDto complaintDto) {
        Complaint complaint = new Complaint();
        setComplaint(userId,complaint, complaintDto);
        return  complaintRepository.save(complaint);
    }

    @Override
    @Transactional
    public Complaint updateComplaint(Long userId,Long commentId, ComplaintDto complaintDto)  {
        Optional<Complaint> optionalComplaint = complaintRepository.findByIdAndAndUserId(commentId,userId);
        if(optionalComplaint.isEmpty()){
            throw new ApiRequestException("Comment does not exist");
        }
        Complaint complaint = optionalComplaint.get();
        setComplaint(userId,complaint,complaintDto);
        return complaint;
    }

    @Override
    public Complaint setComplaint(Long userId,Complaint complaint, ComplaintDto complaintDto) {
        complaint.setTitle(complaintDto.getTitle());
        complaint.setMessage(complaintDto.getMessage());
        complaint.setUserId(userId);
        complaint.setStatus(complaintDto.getStatus());
        return complaint;
    }
}
