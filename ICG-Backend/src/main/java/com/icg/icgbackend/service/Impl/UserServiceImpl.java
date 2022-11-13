package com.icg.icgbackend.service.Impl;

import com.icg.icgbackend.dto.RoleDto;
import com.icg.icgbackend.dto.UserDto;
import com.icg.icgbackend.exception.UserExistException;
import com.icg.icgbackend.mapper.RoleDtoMapper;
import com.icg.icgbackend.mapper.UserDtoMapper;
import com.icg.icgbackend.model.Role;
import com.icg.icgbackend.model.User;
import com.icg.icgbackend.payload.request.SignupRequest;
import com.icg.icgbackend.repository.RoleRepository;
import com.icg.icgbackend.repository.UserRepository;
import com.icg.icgbackend.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    private final UserDtoMapper userDtoMapper;
    private final RoleDtoMapper roleDtoMapper;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public static final Logger LOG = LoggerFactory.getLogger(UserServiceImpl.class);


    @Override
    public void createUser(SignupRequest signupRequest) {
        Optional<User> userByUsername = userRepository.findByUsername(signupRequest.getUsername());
        if(userByUsername.isPresent()){
            throw new UserExistException(
                    "The user " + signupRequest.getUsername() + " already exist. Please check credentials");

        }
        User user = new User();
        user.setUsername(signupRequest.getUsername());
        user.setEmail(signupRequest.getEmail());
        var rolesUser = roleRepository
                .findAll()
                .stream().filter(e->e.getName().name().equals(signupRequest.getRole()))
                .findFirst()
                .get();
        user.setRoles(Set.of(rolesUser));
        user.setPassword(bCryptPasswordEncoder.encode(signupRequest.getPassword()));
        LOG.info("Creating User {}", signupRequest.getUsername());
        userRepository.save(user);

    }


    //TODO: Must be refactoring
    @Override
    public void editUser(Long id, SignupRequest signupRequest) {
        User userEdit = userRepository.findById(id).
                orElseThrow(() -> new EntityNotFoundException("User not found by id " + id));
        var roleUser =roleRepository
                .findAll()
                .stream().filter(e->e.getName().name().equals(signupRequest.getRole()))
                .findFirst()
                .get();
        userEdit.setRoles(Set.of(roleUser));
        userEdit.setUsername(signupRequest.getUsername());
        userEdit.setEmail(signupRequest.getEmail());
        LOG.info("Updating User  {}", " id = " + userEdit.getId());
        userRepository.save(userEdit);

    }

    @Override
    public void deleteUser(Long id, SignupRequest signupRequest) {
        User userDelete = userRepository.findById(id).
                orElseThrow(() -> new EntityNotFoundException("User not found by id " + id));
        LOG.info("Deleted User  {}", " id = " + userDelete.getId());
        userRepository.delete(userDelete);

    }

    @Override
    public UserDto getUserById(Long id) {
        return userRepository.findById(id).map(userDtoMapper::mapper)
                .orElseThrow(() -> new EntityNotFoundException("User with id " + id + " does not exist!"));
    }

    @Override
    public List<UserDto> getAllUsers() {
        final List<User> users = userRepository.findAll();
        return users.stream().map(userDtoMapper::mapper).collect(Collectors.toList());
    }

    @Override
    public List<RoleDto> getAllRoles() {
        final List<Role> roles = roleRepository.findAll();
        return roles.stream()
                .map(roleDtoMapper::mapper)
                .collect(Collectors.toList());
    }
}
