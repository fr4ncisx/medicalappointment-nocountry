package com.healthcare.infrastructure.security.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.healthcare.domain.repository.UserRepository;
import com.healthcare.infrastructure.dto.request.RequestLoginDTO;
import com.healthcare.infrastructure.dto.request.UserAuthenticated;
import com.healthcare.infrastructure.dto.response.ResponseTokenDTO;
import com.healthcare.infrastructure.security.utils.JwtUtils;

@Service
public class AuthService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    /**
     * 
     * @param token JWT token after login
     * @throws UsernameNotFoundException      if user was not found in database
     * @throws BadCredentialsException        Invalid password from password matches
     * @throws IllegalArgumentException       if user is null
     * @throws AlgorithmMismatchException     if the algorithm stated in the token's
     *                                        header is not equal to
     *                                        the one defined in the
     *                                        {@link JWTVerifier}.
     * @throws SignatureVerificationException if the signature is invalid.
     * @throws TokenExpiredException          if the token has expired.
     * @throws MissingClaimException          if a claim to be verified is missing.
     * @throws IncorrectClaimException        if a claim contained a different value
     *                                        than the expected one.
     * @throws JWTVerificationException
     * 
     */

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
        try {
            UserAuthenticated userDetails = (UserAuthenticated) loadUserByUsername(login.email());
            passwordMatches(login, userDetails);
            loadSecurityContext(userDetails);
            String createdToken = jwtUtils.createToken(userDetails);
            return ResponseEntity.ok(new ResponseTokenDTO(createdToken));
        } catch (UsernameNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Map.of("error", ex.getMessage()));
        } catch (BadCredentialsException ex) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(Map.of("error", ex.getMessage()));
        }
    }

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

    public <U extends UserAuthenticated> void passwordMatches(RequestLoginDTO requestFromLogin, U userFromRepository) {
        if (!passwordEncoder.matches(requestFromLogin.password(), userFromRepository.getPassword())) {
            throw new BadCredentialsException("Contraseña incorrecta");
        }
    }

    public void validateUser(DecodedJWT decodedJWT) {
        UserDetails userDetails = loadUserByUsername(decodedJWT.getSubject());
        jwtUtils.loadSecurityContext(userDetails);
    }                

    public <U extends UserDetails> void loadSecurityContext(U user) {
        if (user == null) {
            throw new IllegalArgumentException();
        }
        SecurityContext securityContext = SecurityContextHolder.getContext();
        Authentication auth = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
        securityContext.setAuthentication(auth);
    }
}
