 -- Add photo photo album

 BEGIN;

  CREATE SEQUENCE photo_album_seq INCREMENT 1 START 1;
  CREATE SEQUENCE photo_seq INCREMENT 1 START 1;

  CREATE TABLE photo_album (
   id id NOT NULL default nextval('photo_album_seq'),
   version version NOT NULL default NOW(),
   creator_id id NOT NULL,
   last_updated_by_id id not null,
   cover_id id,
   name CHARACTER VARYING (255) not null,
   description CHARACTER VARYING (255),
   creation_date timestamp without time zone not null,
   last_updated_date timestamp without time zone not null,
   PRIMARY KEY (id)
 );


 CREATE TABLE photo (
   id id NOT NULL default nextval('photo_seq'),
   version version NOT NULL default NOW(),
   uploader_id id NOT NULL,
   photo_album_id id not null,
   name CHARACTER VARYING (255) not null,
   description CHARACTER VARYING (255),
   uploading_date timestamp without time zone not null,
   content_type CHARACTER VARYING (255) not null,
   url CHARACTER VARYING (255) not null,
   PRIMARY KEY (id)
 );

  CREATE INDEX idx_photo_album_creator ON photo_album(creator_id);
  CREATE INDEX idx_photo_album_last_updated_by ON photo_album(last_updated_by_id);
  CREATE INDEX idx_photo_album_cover ON photo_album(cover_id);

  CREATE INDEX idx_photo_uploader ON photo(uploader_id);
  CREATE INDEX idx_photo_photo_album ON photo(photo_album_id);

  alter table photo_album add constraint photo_album_account1
       FOREIGN KEY (creator_id) REFERENCES account(id);
  alter table photo_album add constraint photo_album_account2
       FOREIGN KEY (last_updated_by_id) REFERENCES account(id);
  alter table photo_album add constraint photo_album_photo
       FOREIGN KEY (cover_id) REFERENCES photo(id);

  alter table photo add constraint photo_account
       FOREIGN KEY (uploader_id) REFERENCES account(id);
  alter table photo add constraint photo_photo_album
       FOREIGN KEY (photo_album_id) REFERENCES photo_album(id);


 INSERT INTO execution_history (name) VALUES ('009_17.09.2012.sql');

COMMIT;