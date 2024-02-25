CREATE TABLE location (
    id BIGINT PRIMARY KEY,
    query VARCHAR(255),
    is_active boolean,
    time_offset INTEGER
);

CREATE TABLE weather_data (
    id SERIAL PRIMARY KEY,
    observation_time VARCHAR(255),
    temperature DOUBLE PRECISION,
    wind_speed INTEGER,
    wind_degree INTEGER,
    wind_dir VARCHAR(255),
    pressure INTEGER,
    precip INTEGER,
    humidity INTEGER,
    cloudcover INTEGER,
    feelslike INTEGER,
    uv_index INTEGER,
    visibility INTEGER,
    is_day VARCHAR(255),
    location_id INTEGER REFERENCES location(id)
);

