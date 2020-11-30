CREATE TABLE hero(
    id BIGSERIAL PRIMARY KEY,
    name TEXT
);

CREATE TABLE skill(
    id BIGSERIAL PRIMARY KEY,
    name TEXT,
    power integer,
    hero_id bigint,

    FOREIGN KEY (hero_id) REFERENCES hero (id)
);