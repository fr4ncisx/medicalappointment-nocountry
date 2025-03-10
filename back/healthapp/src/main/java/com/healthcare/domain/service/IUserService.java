package com.healthcare.domain.service;

import com.healthcare.domain.dto.response.UserResponseDTO;

import jakarta.servlet.http.HttpServletRequest;

public interface IUserService {
    public UserResponseDTO getUser(String email, HttpServletRequest request);
}
