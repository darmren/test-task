# Инструкция по запуску

## Запуск проекта с помощью командной строки

1. Клонируйте проект:
   ```bash
   git clone https://github.com/darmren/test-task
   ```

2. Смениите текущую директорию на папку проекта:
   ```bash
   cd test-task
   ```

3. Может также потребоваться установить JDK:
   ```bash
    sudo apt install openjdk-17-jre-headless
   ```
4. Сделайте файл Maven исполняемым:
   ```bash
   chmod +x mvnw
   ```
5. Запустите Docker-контйенер с базой данных:
    ```bash
    sudo docker-compose up -d
    ```
6. Выполните следующую команду, которая собирает проект и запускает получившийся jar-файл:
   ```bash
   ./mvnw package && java -jar target/task-0.0.1-SNAPSHOT.jar
   ```

## Тестирование эндпоинтов

Тестировать эндпоинты можно с помощью Postman по следующему URL:
```
http://localhost:9090/api/students/
```

Или просмотреть документацию API с помощью Swagger:
```
http://localhost:9090/swagger-ui/index.html
```
