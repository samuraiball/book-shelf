DELETE FROM books;
DELETE FROM users;

INSERT INTO books(id, title, genre, description, is_active, is_deleted)
VALUES('550e8400-e29b-41d4-a716-446655440000' ,'WebAPI: The Good Part', 'IT', 'description', true, false);

insert into users(id, email, password, username, role)
values('550e8400-e29b-41d4-a716-446655440001','mohezi@example.com', '$2a$04$5bMt55qnhiaXIkHIduBFyuWS0ON45PcG5SU.wFXN8sHRU5hNfB7Nq', '辺野　茂辺地', 'ROLE_USER');
insert into users(id, email, password, username, role)
values('550e8400-e29b-41d4-a716-446655440002','moheko@example.com', '$2a$04$5bMt55qnhiaXIkHIduBFyuWS0ON45PcG5SU.wFXN8sHRU5hNfB7Nq', '辺野　もへ子', 'ROLE_ADMIN');
