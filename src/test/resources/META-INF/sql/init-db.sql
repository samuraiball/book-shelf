DROP TABLE books IF EXISTS;

CREATE TABLE
  books(id VARCHAR(36) PRIMARY KEY, title VARCHAR(50), genre VARCHAR(50), is_active BOOLEAN);