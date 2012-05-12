 -- Tournament implementation

 BEGIN;

 CREATE TABLE tournament (
   id id NOT NULL,
   version version NOT NULL default NOW(),
   name CHARACTER VARYING (255) not null,
   begin_date timestamp without time zone NOT NULL,
   end_date timestamp without time zone NOT NULL,
   status CHARACTER VARYING (20) not null,
   PRIMARY KEY (id)
 );

 INSERT INTO execution_history (name) VALUES ('007_12.05.2012.sql');

COMMIT;