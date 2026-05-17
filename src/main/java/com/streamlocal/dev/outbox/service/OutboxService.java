package com.streamlocal.dev.outbox.service;

import com.streamlocal.dev.outbox.domain.OutboxStatus;
import com.streamlocal.dev.outbox.persistence.entity.OutboxEvent;
import com.streamlocal.dev.outbox.persistence.repository.OutboxEventRepository;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.UUID;

@Service
public class OutboxService {

    private final OutboxEventRepository outboxEventRepository;

    public OutboxService(OutboxEventRepository outboxEventRepository) {
        this.outboxEventRepository = outboxEventRepository;
    }

    public OutboxEvent savePendingEvent(String aggregateType, UUID aggregateId, String eventType, String payload) {
        OutboxEvent outboxEvent = new OutboxEvent();
        outboxEvent.setAggregateType(aggregateType);
        outboxEvent.setAggregateId(aggregateId);
        outboxEvent.setEventType(eventType);
        outboxEvent.setPayload(payload);
        outboxEvent.setStatus(OutboxStatus.PENDING);
        outboxEvent.setRetryCount(0);
        outboxEvent.setCreatedAt(OffsetDateTime.now());

        return outboxEventRepository.save(outboxEvent);
    }
}