/* ---------------------------------------------------------------------- */
/* Script generated with: DeZign for Databases v4.1.3                     */
/* Target DBMS:           PostgreSQL 8                                    */
/* Project file:          PingPongDataModel.dez                           */
/* Project name:                                                          */
/* Author:                                                                */
/* Script type:           Database creation script                        */
/* Created on:            2010-10-18 18:05                                */
/* ---------------------------------------------------------------------- */


/* ---------------------------------------------------------------------- */
/* Domains                                                                */
/* ---------------------------------------------------------------------- */

CREATE DOMAIN round_group AS CHARACTER(1) CHECK (value IS NULL OR value IN ('A','B','C','D'));

CREATE DOMAIN gender AS CHARACTER(1) CHECK (value IN ('F','M'));

CREATE DOMAIN user_role AS CHARACTER(1) CHECK (value IN ('A','P'));

/* ---------------------------------------------------------------------- */
/* Sequences                                                              */
/* ---------------------------------------------------------------------- */

CREATE SEQUENCE champion_seq INCREMENT 1 START 1;

CREATE SEQUENCE game_seq INCREMENT 1 START 1;

CREATE SEQUENCE match_seq INCREMENT 1 START 1;

CREATE SEQUENCE player_seq INCREMENT 1 START 1;

CREATE SEQUENCE round_seq INCREMENT 1 START 1;

CREATE SEQUENCE tournament_seq INCREMENT 1 START 1;

CREATE SEQUENCE role_seq INCREMENT 1 START 1;

/* ---------------------------------------------------------------------- */
/* Tables                                                                 */
/* ---------------------------------------------------------------------- */

/* ---------------------------------------------------------------------- */
/* Add table "player"                                                     */
/* ---------------------------------------------------------------------- */

CREATE TABLE player (
    player_id INTEGER CONSTRAINT NN_player_id NOT NULL,
    firstname CHARACTER VARYING(128) CONSTRAINT NN_firstname NOT NULL,
    lastname CHARACTER VARYING(128) CONSTRAINT NN_lastname NOT NULL,
    email CHARACTER VARYING(128) CONSTRAINT NN_email NOT NULL,
    login CHARACTER VARYING(128) CONSTRAINT NN_login NOT NULL,
    password CHARACTER VARYING(128) CONSTRAINT NN_password NOT NULL,
    birth DATE CONSTRAINT NN_birth NOT NULL,
    enabled BOOLEAN CONSTRAINT NN_enabled NOT NULL,
    gender gender CONSTRAINT NN_gender NOT NULL,
    version TIMESTAMP CONSTRAINT NN_version NOT NULL,
    CONSTRAINT id PRIMARY KEY (player_id)
);

COMMENT ON COLUMN player.gender IS 'M,F';

/* ---------------------------------------------------------------------- */
/* Add table "round"                                                      */
/* ---------------------------------------------------------------------- */

CREATE TABLE round (
    round_id INTEGER CONSTRAINT NN_round_id NOT NULL,
    name CHARACTER VARYING(64) CONSTRAINT NN_name NOT NULL,
    version TIMESTAMP CONSTRAINT NN_version NOT NULL,
    CONSTRAINT PK_round PRIMARY KEY (round_id)
);

/* ---------------------------------------------------------------------- */
/* Add table "player_round"                                               */
/* ---------------------------------------------------------------------- */

CREATE TABLE player_round (
    round_id INTEGER CONSTRAINT NN_round_id NOT NULL,
    player_id INTEGER CONSTRAINT NN_player_id NOT NULL,
    round_group round_group,
    version TIMESTAMP CONSTRAINT NN_version NOT NULL,
    CONSTRAINT PK_player_round PRIMARY KEY (round_id, player_id)
);

/* ---------------------------------------------------------------------- */
/* Add table "match"                                                      */
/* ---------------------------------------------------------------------- */

CREATE TABLE match (
    match_id INTEGER CONSTRAINT NN_match_id NOT NULL,
    tournament_id INTEGER CONSTRAINT NN_tournament_id NOT NULL,
    round_id INTEGER CONSTRAINT NN_round_id NOT NULL,
    player1_id INTEGER CONSTRAINT NN_player1_id NOT NULL,
    player2_id INTEGER CONSTRAINT NN_player2_id NOT NULL,
    round_group round_group,
    match_date TIMESTAMP CONSTRAINT NN_match_date NOT NULL,
    version TIMESTAMP CONSTRAINT NN_version NOT NULL,
    CONSTRAINT PK_match PRIMARY KEY (match_id)
);

/* ---------------------------------------------------------------------- */
/* Add table "game"                                                       */
/* ---------------------------------------------------------------------- */

CREATE TABLE game (
    game_id INTEGER CONSTRAINT NN_game_id NOT NULL,
    match_id INTEGER CONSTRAINT NN_match_id NOT NULL,
    player1_points SMALLINT DEFAULT 0 CONSTRAINT NN_player1_points NOT NULL,
    player2_points SMALLINT DEFAULT 0 CONSTRAINT NN_player2_points NOT NULL,
    version TIMESTAMP CONSTRAINT NN_version NOT NULL,
    CONSTRAINT PK_game PRIMARY KEY (game_id)
);

