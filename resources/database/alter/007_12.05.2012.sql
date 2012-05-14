 -- Tournament implementation

 BEGIN;

 CREATE SEQUENCE tournament_seq INCREMENT 1 START 1;

 CREATE TABLE tournament (
   id id NOT NULL default nextval('tournament_seq'),
   version version NOT NULL default NOW(),
   name CHARACTER VARYING (255) not null,
   max_participants_count id not null,
   status CHARACTER VARYING (20) not null,
   PRIMARY KEY (id)
 );

 INSERT INTO execution_history (name) VALUES ('007_12.05.2012.sql');

COMMIT;