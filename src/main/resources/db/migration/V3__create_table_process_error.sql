CREATE TABLE process_error (
    id               UUID          NOT NULL,
    file_process_id  UUID          NOT NULL,
    line_number      INTEGER       NOT NULL,
    error_message    VARCHAR(1000) NOT NULL,
    created_at       TIMESTAMP     NOT NULL,
    PRIMARY KEY (id),
    CONSTRAINT fk_process_error_file_process
       FOREIGN KEY (file_process_id)
           REFERENCES file_process (id)
);