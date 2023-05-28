insert into roles (name) values ('ROLE_SOFTWARE_ENGINEER');
insert into roles (name) values ('ROLE_HUMAN_RESOURCES');
insert into roles (name) values ('ROLE_PROJECT_MANAGER');
insert into roles (name) values ('ROLE_ADMINISTRATOR');

-- Lozinka za sve user-e je 123
insert into users (user_type, username, password, name, surname, phone_number, enabled, tfa, company_role) values (1,'juzer@gmail.com','$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra', 'Gojko', 'Mrmot', '18921892', true, false, 1);
insert into users (user_type, username, password, name, surname, phone_number, enabled, tfa, company_role) values (2,'asdf@gmail.com','$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra', 'Petar', 'Trs', '2313211', true, false, 2);
insert into users (user_type, username, password, name, surname, phone_number, enabled, tfa, company_role) values (3,'juzer3@gmail.com','$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra', 'Negovan', 'Radicic', '18921892', true, false, 3);
insert into users (user_type, username, password, name, surname, phone_number, enabled, tfa, company_role) values (4, 'juzer4@gmail.com','$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra', 'Mil', 'Mar', '746352332', true, false, 4);
insert into users (user_type, username, password, name, surname, phone_number, enabled, tfa, company_role) values (1,'a@gmail.com','$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra', 'Marko', 'Markovic', '+3816530493', true, false, 1);
insert into users (user_type, username, password, name, surname, phone_number, enabled, tfa, company_role) values (1,'b@gmail.com','$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra', 'Nikola', 'Nikolic', '+3804342921', true, false, 1);


INSERT INTO USER_ROLE (user_id, role_id) values (1, 1); 
INSERT INTO USER_ROLE (user_id, role_id) values (2, 2);
INSERT INTO USER_ROLE (user_id, role_id) values (3, 3);
INSERT INTO USER_ROLE (user_id, role_id) values (4, 4);
INSERT INTO USER_ROLE (user_id, role_id) values (5, 1);
INSERT INTO USER_ROLE (user_id, role_id) values (6, 1);

insert into projects (name, duration) values ('First Project', '2 months')
insert into projects (name, duration) values ('Second Project', '3 months')
insert into projects (name, duration) values ('Third Project', '4 months')

insert into se_project (project_id, software_engineer_id, job_description) values (1, 1, 'Prvi opis posla');
insert into se_project (project_id, software_engineer_id, job_description) values (2, 1, 'Drugi opis posla');
insert into se_project (project_id, software_engineer_id, job_description) values (3, 5, 'Treci opis posla');
insert into se_project (project_id, software_engineer_id, job_description) values (3, 6, 'Cetvrti opis posla');

insert into permissions (name) values ('USERS_C');
insert into permissions (name) values ('USERS_R');
insert into permissions (name) values ('USERS_U');
insert into permissions (name) values ('USERS_D');

insert into role_permission (role_id, permission_id) values (1, 2)
insert into role_permission (role_id, permission_id) values (2, 2)
insert into role_permission (role_id, permission_id) values (3, 2)
insert into role_permission (role_id, permission_id) values (4, 2)

insert into registration_requests (email, name, surname, company_role, status) values ('proba1@gmail.com', 'Milos', 'Milosevic', 2, 1)
insert into registration_requests (email, name, surname, company_role, status) values ('proba2@gmail.com', 'Marko', 'Markovic', 3, 2)
insert into registration_requests (email, name, surname, company_role, status) values ('proba3@gmail.com', 'Nenad', 'Nenadovic', 1, 0)
