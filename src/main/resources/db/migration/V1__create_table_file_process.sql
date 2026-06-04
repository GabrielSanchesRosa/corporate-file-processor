CREATE TABLE file_process (
    id              UUID            NOT NULL,
    file_name       VARCHAR(255)    NOT NULL,
    received_at     TIMESTAMP       NOT NULL,
    processed_at    TIMESTAMP,
    status          VARCHAR(50)     NOT NULL,
    total_records   INTEGER,
    success_records INTEGER,
    failed_records  INTEGER,
    PRIMARY KEY (id)
);