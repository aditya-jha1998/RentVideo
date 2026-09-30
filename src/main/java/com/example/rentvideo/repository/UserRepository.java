package com.example.rentvideo.repository;

import com.example.rentvideo.entity.USER;
import com.example.rentvideo.entity.Video;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<USER, Long> {

    Optional<USER> findByEmail(String email);
}
