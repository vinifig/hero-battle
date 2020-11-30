CREATE TABLE battle(
    id BIGSERIAL PRIMARY KEY,
    winner_hero_id BIGINT,
    loser_hero_id BIGINT,
    "timestamp" BIGINT,

    FOREIGN KEY (winner_hero_id) REFERENCES hero (id),
    FOREIGN KEY (loser_hero_id) REFERENCES hero (id)
);