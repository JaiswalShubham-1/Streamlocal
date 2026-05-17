package com.streamlocal.dev.video.persistence.repository;

import com.streamlocal.dev.video.domain.VideoFileRole;
import com.streamlocal.dev.video.persistence.entity.VideoFile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface VideoFileRepository extends JpaRepository<VideoFile, Long> {

    List<VideoFile> findByVideoId(UUID videoId);

    List<VideoFile> findAllByVideoIdAndFileRole(UUID videoId, VideoFileRole fileRole);

    Optional<VideoFile> findFirstByVideoIdAndFileRole(UUID videoId, VideoFileRole fileRole);

    Optional<VideoFile> findByStoragePath(String storagePath);

    boolean existsByStoragePath(String storagePath);
}