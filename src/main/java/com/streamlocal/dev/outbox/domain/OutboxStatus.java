package com.streamlocal.dev.outbox.domain;

public enum OutboxStatus {
    PENDING,
    PUBLISHED,
    FAILED
}