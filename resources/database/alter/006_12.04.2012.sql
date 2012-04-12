 -- Forgot password implementation

 BEGIN;

 CREATE TABLE forgot_password (
   id varchar(128) NOT NULL,
   version version NOT NULL default NOW(),
   valid_till timestamp without time zone NOT NULL,
   account_id id NOT NULL,
   PRIMARY KEY (id)
 );

CREATE INDEX idx_forgot_password_account ON forgot_password(account_id);

 INSERT INTO execution_history (name) VALUES ('006_12.04.2012.sql');

COMMIT;