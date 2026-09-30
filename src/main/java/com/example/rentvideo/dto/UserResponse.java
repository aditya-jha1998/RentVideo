package com.example.rentvideo.dto;


import com.example.rentvideo.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserResponse {

    private  long id;
    private String email;
    private String firstname;
    private String lastname;
    private Role role;
}
