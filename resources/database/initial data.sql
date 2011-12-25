begin;
INSERT INTO player VALUES (nextval('player_seq'),'root','root','admin@mail.com','root','root','2010-10-10',true,'M',now());
INSERT INTO role VALUES (nextval('role_seq'),'A');
INSERT INTO role VALUES (nextval('role_seq'),'P');
INSERT INTO role_player VALUES (currval('player_seq'),(SELECT role_id FROM role WHERE name='A'));
commit;