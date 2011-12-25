 -- Create table execution history

 BEGIN;

 CREATE TABLE execution_history (
     name CHARACTER VARYING(40) NOT NULL,
     version TIMESTAMP NOT NULL DEFAULT now(),
     CONSTRAINT PK_execution_history PRIMARY KEY (name)
 );

 INSERT INTO execution_history (name) VALUES ('001_25.12.2011.sql');

commit;