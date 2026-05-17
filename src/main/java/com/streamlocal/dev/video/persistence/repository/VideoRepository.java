package com.streamlocal.dev.video.persistence.repository;

import com.streamlocal.dev.video.persistence.entity.Video;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface VideoRepository extends JpaRepository<Video, UUID> {

    Optional<Video> findByTitle(String title);

    boolean existsByTitle(String title);
}