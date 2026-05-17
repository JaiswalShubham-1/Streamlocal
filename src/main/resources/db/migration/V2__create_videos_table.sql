CREATE TABLE streamlocal.videos (
    id UUID PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    description TEXT,
    status VARCHAR(32) NOT NULL,
    duration_seconds INTEGER,
    version BIGINT NOT NULL DEFAULT 0,
    created_at TIMESTAMPTZ NOT NULL DEFAULT now(),
    updated_at TIMESTAMPTZ NOT NULL DEFAULT now(),

    CONSTRAINT uq_videos_title UNIQUE (title),

    CONSTRAINT chk_videos_status
        CHECK (status IN ('UPLOADED', 'PROCESSING', 'READY', 'FAILED')),

    CONSTRAINT chk_videos_duration_seconds
        CHECK (duration_seconds IS NULL OR duration_seconds >= 0)
);