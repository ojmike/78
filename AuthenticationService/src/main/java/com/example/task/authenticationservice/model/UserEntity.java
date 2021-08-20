package com.example.task.authenticationservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "users")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserEntity {
   @Id
   @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String firstName;
    private String password;
    private String lastName;
    private String email;
}
