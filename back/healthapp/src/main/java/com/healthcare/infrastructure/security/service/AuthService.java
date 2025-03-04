package com.healthcare.infrastructure.security.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.healthcare.domain.repository.UserRepository;
import com.healthcare.infrastructure.dto.request.RequestLoginDTO;
import com.healthcare.infrastructure.dto.request.UserAuthenticated;
import com.healthcare.infrastructure.security.utils.JwtUtils;

@Service
public class AuthService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtUtils jwtUtils;

    /**
     * Checks whether the user exists or not, if exists it converts User to
     * UserAuthenticated
     */
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepository.findByEmail(email)
                .map(UserAuthenticated::new)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado"));
    }

    /**
     * <p>
     * This method checks step by step calling the repository to retrieve the user
     * object
     * <p>
     * Then we check if the raw password and the encoded password matches
     * <p>
     * After that we save into the SecurityContext the authentication of the User
     * <p>
     * Finally we generate a new JWT and send it with a HTTP STATUS CODE 200
     * 
     * @param login json with username and password
     * @return jwt token
     * @throws UsernameNotFoundException If user was not found
     * @throws BadCredentialsException   Password doesnt match user or is invalid
     */
    public ResponseEntity<?> loginUser(RequestLoginDTO login) {
        UserAuthenticated userDetails = (UserAuthenticated) loadUserByUsername(login.email());
        jwtUtils.passwordMatches(login, userDetails);
        jwtUtils.loadContext(userDetails);
        String createdToken = jwtUtils.generateToken(userDetails);
        return ResponseEntity.ok(createdToken);
    }
}
