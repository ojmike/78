package com.example.task.complaintservice.service;

import com.example.task.complaintservice.Dto.ComplaintDto;
import com.example.task.complaintservice.model.Complaint;
import com.example.task.complaintservice.repository.ComplaintRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
@Service
public class ComplaintServiceImpl implements ComplaintService{

    @Autowired
    private ComplaintRepository complaintRepository;

    @Override
    public Complaint getComplaint(Long userId, Long complaintId) {
        return complaintRepository.findByIdAndAndUserId(complaintId,userId).get();
    }

    @Override
    public List<Complaint> getComplaints(Long userId) {
        return complaintRepository.findByUserId(userId);
    }

    @Override
    public Complaint createComplaint(Long userId, ComplaintDto complaintDto) {
        Complaint complaint = new Complaint();
        setComplaint(complaint, complaintDto);
        return  complaintRepository.save(complaint);
    }

    @Override
    @Transactional
    public Complaint updateComplaint(Long userId, Long commentId, ComplaintDto complaintDto) {
        Optional<Complaint> optionalComplaint = complaintRepository.findByIdAndAndUserId(commentId,userId);
        Complaint complaint = optionalComplaint.get();
        setComplaint(complaint,complaintDto);
        return null;
    }

    @Override
    public Complaint setComplaint(Complaint complaint, ComplaintDto complaintDto) {
        complaint.setTitle(complaintDto.getTitle());
        complaint.setMessage(complaintDto.getMessage());
        complaint.setUserId(complaintDto.getUserId());
        complaint.setStatus(complaintDto.getStatus());
        return complaint;
    }
}
