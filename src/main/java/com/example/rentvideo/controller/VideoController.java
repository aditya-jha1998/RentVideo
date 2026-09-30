package com.example.rentvideo.controller;

import com.example.rentvideo.dto.VideoRequest;
import com.example.rentvideo.entity.Video;
import com.example.rentvideo.service.VideoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/videos")
public class VideoController {

    private final VideoService videoService;


    public VideoController(VideoService videoService) {
        this.videoService = videoService;
    }

    @GetMapping("/{id}")
    public List<Video> getAvailableVideos(){
        return  videoService.getAvailableVideo();
    }


    @PostMapping
    public ResponseEntity<Video> createVideo(
            @Valid @RequestBody VideoRequest request) {

        Video video = videoService.createVidoe(request);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(video);
    }

    @PutMapping("/{id}")
    public Video updateVideo(
            @PathVariable Long id,
            @Valid @RequestBody VideoRequest request) {

        return videoService.updateVideo(id, request);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVideo(@PathVariable Long id) {

        videoService.deleteVideo(id);

        return ResponseEntity.noContent().build();
    }
}
