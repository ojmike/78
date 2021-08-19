package com.example.task.complaintservice.repository;

import com.example.task.complaintservice.model.Complaint;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface ComplaintRepository extends JpaRepository<Complaint, Long> {
    Optional<Complaint> findByIdAndAndUserId(Long commentId, Long userId);
    List<Complaint> findByUserId(Long userId);
}
