CREATE TABLE noise_record_sound_type (
    noise_record_id BIGINT NOT NULL,
    sound_type VARCHAR(50) NOT NULL,

    CONSTRAINT pk_noise_record_sound_type
        PRIMARY KEY (noise_record_id, sound_type),

    CONSTRAINT fk_noise_record_sound_type
        FOREIGN KEY (noise_record_id)
        REFERENCES noise_records(id)
);