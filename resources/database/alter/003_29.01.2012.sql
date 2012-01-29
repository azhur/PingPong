 -- Create table account

 BEGIN;
 CREATE SEQUENCE account_seq INCREMENT 1 START 1;


 CREATE TABLE account (
     id INTEGER CONSTRAINT NN_id NOT NULL default nextval('account_seq'),

     version TIMESTAMP CONSTRAINT NN_version NOT NULL default now(),
     CONSTRAINT id PRIMARY KEY (id)
 );


 INSERT INTO execution_history (name) VALUES ('003_29.01.2012.sql');

commit;