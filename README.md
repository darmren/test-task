# Инструкция по запуску

## Настройка БД

Для запуска проекта потребуется PostgreSQL 15.7.

1. Создайте для пользователя `postgres` базу `test-task`.
2. Запустите в окне запросов следующий SQL-скрипт:

```sql
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
```

## Настройка проекта

1. Клонируйте проект:
   ```bash
   git clone https://github.com/darmren/test-task
   ```

2. В файле `application.properties` замените свойства:
   ```properties
   spring.datasource.username=Ваше_имя_пользователя
   spring.datasource.password=Ваш_пароль
   ```
   на Ваши имя пользователя и пароль в настроенной ранее базе данных.

3. Может также потребоваться заменить порт на свободный или освободить текущий.

## Запуск приложения

Запустите `TaskApplication`.

## Тестирование эндпоинтов

Тестировать эндпоинты можно с помощью Postman по следующему URL:
```
http://localhost:9090/api/students/
```

Или просмотреть документацию API с помощью Swagger:
```
http://localhost:9090/swagger-ui/index.html
```
