 -- Create table account

 BEGIN;
 CREATE SEQUENCE account_seq INCREMENT 1 START 1;

 CREATE TABLE account (
     id id NOT NULL default nextval('account_seq'),
     discriminator CHARACTER VARYING(20) not null,
     email email not null,
     password password not null,
     salt CHARACTER VARYING (20) not null,
     enabled boolean not null default true,
     version version NOT NULL default now(),
     CONSTRAINT pk_account_id PRIMARY KEY (id)
 );

 CREATE TABLE admin_account (
      id id NOT NULL,
      CONSTRAINT pk_admin_account_id PRIMARY KEY (id)
  );


 alter table admin_account add constraint admin_account_account
 FOREIGN KEY (id) REFERENCES account(id);


 insert into account(id, discriminator, email, password, salt, enabled)
      values(0, 'ADMIN_ACCOUNT', 'artur.zhurat@gmail.com', md5('111111'), '', TRUE);
 insert into admin_account values (0);
 insert into authority(id, account_id, name) values (0, 0, 'ROLE_ADMIN_USER');


 CREATE TABLE player_account (
       id id NOT NULL,
       player_id id NOT NULL,
       CONSTRAINT pk_player_account_id PRIMARY KEY (id)
   );


  alter table player_account add constraint player_account_account
  FOREIGN KEY (id) REFERENCES account(id);

  alter table player_account add constraint player_account_player
    FOREIGN KEY (player_id) REFERENCES player(id) on update cascade on delete cascade;

    create unique index idx_player_account_player on player_account(player_id);

  CREATE SEQUENCE authority_seq INCREMENT 1 START 1;

  CREATE TABLE authority (
         id id NOT NULL default nextval('authority_seq'),
         account_id id not null,
         name character varying(20) not null,
         version version NOT NULL default now(),
         CONSTRAINT pk_authority_id PRIMARY KEY (id)
     );

  alter table authority add constraint authority_account
      FOREIGN KEY (account_id) REFERENCES account(id) on update cascade on delete cascade;

  create unique index idx_authority_account_name on authority(account_id, name);

  alter table player drop column email;
  alter table player drop column login;
  alter table player drop column password;

  update player set birth = '1988-11-20';
  alter table player alter column birth set not null;

  alter table player add column status character varying (15);
  update player set status = 'REGISTRATION';
  alter table player alter column status set not null;

 INSERT INTO execution_history (name) VALUES ('003_29.01.2012.sql');

commit;