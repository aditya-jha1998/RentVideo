package com.example.rentvideo.service;

import com.example.rentvideo.dto.RegisterRequest;
import com.example.rentvideo.dto.UserResponse;
import com.example.rentvideo.entity.Role;
import com.example.rentvideo.entity.USER;
import com.example.rentvideo.repository.UserRepository;
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


    public UserResponse registeruser(RegisterRequest request){
        if(userRepository.findByEmail(request.getEmail()).isPresent()){
            throw new RuntimeException("Email already registered");
        }

        USER user=new USER();

        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setFirstname(request.getFirstname());
        user.setLastname(request.getLastname());

        if(request.getRole()==null){
            user.setRole(Role.CUSTOMER);
        }
        else{
            user.setRole(request.getRole());
        }

        USER saveduser =userRepository.save(user);

        return new UserResponse(
                saveduser.getId(),
                saveduser.getEmail(),
                saveduser.getFirstname(),
                saveduser.getLastname(),
                saveduser.getRole()
        );
    }
}
