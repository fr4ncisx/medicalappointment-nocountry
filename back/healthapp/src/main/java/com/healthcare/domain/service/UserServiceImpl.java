package com.healthcare.domain.service;

import com.healthcare.domain.dto.request.UserRequestUpdate;
import com.healthcare.domain.dto.response.UserResponse;
import com.healthcare.domain.exceptions.NotFoundInDatabaseException;
import com.healthcare.domain.model.entity.User;
import com.healthcare.domain.repository.UserRepository;
import com.healthcare.infrastructure.security.utils.JwtUtils;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.authorization.AuthorizationDeniedException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements IUserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final JwtUtils jwtUtils;
    private final BCryptPasswordEncoder passwordEncoder;

    @Override
    public UserResponse getUser(String email, HttpServletRequest request) {
        var user = userRepository.findByEmail(email)
                .orElseThrow(() -> new NotFoundInDatabaseException("El usuario no se encontró"));
        var tokenEmail = getEmailFromToken(request);
        if(!user.getEmail().equals(tokenEmail)){
            throw new AuthorizationDeniedException("No se pueden ver los datos de otros usuarios");
        }
        return modelMapper.map(user, UserResponse.class);
    }

    @Transactional
    @Override
    public void edit(Long id, UserRequestUpdate userRequest, HttpServletRequest request) {
        var user = assertUserNotNull(id);
        var userEmail = getEmailFromToken(request);
        if(!userEmail.equals(user.getEmail())){
            throw new AuthorizationDeniedException("No se pueden editar los datos de otros usuarios");
        }
        encodePassword(user, userRequest.getPassword());
        userRepository.save(user);
    }

    private void encodePassword(User u, String rawPassword){
        u.setPassword(passwordEncoder.encode(rawPassword));
    }

    private User assertUserNotNull(Long id){
        return userRepository.findById(id).orElseThrow(() -> new NotFoundInDatabaseException("Usuario no encontrado"));
    }

    private String getEmailFromToken(HttpServletRequest request){
        String token = request.getHeader("Authorization").replace("Bearer ", "");
        return jwtUtils.verifyToken(token).getSubject();
    }

}
