 -- Create table execution history

 BEGIN;
 CREATE SEQUENCE player_seq INCREMENT 1 START 1;
 CREATE DOMAIN gender AS CHARACTER(30);


 CREATE TABLE player (
     id INTEGER CONSTRAINT NN_id NOT NULL default nextval('player_seq'),
     name CHARACTER VARYING(128) CONSTRAINT NN_firstname NOT NULL,
     email CHARACTER VARYING(128) CONSTRAINT NN_email NOT NULL,
     login CHARACTER VARYING(128) CONSTRAINT NN_login NOT NULL,
     password CHARACTER VARYING(128) CONSTRAINT NN_password NOT NULL,
     birth DATE CONSTRAINT NN_birth NOT NULL,
     enabled BOOLEAN CONSTRAINT NN_enabled NOT NULL,
     gender gender CONSTRAINT NN_gender NOT NULL,
     version TIMESTAMP CONSTRAINT NN_version NOT NULL default now(),
     CONSTRAINT id PRIMARY KEY (id)
 );

 create unique index idx_player_login on player(login);

 INSERT INTO execution_history (name) VALUES ('002_25.12.2011.sql');

commit;