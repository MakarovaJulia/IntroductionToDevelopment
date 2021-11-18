create table file_info
(
    id serial not null
        constraint file_info_pk
            primary key,
    original_file_name varchar(100),
    storage_file_name varchar(100) not null,
    size bigint not null,
    type varchar(100)
);

create table users
(
    id serial not null
        constraint users_pk
            primary key,
    first_name varchar(20),
    last_name varchar(20),
    age integer,
    password_hash varchar(100) not null,
    email varchar(100) not null unique,
    avatar_id integer
        constraint users_file_info_id_fk
            references file_info
);

create table books
(
    id serial primary key ,
    author_id int not null references users,
    created_at timestamp not null,
    title varchar(255) not null,
    description varchar(5000) not null,
    cover_id int
);

create table chapters
(
    id serial primary key ,
    book_id int not null references books,
    number integer,
    created_at timestamp not null,
    title varchar(255) not null,
    content varchar(40000) not null
);

create table if not exists books_chapters
(
    book_id integer not null references books,
    chapter_id integer not null references chapters
);


create table if not exists file_info
(
    id serial not null primary key,
    original_file_name varchar(100),
    storage_file_name varchar(100) not null,
    size bigint not null,
    type varchar(100)
);

create table if not exists users
(
    id serial not null primary key,
    first_name varchar(20),
    last_name varchar(20),
    age integer,
    password_hash varchar(100) not null,
    email varchar(100) not null unique,
    avatar_id integer references file_info
);

create table if not exists posts
(
    id serial primary key,
    author_id int not null references users,
    created_at timestamp not null,
    content varchar(1000) not null
);

create table if not exists users_books
(
    user_id integer not null references users,
    book_id integer not null references books
);

create table if not exists comments
(
    id serial primary key,
    author_id int not null references users,
    created_at timestamp not null,
    content varchar(1000) not null
);

alter table users add column back_photo_id integer;
alter table books add column is_published boolean;