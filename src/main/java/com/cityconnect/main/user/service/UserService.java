package com.cityconnect.main.user.service;

import com.cityconnect.main.user.model.User;
import com.cityconnect.main.user.model.UserRole;
import com.cityconnect.main.user.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

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
}
