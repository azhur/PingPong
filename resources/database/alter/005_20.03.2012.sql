 -- Remove enabled column from player table

 BEGIN;
 alter table player drop column enabled;

 INSERT INTO execution_history (name) VALUES ('005_20.03.2012.sql');

COMMIT;