package com.icg.icgbackend.service.Impl;

import com.icg.icgbackend.exception.UserNotFoundException;
import com.icg.icgbackend.model.User;
import com.icg.icgbackend.payload.reponse.JwtResponse;
import com.icg.icgbackend.payload.request.LoginRequest;
import com.icg.icgbackend.repository.UserRepository;
import com.icg.icgbackend.security.JWT.JWTTokenProvider;
import com.icg.icgbackend.service.AuthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthServiceImpl implements AuthService {

    public static final Logger LOG = LoggerFactory.getLogger(AuthServiceImpl.class);

    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;
    private final JWTTokenProvider jwtTokenProvider;

    @Override
    @Transactional
    public JwtResponse authenticateUser(LoginRequest loginRequest) {
        String username = loginRequest.getUsername();
        String password = loginRequest.getPassword();
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(username, password));
        User user = userRepository.findByUsername(username).orElseThrow(() ->{
            LOG.error("User with username " + username + "was not found");
                    return new UserNotFoundException("User with username " + username + "was not found");
                }
        );

        String token = jwtTokenProvider.generateJwtToken(user);
        return new JwtResponse(token);

    }

}
