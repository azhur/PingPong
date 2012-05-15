 -- Tournament implementation

 BEGIN;

CREATE TABLE player_tournament
(
  player_id id NOT NULL,
  tournament_id id NOT NULL,
  CONSTRAINT pk_player_tournament PRIMARY KEY (player_id , tournament_id ),
  CONSTRAINT player_tournament_player FOREIGN KEY (player_id)
      REFERENCES player (id) MATCH SIMPLE
      ON UPDATE CASCADE ON DELETE RESTRICT,
  CONSTRAINT player_tournament_tournament FOREIGN KEY (tournament_id)
      REFERENCES tournament (id) MATCH SIMPLE
      ON UPDATE CASCADE ON DELETE RESTRICT
);

 INSERT INTO execution_history (name) VALUES ('008_15.05.2012.sql');

COMMIT;