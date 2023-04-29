Library
Это проект, который предоставляет систему управления библиотекой. Он использует базу данных PostgreSQL для хранения данных, а база данных разворачивается с помощью Docker Compose. API проекта документируется с помощью Swagger. 

Зависимости
Для работы проекта необходимы следующие зависимости:
•	Java 11 или выше
•	Maven
•	Docker Compose

Начало работы
1.	Клонируйте репозиторий на свой локальный компьютер с github.com по ссылке https://github.com/Irina-Mironova/Library.git.
2.	Запустите Docker. Перейдите в директорию, где хранится файл  docker-compose.yml,  откройте окно PowerShell и выполните команду docker-compose up, чтобы запустить базу данных PostgreSQL libraryDB.
3.	Запустите проект в IntelliJ IDEA (метод main в классе HabrApplication).
4.	Документация API находится на странице Swagger UI. Вы можете открыть страницу Swagger UI, введя URL-адрес веб-приложения  http://localhost:8189/library/swagger-ui/index.html

Использование
Проект Library предоставляет следующие конечные точки (endpoints):
- Работа с авторами:
•	api/v1/authors/update - PUT - Запрос на обновление данных об авторе
•	api/v1/authors/new  - POST - Запрос на добавление нового автора
•	api/v1/authors - GET - Запрос на вывод всех авторов постранично
•	api/v1/authors/{id} - GET - Запрос на получение автора по id
•	api/v1/authors/{id}- DELETE - Запрос на удаление автора по id
•	api/v1/authors/name_page- GET - Запрос на поиск авторов по фамилии с постраничным выводом.
•	api/v1/authors/name- GET - Запрос на поиск авторов по фамилии
- Работа с книгами:
•	api/v1/books/update - PUT - Запрос на обновление данных о книге
•	api/v1/books/new  - POST - Запрос на добавление новой книги
•	api/v1/books - GET - Запрос на вывод всех книг постранично
•	api/v1/books/{id} - GET - Запрос на получение книги по id
•	api/v1/books/{id}- DELETE - Запрос на удаление книги по id
•	api/v1/books/filter_page - POST - Запрос на поиск книг по автору и названию с постраничным выводом.
- Работа с читателями:
•	api/v1/readers/update - PUT - Запрос на обновление данных о читателе
•	api/v1/readers/new  - POST - Запрос на добавление нового читателя
•	api/v1/readers - GET - Запрос на вывод всех читателей постранично
•	api/v1/readers/{id} - GET - Запрос на получение читателя по id
•	api/v1/readers/{id} - DELETE - Запрос на удаление читателя по id
•	api/v1/readers/name_page - GET - Запрос на поиск читателя по фамилии с постраничным выводом 
•	api/v1/readers/name - GET - Запрос на поиск читателей по фамилии
- Работа с выдачей книг:
•	api/v1/borrow_book/take - GET - Запрос на выдачу книги читателю 
•	api/v1/borrow_book/give  - GET - Запрос на возврат книги в библиотеку

Конфигурация
Информация для подключения к базе данных хранится в файле application.properties. Вы можете изменить этот файл, чтобы изменить детали подключения к базе данных при необходимости.

