package com.icg.icgbackend.service;

import com.icg.icgbackend.dto.LoginRequest;
import com.icg.icgbackend.dto.RegisterRequest;
import com.icg.icgbackend.dto.UserDto;
import com.icg.icgbackend.exception.UserExistException;
import com.icg.icgbackend.model.User;
import com.icg.icgbackend.repository.UserRepository;
import com.icg.icgbackend.security.JWT.JWTTokenProvider;
import com.icg.icgbackend.security.UserCustomDetail;
import com.icg.icgbackend.util.JWTSuccessAuthenticateResponse;
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

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthServiceImpl implements AuthService {

    public static final Logger LOG = LoggerFactory.getLogger(AuthServiceImpl.class);

    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final JWTTokenProvider jwtTokenProvider;

    @Override
    @Transactional
    public UserDto registerUser(RegisterRequest registerUser) {
        Optional<User> userByUsername = userRepository.findUserByUsername(registerUser.getEmail());
        if(userByUsername.isPresent()){
            throw new UserExistException(
                    "The user " + registerUser.getUsername() + " already exist. Please check credentials");

        }
        User user = new User();
        user.setRoles(user.getRoles());
        user.setUsername(registerUser.getUsername());
        user.setEmail(registerUser.getEmail());
        user.setPassword(bCryptPasswordEncoder.encode(registerUser.getPassword()));
        LOG.info("Register User {}", registerUser.getEmail());
        userRepository.save(user);
        return UserDto.fromUser(user);

    }

    @Override
    @Transactional
    public  JWTSuccessAuthenticateResponse authenticateUser(LoginRequest authenticateUser) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authenticateUser.getUsername(), authenticateUser.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtTokenProvider.generateToken(authentication);

        UserCustomDetail userDetails = (UserCustomDetail) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());
        return new JWTSuccessAuthenticateResponse(
                token
        );
    }
}
