DROP TABLE books IF EXISTS;

CREATE TABLE
  books(id VARCHAR(36) PRIMARY KEY, title VARCHAR(100), description VARCHAR (300),genre VARCHAR(50), is_active BOOLEAN, is_deleted BOOLEAN);