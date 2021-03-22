# test-task-alfa-bank

Для запуска приложения выполните:   
$ ./gradlew build && java -jar build/libs/test-task-alfa-bank-1.0-SNAPSHOT.jar 

Для проверки состояния приложения выполните: 
$ curl localhost:24043

Для получения ссылки на гифку выполните:    
$ curl -X GET "localhost:24043//currency?currency=EUR"

(Вместо EUR подставьте трёхзначный код интересующей Вас валюты).

Docker deploy
$ docker build -t such/test-task-alfa-bank:latest .

$ docker run -p 24043:24043 such/test-task-alfa-bank



