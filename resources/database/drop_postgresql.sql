/* ---------------------------------------------------------------------- */
/* Script generated with: DeZign for Databases v4.1.3                     */
/* Target DBMS:           PostgreSQL 8                                    */
/* Project file:          PingPongDataModel.dez                           */
/* Project name:                                                          */
/* Author:                                                                */
/* Script type:           Database drop script                            */
/* Created on:            2010-10-18 18:05                                */
/* ---------------------------------------------------------------------- */


/* ---------------------------------------------------------------------- */
/* Drop sequences                                                         */
/* ---------------------------------------------------------------------- */

DROP SEQUENCE champion_seq;

DROP SEQUENCE game_seq;

DROP SEQUENCE match_seq;

DROP SEQUENCE player_seq;

DROP SEQUENCE round_seq;

DROP SEQUENCE tournament_seq;

DROP SEQUENCE role_seq;

/* ---------------------------------------------------------------------- */
/* Drop foreign key constraints                                           */
/* ---------------------------------------------------------------------- */

ALTER TABLE player_round DROP CONSTRAINT player_player_round;

ALTER TABLE player_round DROP CONSTRAINT round_player_round;

ALTER TABLE match DROP CONSTRAINT player2_match;

ALTER TABLE match DROP CONSTRAINT player1_match;

ALTER TABLE match DROP CONSTRAINT tournament_match;

ALTER TABLE match DROP CONSTRAINT round_match;

ALTER TABLE game DROP CONSTRAINT match_game;

ALTER TABLE champion DROP CONSTRAINT player_champion;

ALTER TABLE champion DROP CONSTRAINT tournament_champion;

ALTER TABLE role_player DROP CONSTRAINT role_role_player;

ALTER TABLE role_player DROP CONSTRAINT player_role_player;

ALTER TABLE remind_password DROP CONSTRAINT player_remind_password;

/* ---------------------------------------------------------------------- */
/* Drop table "player"                                                    */
/* ---------------------------------------------------------------------- */

/* Drop constraints */

ALTER TABLE player DROP CONSTRAINT id;

/* Drop table */

DROP TABLE player;

/* ---------------------------------------------------------------------- */
/* Drop table "round"                                                     */
/* ---------------------------------------------------------------------- */

/* Drop constraints */

ALTER TABLE round DROP CONSTRAINT PK_round;

/* Drop table */

DROP TABLE round;

/* ---------------------------------------------------------------------- */
/* Drop table "player_round"                                              */
/* ---------------------------------------------------------------------- */

/* Drop constraints */

ALTER TABLE player_round DROP CONSTRAINT PK_player_round;

/* Drop table */

DROP TABLE player_round;

/* ---------------------------------------------------------------------- */
/* Drop table "match"                                                     */
/* ---------------------------------------------------------------------- */

/* Drop constraints */

ALTER TABLE match DROP CONSTRAINT PK_match;

/* Drop table */

DROP TABLE match;

/* ---------------------------------------------------------------------- */
/* Drop table "game"                                                      */
/* ---------------------------------------------------------------------- */

/* Drop constraints */

ALTER TABLE game DROP CONSTRAINT PK_game;

/* Drop table */

DROP TABLE game;

/* ---------------------------------------------------------------------- */
/* Drop table "tournament"                                                */
/* ---------------------------------------------------------------------- */

/* Drop constraints */

ALTER TABLE tournament DROP CONSTRAINT PK_tournament;

/* Drop table */

DROP TABLE tournament;

/* ---------------------------------------------------------------------- */
/* Drop table "champion"                                                  */
/* ---------------------------------------------------------------------- */

/* Drop constraints */

ALTER TABLE champion DROP CONSTRAINT PK_champion;

/* Drop table */

DROP TABLE champion;

/* ---------------------------------------------------------------------- */
/* Drop table "role"                                                      */
/* ---------------------------------------------------------------------- */

/* Drop constraints */

ALTER TABLE role DROP CONSTRAINT PK_role;

/* Drop table */

DROP TABLE role;

/* ---------------------------------------------------------------------- */
/* Drop table "role_player"                                               */
/* ---------------------------------------------------------------------- */

/* Drop constraints */

ALTER TABLE role_player DROP CONSTRAINT PK_role_player;

/* Drop table */

DROP TABLE role_player;

/* ---------------------------------------------------------------------- */
/* Drop table "remind_password"                                           */
/* ---------------------------------------------------------------------- */

/* Drop constraints */

/* Drop table */

DROP TABLE remind_password;

/* ---------------------------------------------------------------------- */
/* Drop domains                                                           */
/* ---------------------------------------------------------------------- */

DROP DOMAIN round_group;

DROP DOMAIN gender;

DROP DOMAIN user_role;
