package com.streamlocal.dev.video.service;

import com.streamlocal.dev.outbox.service.OutboxService;
import com.streamlocal.dev.video.domain.VideoStatus;
import com.streamlocal.dev.video.persistence.entity.Video;
import com.streamlocal.dev.video.persistence.repository.VideoRepository;
import com.streamlocal.dev.video.web.dto.CreateVideoRequest;
import com.streamlocal.dev.video.web.dto.VideoResponse;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.OffsetDateTime;
import java.util.UUID;

@Service
public class VideoService {

    private final VideoRepository videoRepository;
    private final OutboxService outboxService;

    public VideoService(VideoRepository videoRepository, OutboxService outboxService) {
        this.videoRepository = videoRepository;
        this.outboxService = outboxService;
    }

    @Transactional
    public VideoResponse createVideo(CreateVideoRequest request) {
        if (videoRepository.existsByTitle(request.getTitle())) {
            throw new IllegalArgumentException("Video title already exists: " + request.getTitle());
        }

        OffsetDateTime now = OffsetDateTime.now();

        Video video = new Video();
        video.setId(UUID.randomUUID());
        video.setTitle(request.getTitle());
        video.setDescription(request.getDescription());
        video.setDurationSeconds(request.getDurationSeconds());
        video.setStatus(VideoStatus.UPLOADED);
        video.setCreatedAt(now);
        video.setUpdatedAt(now);

        Video savedVideo = videoRepository.save(video);

        String payload = """
                {
                  "videoId": "%s",
                  "title": "%s",
                  "status": "%s"
                }
                """.formatted(savedVideo.getId(), savedVideo.getTitle(), savedVideo.getStatus().name());

        outboxService.savePendingEvent(
                "VIDEO",
                savedVideo.getId(),
                "VIDEO_CREATED",
                payload
        );

        return toResponse(savedVideo);
    }

    private VideoResponse toResponse(Video video) {
        VideoResponse response = new VideoResponse();
        response.setId(video.getId());
        response.setTitle(video.getTitle());
        response.setDescription(video.getDescription());
        response.setStatus(video.getStatus());
        response.setDurationSeconds(video.getDurationSeconds());
        response.setCreatedAt(video.getCreatedAt());
        response.setUpdatedAt(video.getUpdatedAt());
        return response;
    }
}