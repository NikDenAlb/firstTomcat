drop table if exists users CASCADE;
drop table if exists coaches CASCADE;
drop table if exists users_coaches;

CREATE TABLE users
(
    user_id SERIAL PRIMARY KEY,
    name    VARCHAR(100) NOT NULL
);
CREATE TABLE coaches
(
    coach_id SERIAL PRIMARY KEY,
    name     VARCHAR(100) NOT NULL
);
CREATE TABLE users_coaches
(
    user_id  BIGINT,
    coach_id BIGINT,
    PRIMARY KEY (user_id, coach_id),
    FOREIGN KEY (user_id) REFERENCES users (user_id) ON DELETE CASCADE,
    FOREIGN KEY (coach_id) REFERENCES coaches (coach_id) ON DELETE CASCADE
);