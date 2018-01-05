CREATE TABLE Document(
  id INTEGER,
  name VARCHAR(255),
  token_values VARCHAR(4000),
  version INTEGER
);

CREATE TABLE Token(
  id INTEGER,
  name VARCHAR(255)
);

CREATE TABLE document_token(
  id INTEGER,
  document_id INTEGER,
  token_id INTEGER
);
