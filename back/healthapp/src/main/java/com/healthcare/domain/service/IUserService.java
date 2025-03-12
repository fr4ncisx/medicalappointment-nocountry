package com.healthcare.domain.service;

import com.healthcare.domain.dto.request.UserRequestUpdate;
import com.healthcare.domain.dto.response.UserResponse;
import jakarta.servlet.http.HttpServletRequest;

public interface IUserService {
    UserResponse getUser(String email, HttpServletRequest request);
    void edit(Long id, UserRequestUpdate user, HttpServletRequest request);
}
