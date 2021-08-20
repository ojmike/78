package com.example.task.complaintservice.controller;

import com.example.task.complaintservice.Dto.ComplaintDto;
import com.example.task.complaintservice.security.JWTUtil;
import com.example.task.complaintservice.service.ComplaintService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.support.HttpRequestHandlerServlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@RestController
@RequestMapping
public class ComplaintController {
    @Autowired
    private ComplaintService complaintService;

    @Autowired
    private JWTUtil jwtUtil;


    @GetMapping("/complaint/{complaintId}")
    public ResponseEntity<?> getComplaint(HttpServletRequest request, @PathVariable(value = "complaintId") Long complaintId, HttpRequestHandlerServlet httpRequestHandlerServlet) throws ServletException, IOException {
        String jwt = jwtUtil.parseJwt(request);
       Long userId = Long.valueOf(jwtUtil.extractUserName(jwt));
        return new ResponseEntity<>(complaintService.getComplaint(userId,complaintId), HttpStatus.OK);
    }

    @GetMapping("/complaints")
    public ResponseEntity<?> getComplaints(HttpServletRequest request) throws ServletException, IOException {
        String jwt = jwtUtil.parseJwt(request);
        Long userId = Long.valueOf(jwtUtil.extractUserName(jwt));
        return new ResponseEntity<>(complaintService.getComplaints(userId),HttpStatus.OK);
    }

    @PostMapping("complaint")
    public ResponseEntity<?> createComplaint(HttpServletRequest request, @RequestBody ComplaintDto complaintDto){
        String jwt = jwtUtil.parseJwt(request);
        Long userId = Long.valueOf(jwtUtil.extractUserName(jwt));
        return new ResponseEntity<>(complaintService.createComplaint(userId,complaintDto),HttpStatus.OK);
    }

    @PutMapping("complaint/{complaintId}")
    public ResponseEntity<?> updateComplaint(HttpServletRequest request,@PathVariable Long complaintId, @RequestBody ComplaintDto complaintDto){
        String jwt = jwtUtil.parseJwt(request);
        Long userId = Long.valueOf(jwtUtil.extractUserName(jwt));
        System.out.println(userId);
        return new ResponseEntity<>(complaintService.updateComplaint(userId,complaintId,complaintDto),HttpStatus.OK);
    }


}
