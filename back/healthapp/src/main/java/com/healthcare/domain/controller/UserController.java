package com.healthcare.domain.controller;

import org.springframework.web.bind.annotation.RestController;

import com.healthcare.domain.dto.response.UserResponseDTO;
import com.healthcare.domain.service.IUserService;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/user")
public class UserController {

    private final IUserService userService;

    @PreAuthorize("hasAnyRole({'ADMIN','MEDICO','PACIENTE'})")
    @GetMapping
    public ResponseEntity<UserResponseDTO> getUserDetails(@RequestParam String email, HttpServletRequest request) {
        var response = userService.getUser(email, request);
        return ResponseEntity.ok(response);
    }    

}