/* ---------------------------------------------------------------------- */
/* Add table "tournament"                                                 */
/* ---------------------------------------------------------------------- */

CREATE TABLE tournament (
    tournament_id INTEGER CONSTRAINT NN_tournament_id NOT NULL,
    name CHARACTER VARYING(256) CONSTRAINT NN_name NOT NULL,
    version TIMESTAMP CONSTRAINT NN_version NOT NULL,
    CONSTRAINT PK_tournament PRIMARY KEY (tournament_id)
);

/* ---------------------------------------------------------------------- */
/* Add table "champion"                                                   */
/* ---------------------------------------------------------------------- */

CREATE TABLE champion (
    champion_id INTEGER CONSTRAINT NN_champion_id NOT NULL,
    tournament_id INTEGER CONSTRAINT NN_tournament_id NOT NULL,
    player_id INTEGER CONSTRAINT NN_player_id NOT NULL,
    place SMALLINT CONSTRAINT NN_place NOT NULL,
    version TIMESTAMP CONSTRAINT NN_version NOT NULL,
    CONSTRAINT PK_champion PRIMARY KEY (champion_id)
);

/* ---------------------------------------------------------------------- */
/* Add table "role"                                                       */
/* ---------------------------------------------------------------------- */

CREATE TABLE role (
    role_id INTEGER CONSTRAINT NN_role_id NOT NULL,
    name user_role CONSTRAINT NN_name NOT NULL,
    CONSTRAINT PK_role PRIMARY KEY (role_id)
);

/* ---------------------------------------------------------------------- */
/* Add table "role_player"                                                */
/* ---------------------------------------------------------------------- */

CREATE TABLE role_player (
    role_id INTEGER CONSTRAINT NN_role_id NOT NULL,
    player_id INTEGER CONSTRAINT NN_player_id NOT NULL,
    CONSTRAINT PK_role_player PRIMARY KEY (role_id, player_id)
);

/* ---------------------------------------------------------------------- */
/* Add table "remind_password"                                            */
/* ---------------------------------------------------------------------- */

CREATE TABLE remind_password (
    player_id INTEGER CONSTRAINT NN_player_id NOT NULL,
    token CHARACTER VARYING(128) CONSTRAINT NN_token NOT NULL,
    expires DATE CONSTRAINT NN_expires NOT NULL
);

/* ---------------------------------------------------------------------- */
/* Foreign key constraints                                                */
/* ---------------------------------------------------------------------- */

ALTER TABLE player_round ADD CONSTRAINT player_player_round 
    FOREIGN KEY (player_id) REFERENCES player (player_id) ON DELETE RESTRICT ON UPDATE CASCADE;

ALTER TABLE player_round ADD CONSTRAINT round_player_round 
    FOREIGN KEY (round_id) REFERENCES round (round_id) ON DELETE RESTRICT ON UPDATE CASCADE;

ALTER TABLE match ADD CONSTRAINT player2_match 
    FOREIGN KEY (player1_id) REFERENCES player (player_id) ON DELETE RESTRICT ON UPDATE CASCADE;

ALTER TABLE match ADD CONSTRAINT player1_match 
    FOREIGN KEY (player2_id) REFERENCES player (player_id) ON DELETE RESTRICT ON UPDATE CASCADE;

ALTER TABLE match ADD CONSTRAINT tournament_match 
    FOREIGN KEY (tournament_id) REFERENCES tournament (tournament_id) ON DELETE RESTRICT ON UPDATE CASCADE;

ALTER TABLE match ADD CONSTRAINT round_match 
    FOREIGN KEY (round_id) REFERENCES round (round_id) ON DELETE RESTRICT ON UPDATE CASCADE;

ALTER TABLE game ADD CONSTRAINT match_game 
    FOREIGN KEY (match_id) REFERENCES match (match_id) ON DELETE RESTRICT ON UPDATE CASCADE;

ALTER TABLE champion ADD CONSTRAINT player_champion 
    FOREIGN KEY (player_id) REFERENCES player (player_id) ON DELETE RESTRICT ON UPDATE CASCADE;

ALTER TABLE champion ADD CONSTRAINT tournament_champion 
    FOREIGN KEY (tournament_id) REFERENCES tournament (tournament_id) ON DELETE RESTRICT ON UPDATE CASCADE;

ALTER TABLE role_player ADD CONSTRAINT role_role_player 
    FOREIGN KEY (role_id) REFERENCES role (role_id) ON DELETE CASCADE ON UPDATE CASCADE;

ALTER TABLE role_player ADD CONSTRAINT player_role_player 
    FOREIGN KEY (player_id) REFERENCES player (player_id) ON DELETE CASCADE ON UPDATE CASCADE;

ALTER TABLE remind_password ADD CONSTRAINT player_remind_password 
    FOREIGN KEY (player_id) REFERENCES player (player_id);
