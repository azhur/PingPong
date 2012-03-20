 -- Create table execution history

 BEGIN;
 CREATE SEQUENCE player_seq INCREMENT 1 START 1;


 CREATE TABLE player (
     id id NOT NULL default nextval('player_seq'),
     name CHARACTER VARYING(128) NOT NULL,
     email CHARACTER VARYING(128) NOT NULL,
     login CHARACTER VARYING(128) NOT NULL,
     password CHARACTER VARYING(128) NOT NULL,
     birth DATE NOT NULL,
     enabled BOOLEAN NOT NULL,
     gender gender NOT NULL,
     version version NOT NULL DEFAULT now(),
     CONSTRAINT id PRIMARY KEY (id)
 );

 create unique index idx_player_login on player(login);

 INSERT INTO execution_history (name) VALUES ('002_25.12.2011.sql');

commit;