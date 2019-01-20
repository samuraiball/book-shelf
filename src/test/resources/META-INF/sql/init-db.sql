drop table if exists books;
drop table if exists users;

create table
 users (id int primary key auto_increment, email varchar(100), password varchar(100), name varchar(20), role varchar(20));

create table
  books(id varchar(36) primary key, title varchar(100), description varchar (300),genre varchar(50), is_active boolean, is_deleted boolean);

insert into users(email, password, name, role)
values('mohezi@example.com', '$2a$04$5bMt55qnhiaXIkHIduBFyuWS0ON45PcG5SU.wFXN8sHRU5hNfB7Nq', '辺野　茂辺地', 'ROLE_USER');
insert into users(email, password, name, role)
values('moheko@example.com', '$2a$04$5bMt55qnhiaXIkHIduBFyuWS0ON45PcG5SU.wFXN8sHRU5hNfB7Nq', '辺野　もへ子', 'ROLE_ADMIN');
