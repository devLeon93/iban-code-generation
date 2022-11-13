package com.icg.icgbackend.service.Impl;

import com.icg.icgbackend.exception.UserNotFoundException;
import com.icg.icgbackend.model.Role;
import com.icg.icgbackend.model.URole;
import com.icg.icgbackend.model.User;
import com.icg.icgbackend.payload.reponse.JwtResponse;
import com.icg.icgbackend.payload.reponse.MessageResponse;
import com.icg.icgbackend.payload.request.LoginRequest;
import com.icg.icgbackend.payload.request.SignupRequest;
import com.icg.icgbackend.repository.RoleRepository;
import com.icg.icgbackend.repository.UserRepository;
import com.icg.icgbackend.security.JWT.JWTTokenProvider;
import com.icg.icgbackend.security.UserDetailsImpl;
import com.icg.icgbackend.service.AuthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

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
