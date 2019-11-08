INSERT INTO roles (name) values ('ROLE_USER');
INSERT INTO roles (name) values ('ROLE_ADMIN');
INSERT INTO roles (name) values ('ROLE_DBA');

INSERT INTO users (username, email, password) VALUES ('konrad', 'konrad@mail.com', 'butter');

insert into users_roles (user_id, roles_id) values (1,1);
insert into users_roles (user_id, roles_id) values (1,2);