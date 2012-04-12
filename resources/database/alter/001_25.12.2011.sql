 -- Create table execution history

 BEGIN;

 CREATE DOMAIN gender AS CHARACTER VARYING (10);
 CREATE DOMAIN id AS Integer;
 CREATE DOMAIN email AS CHARACTER VARYING (128);
 CREATE DOMAIN password AS CHARACTER VARYING (128);
 CREATE DOMAIN version TIMESTAMP;

 CREATE TABLE execution_history (
     name CHARACTER VARYING(40) NOT NULL,
     version version NOT NULL DEFAULT now(),
     CONSTRAINT PK_execution_history PRIMARY KEY (name)
 );

 INSERT INTO execution_history (name) VALUES ('001_25.12.2011.sql');

commit;