DROP TABLE IF EXISTS phones;

CREATE TABLE phones (
  id INTEGER,
  country_code VARCHAR NOT NULL,
  area_code VARCHAR NOT NULL,
  number VARCHAR NOT NULL,
  PRIMARY KEY(id)
);
