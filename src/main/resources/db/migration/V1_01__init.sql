create sequence public.location_seq;

CREATE TABLE location (
    id BIGINT PRIMARY KEY  DEFAULT nextval('location_seq'),
    query VARCHAR(255),
    is_active boolean,
    time_offset INTEGER
);

create sequence public.weather_data_seq;

CREATE TABLE weather_data (
    id BIGINT PRIMARY KEY DEFAULT nextval('weather_data_seq'),
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

create sequence public.settings_seq;

CREATE TABLE settings(
    id BIGINT PRIMARY KEY DEFAULT nextval('settings_seq'),
    name VARCHAR(255),
    value VARCHAR(255)
);

INSERT INTO settings values (default, 'timeout', '10')