package com.healthcare.domain.service;

import org.modelmapper.ModelMapper;
import org.springframework.security.authorization.AuthorizationDeniedException;
import org.springframework.stereotype.Service;
import com.healthcare.domain.dto.response.UserResponseDTO;
import com.healthcare.domain.exceptions.NotFoundInDatabaseException;
import com.healthcare.domain.repository.UserRepository;
import com.healthcare.infrastructure.security.utils.JwtUtils;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements IUserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final JwtUtils jwtUtils;

    @Override
    public UserResponseDTO getUser(String email, HttpServletRequest request) {
        var user = userRepository.findByEmail(email)
                .orElseThrow(() -> new NotFoundInDatabaseException("El usuario no se encontró"));
        var tokenEmail = getEmailFromToken(request);
        if(!user.getEmail().equals(tokenEmail)){
            throw new AuthorizationDeniedException("No se pueden ver los datos de otros usuarios");
        }
        return modelMapper.map(user, UserResponseDTO.class);
    }

    private String getEmailFromToken(HttpServletRequest request){
        String token = request.getHeader("Authorization").replace("Bearer ", "");
        return jwtUtils.verifyToken(token).getSubject();
    }

}
