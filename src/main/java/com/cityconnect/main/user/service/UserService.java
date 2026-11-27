package com.cityconnect.main.user.service;

import com.cityconnect.main.exception.UserAlreadyExistsException;
import com.cityconnect.main.exception.UserNotFoundException;
import com.cityconnect.main.user.model.User;
import com.cityconnect.main.user.model.UserRole;
import com.cityconnect.main.user.repository.UserRepository;
import com.cityconnect.main.web.dto.RegisterRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
public class UserService {

    private static final Logger log = LoggerFactory.getLogger(UserService.class);
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }


    public void createAdminIfMissing (String username,String email,String rawPassword){

        if(userRepository.existsByUsername(username)){
            return;
        }
        User admin=new User();
        admin.setUsername(username);
        admin.setEmail(email);
        admin.setPassword(passwordEncoder.encode(rawPassword));
        admin.setCity("Sofia");
        admin.setRole(UserRole.ADMIN);

        userRepository.save(admin);
    }
    @Transactional
    public void register(RegisterRequest request){
        if(userRepository.existsByUsername(request.getUsername())) {
            throw new UserAlreadyExistsException("A user with this username already exists.");

        }
        if(userRepository.existsByEmail(request.getEmail())){
            throw new UserAlreadyExistsException("A user with this email already exists.");

        }

        User user = new User();
        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setCity(request.getCity());
        user.setRole(UserRole.USER);

        userRepository.save(user);
        log.info("Registered new user {}", user.getUsername());
    }
    @Transactional(readOnly = true)
    public User getById(UUID id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User not found."));
    }
}
