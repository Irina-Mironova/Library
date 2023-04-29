create table authors
(
    author_id   bigserial not null primary key,
    author_name varchar(255) not null,
    description varchar(255),
    created_at  timestamp default current_timestamp,
    updated_at  timestamp default current_timestamp
);

create table readers
(
    reader_id   bigserial not null primary key,
    reader_name varchar(150) not null,
    dt_birth    timestamp null,
    address     varchar(255),
    phone       varchar(25),
    created_at  timestamp default current_timestamp,
    updated_at  timestamp default current_timestamp
);


create table books
(
    book_id       bigserial not null primary key,
    author_id     bigint not null,
    title         varchar(100),
    year_publish  int,
    amount        int not null,
    created_at    timestamp default current_timestamp,
    updated_at    timestamp default current_timestamp,
    CONSTRAINT books_fk_author_id FOREIGN KEY(author_id) REFERENCES authors(author_id) on delete cascade
);

create table books_readers(
    id bigserial not null primary key,
    book_id bigint not null,
    reader_id bigint not null,
    dt_borrow timestamp default current_timestamp,
    dt_return timestamp null,
    CONSTRAINT br_fk_book_id FOREIGN KEY(book_id) REFERENCES books(book_id) on delete cascade,
    CONSTRAINT br_fk_reader_id FOREIGN KEY(reader_id) REFERENCES readers(reader_id) on delete cascade
);

insert into authors (author_name, description)
values ('Пушкин Александр Сергеевич', 'русский поэт, драматург и прозаик'),
       ('Толстой Лев Николаевич', 'один из наиболее известных русских писателей и мыслителей,' ||
        'один из величайших в мире писателей-романистов'),
       ('Тургенев Иван Сергеевич', 'усский писатель-реалист, поэт, публицист, драматург, прозаик ' ||
        'и переводчик'),
       ('Зощенко Михаил Михайлович', 'советский писатель, драматург, сценарист и переводчик'),
       ('Лермонтов Михаил Юрьевич', 'русский поэт, прозаик, драматург, художник. Поручик лейб-гвардии ' ||
        'Гусарского полка.');

insert into books (author_id, title, year_publish, amount)
values (1, 'Повести покойного Ивана Петровича Белкина', 2019, 5),
       (1, 'Евгений Онегин', 2018, 2),
       (2, 'Анна Каренина', 2005, 4),
       (2, 'Воскресение', 2007, 2),
       (3, 'Отцы и дети', 2003, 2),
       (3, 'Накануне', 1997, 1),
       (4, 'Избранное', 1995, 3),
       (5, 'Герой нашего времени', 1998, 6),
       (5, 'Мцыри.Стихотворения', 1997, 3);

insert into readers (reader_name, dt_birth, address, phone)
values ('Иванов Александр Петрович', CAST(CAST('1982-03-20' AS date) as DATE), 'Москва, ул.Краноармейская,5',
         '+79324256789'),
       ('Петров Алексей Павлович', CAST(CAST('2000-01-02' AS date) as DATE), 'Москва, ул.Гроздненская,25,' ||
        ' кв. 27', '+79454256789'),
       ('Сидоров Петр Петрович', CAST(CAST('2004-12-12' AS date) as DATE), 'Москва, ул.Левитана,8',
       '+79370002789'),
       ('Павлова Ольга Сергеевна', CAST(CAST('1999-04-11' AS date) as DATE), 'Москва, ул.Львова,16, кв. 15',
       '+79324254875'),
       ('Ишкина Елена Сергеевна', CAST(CAST('1998-05-24' AS date) as DATE), 'Москва, ул.Ленина,18, кв. 7',
        '+79324257455');

insert into books_readers (book_id, reader_id)
values (1,1),
       (1,2),
       (2,3),
       (2,1),
       (3,4);