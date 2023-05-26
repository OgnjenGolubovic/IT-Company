insert into roles (name) values ('ROLE_SOFTWARE_ENGINEER');
insert into roles (name) values ('ROLE_HUMAN_RESOURCES');
insert into roles (name) values ('ROLE_PROJECT_MANAGER');
insert into roles (name) values ('ROLE_ADMINISTRATOR');

-- Lozinka za sve user-e je 123
insert into users (user_type, username, password, name, surname, gender, jmbg, phone_number, enabled, tfa) values (1,'juzer@gmail.com','$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra', 'Gojko', 'Mrmot', 1, 123123123, '18921892', true, false);
insert into users (user_type, username, password, name, surname, gender, jmbg, phone_number, enabled, tfa) values (2,'asdf@gmail.com','$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra', 'Petar', 'Trs', 1, 1223131, '2313211', true, false);
insert into users (user_type, username, password, name, surname, gender, jmbg, phone_number, enabled, tfa) values (3,'juzer3@gmail.com','$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra', 'Negovan', 'Radicic', 1, 121123123, '18921892', true, false);
insert into users (user_type, username, password, name, surname, gender, jmbg, phone_number, enabled, tfa) values (4, 'juzer4@gmail.com','$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra', 'Mil', 'Mar', 0, 321124412, '746352332', true, false);

INSERT INTO USER_ROLE (user_id, role_id) values (1, 1); 
INSERT INTO USER_ROLE (user_id, role_id) values (2, 2);
INSERT INTO USER_ROLE (user_id, role_id) values (3, 3);
INSERT INTO USER_ROLE (user_id, role_id) values (4, 4);

insert into permissions (name) values ('USERS_C');
insert into permissions (name) values ('USERS_R');
insert into permissions (name) values ('USERS_U');
insert into permissions (name) values ('USERS_D');

insert into role_permission (role_id, permission_id) values (1, 2)
insert into role_permission (role_id, permission_id) values (2, 2)
insert into role_permission (role_id, permission_id) values (3, 2)
insert into role_permission (role_id, permission_id) values (4, 2)