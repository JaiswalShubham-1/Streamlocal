package com.streamlocal.dev.outbox.persistence.repository;

import com.streamlocal.dev.outbox.domain.OutboxStatus;
import com.streamlocal.dev.outbox.persistence.entity.OutboxEvent;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OutboxEventRepository extends JpaRepository<OutboxEvent, Long> {

    List<OutboxEvent> findByStatusOrderByCreatedAtAsc(OutboxStatus status);

    List<OutboxEvent> findTop100ByStatusOrderByCreatedAtAsc(OutboxStatus status);
}