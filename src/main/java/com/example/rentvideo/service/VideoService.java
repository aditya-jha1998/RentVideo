package com.example.rentvideo.service;


import com.example.rentvideo.dto.VideoRequest;
import com.example.rentvideo.entity.Video;
import com.example.rentvideo.repository.VideoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VideoService {

    private  final VideoRepository videoRepository;

    public VideoService(VideoRepository videoRepository) {
        this.videoRepository = videoRepository;
    }

    public List<Video> getAvailableVideo(){
        return  videoRepository.findByAvailableTrue();
    }

    public Video createVidoe(VideoRequest request){
        Video video=new Video();

        video.setTitle(request.getTitle());
        video.setDirector(request.getDirector());
        video.setGenre(request.getGenre());
        video.setAvailable(true);

        return videoRepository.save(video);
    }


    public  Video updateVideo(Long id, VideoRequest request) {

        Video video = videoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Video not found"));

        video.setTitle(request.getTitle());
        video.setDirector(request.getDirector());
        video.setGenre(request.getGenre());

        return videoRepository.save(video);
    }

    public void deleteVideo(Long id) {

        Video video = videoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Video not found"));

        videoRepository.delete(video);
    }
}
