package com.example.task.complaintservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name ="complaint")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Complaint {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "complaint_id")
    private Long id;

    @Column(name="complaint_title")
    private String title;

    @Column(name="complaint_message")
    private String message;

    @Column(name="complaint_status")
    private String status;

    @Column(name="user_id")
    private Long userId;
}
