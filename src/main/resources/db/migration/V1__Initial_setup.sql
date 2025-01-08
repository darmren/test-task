CREATE TABLE IF NOT EXISTS departments
(
    id serial PRIMARY KEY,
    name text
);

INSERT INTO departments (name) VALUES ('OIT');
INSERT INTO departments (name) VALUES ('OAR');
INSERT INTO departments (name) VALUES ('OEI');
INSERT INTO departments (name) VALUES ('OCB');
INSERT INTO departments (name) VALUES ('ОЕЕ');
INSERT INTO departments (name) VALUES ('OG');
INSERT INTO departments (name) VALUES ('OND');
INSERT INTO departments (name) VALUES ('OCI');
INSERT INTO departments (name) VALUES ('OEF');
INSERT INTO departments (name) VALUES ('OMMF');

CREATE TABLE IF NOT EXISTS groups
(
    id serial PRIMARY KEY,
    name text NOT NULL,
    department_id integer NOT NULL REFERENCES departments(id),
    entering_year integer NOT NULL
);

INSERT INTO groups(name, department_id, entering_year) VALUES ('8K23', 1, 2022);
INSERT INTO groups(name, department_id, entering_year) VALUES ('8K13', 1, 2021);

CREATE TABLE IF NOT EXISTS students
(
    id serial PRIMARY KEY,
    surname character varying(255) NOT NULL,
    name character varying(255) NOT NULL,
    patronymic character varying(255),
    group_id integer REFERENCES groups(id) NOT NULL,
    birthday timestamp(6) without time zone NOT NULL,
    sex character varying(255) NOT NULL,
    status character varying(255) NOT NULL
);

--SELECT * FROM flyway_schema_history;