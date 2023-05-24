insert into roles (name) values ('ROLE_SOFTWARE_ENGINEER');
insert into roles (name) values ('ROLE_HUMAN_RESOURCES');
insert into roles (name) values ('ROLE_PROJECT_MANAGER');
insert into roles (name) values ('ROLE_ADMINISTRATOR');

-- Lozinka za sve user-e je 123
insert into users (user_type, username, password, name, surname, phone_number, enabled) values (1,'juzer@gmail.com','$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra', 'Gojko', 'Mrmot', '18921892', true);
insert into users (user_type, username, password, name, surname, phone_number, enabled) values (2,'asdf@gmail.com','$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra', 'Petar', 'Trs', '2313211', true);
insert into users (user_type, username, password, name, surname, phone_number, enabled) values (3,'juzer3@gmail.com','$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra', 'Negovan', 'Radicic', '18921892', true);
insert into users (user_type, username, password, name, surname, phone_number, enabled) values (4, 'juzer4@gmail.com','$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra', 'Mil', 'Mar', '746352332', true);

INSERT INTO USER_ROLE (user_id, role_id) values (1, 1); 
INSERT INTO USER_ROLE (user_id, role_id) values (2, 2);
INSERT INTO USER_ROLE (user_id, role_id) values (3, 3);
INSERT INTO USER_ROLE (user_id, role_id) values (4, 4);

insert into registration_requests (request, email, name, surname, company_role) values (1, 'proba1@gmail.com', 'Milos', 'Milosevic', 2)
insert into registration_requests (request, email, name, surname, company_role) values (2, 'proba2@gmail.com', 'Marko', 'Markovic', 3)
insert into registration_requests (request, email, name, surname, company_role) values (3, 'proba3@gmail.com', 'Nenad', 'Nenadovic', 1)
