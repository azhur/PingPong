 -- Create table account

 BEGIN;
 CREATE SEQUENCE account_seq INCREMENT 1 START 1;

 CREATE TABLE account (
     id INTEGER CONSTRAINT NN_id NOT NULL default nextval('account_seq'),
     discriminator CHARACTER VARYING(20) not null,
     email CHARACTER VARYING (30) not null,
     password CHARACTER VARYING (50) not null,
     salt CHARACTER VARYING (20) not null,
     enabled boolean not null default true,
     version TIMESTAMP CONSTRAINT NN_version NOT NULL default now(),
     CONSTRAINT pk_account_id PRIMARY KEY (id)
 );

 CREATE TABLE admin_account (
      id INTEGER CONSTRAINT NN_id NOT NULL,
      CONSTRAINT pk_admin_account_id PRIMARY KEY (id)
  );


 alter table admin_account add constraint admin_account_account
 FOREIGN KEY (id) REFERENCES account(id);

 CREATE TABLE player_account (
       id INTEGER NOT NULL,
       player_id INTEGER NOT NULL,
       CONSTRAINT pk_player_account_id PRIMARY KEY (id)
   );


  alter table player_account add constraint player_account_account
  FOREIGN KEY (id) REFERENCES account(id);

  alter table player_account add constraint player_account_player
    FOREIGN KEY (player_id) REFERENCES player(id) on update cascade on delete cascade;

    create unique index idx_player_account_player on player_account(player_id);

  CREATE SEQUENCE authority_seq INCREMENT 1 START 1;

  CREATE TABLE authority (
         id INTEGER NOT NULL default nextval('authority_seq'),
         account_id INTEGER not null,
         name character varying(20) not null,
         version TIMESTAMP CONSTRAINT NN_version NOT NULL default now(),
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