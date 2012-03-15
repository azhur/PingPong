 -- Added indexes

 BEGIN;
 create unique index idx_account_email on account(email);
 create unique index idx_execution_history_name on execution_history(name);


 INSERT INTO execution_history (name) VALUES ('004_14.03.2012.sql');

commit;