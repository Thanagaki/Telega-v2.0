DROP TABLE IF EXISTS java_quiz;
DROP TABLE IF EXISTS users;
CREATE SEQUENCE global_seq START WITH 100000;

CREATE TABLE users
(
    id         BIGINT PRIMARY KEY DEFAULT nextval('global_seq'),
    chat_id    INTEGER UNIQUE                NOT NULL,
    name       VARCHAR                       NOT NULL,
    score      INTEGER             DEFAULT 0 NOT NULL,
    high_score INTEGER             DEFAULT 0 NOT NULL,
    bot_state  VARCHAR                       NOT NULL
);

CREATE TABLE youTube_links
(
    id             BIGINT PRIMARY KEY DEFAULT nextval('global_seq'),
    link           VARCHAR NOT NULL

);