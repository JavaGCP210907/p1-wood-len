CREATE TABLE roles(
	role_id serial PRIMARY KEY,
	role_name TEXT UNIQUE
);

CREATE TABLE users(
	user_id serial PRIMARY KEY,
	role_id_fk int REFERENCES roles(role_id),
	username TEXT UNIQUE,
	password TEXT,
	first_name TEXT,
	last_name TEXT,
	email TEXT UNIQUE
);

CREATE TABLE statuses(
	status_id serial PRIMARY KEY,
	status_name TEXT UNIQUE
);

CREATE TABLE types(
	type_id serial PRIMARY KEY,
	type_name TEXT UNIQUE
);

CREATE TABLE reimbursements(
	reimbursment_id serial PRIMARY KEY,
	status_id_fk int REFERENCES statuses(status_id),
	type_id_fk int REFERENCES types(type_id),
	user_id_fk int REFERENCES users(user_id),
	resolver_id_fk int REFERENCES users(user_id),
	amount NUMERIC,
	submit_date timestamp,
	resolved_date timestamp,
	description TEXT
);

INSERT INTO roles(role_name)
VALUES ('employee'), ('manager');

INSERT INTO statuses(status_name)
VALUES ('pending'), ('approved'), ('denied');
SELECT * FROM statuses;

INSERT INTO types(type_name)
VALUES ('lodging'), ('travel'), ('food'), ('other');
SELECT * FROM types;

SELECT * FROM users;
SELECT * FROM reimbursements;

Select * From users; Where username = 'username' AND password = 'password';
INSERT INTO users (role_id_fk, username, PASSWORD, first_name, last_name, email)
VALUES (2, 'manager', 'password', 'Mr', 'Bossman', 'test2@gmail.com');
TRUNCATE TABLE reimbursements;
TRUNCATE TABLE reimbursements, users;
--SELECT * FROM reimbursements;
--SELECT * FROM TYPES;
--SELECT * FROM statuses;
--DELETE FROM reimbursements;