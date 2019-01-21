drop table if exists books;
drop table if exists users;

create table
 users (id VARCHAR(36) primary key, email varchar(100), password varchar(100), username varchar(20), role varchar(20));

create table
  books(id varchar(36) primary key, title varchar(100), description varchar (300),genre varchar(50), url varchar(300),is_active boolean, is_deleted boolean);

insert into users(id, email, password, username, role) values('550e8400-e29b-41d4-a716-446655440001','mohezi@example.com', '$2a$04$5bMt55qnhiaXIkHIduBFyuWS0ON45PcG5SU.wFXN8sHRU5hNfB7Nq', '辺野　茂辺地', 'ROLE_USER');
insert into users(id, email, password, username, role) values('550e8400-e29b-41d4-a716-446655440002','moheko@example.com', '$2a$04$5bMt55qnhiaXIkHIduBFyuWS0ON45PcG5SU.wFXN8sHRU5hNfB7Nq', '辺野　もへ子', 'ROLE_ADMIN');


insert into books(id, title, genre, description, is_active, is_deleted)
values('550e8400-e29b-41d4-a716-446655440000' ,'webapi: the good part', 'it', 'description', true, false);
