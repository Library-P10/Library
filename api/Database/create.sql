create table author
(
    id         int auto_increment
        primary key,
    first_name varchar(50) not null,
    last_name  varchar(50) not null
);

create table categorie
(
    id    int auto_increment
        primary key,
    label varchar(25) not null
);

create table book
(
    id           int          not null
        primary key,
    title        varchar(100) not null,
    pub_date     date         null,
    page         int          not null,
    synopsis     text         not null,
    cover        varchar(200) not null,
    author_id    int          not null,
    categorie_id int          not null,
    constraint author_book_fk
        foreign key (author_id) references author (id),
    constraint categorie_book_fk
        foreign key (categorie_id) references categorie (id)
);

create table customer
(
    id          int auto_increment
        primary key,
    first_name  varchar(50)  not null,
    last_name   varchar(50)  not null,
    adress      varchar(100) not null,
    postal_code varchar(6)   not null,
    city        varchar(50)  not null,
    email       varchar(100) not null,
    password    varchar(200) not null
);

create table library
(
    id        int auto_increment
        primary key,
    nom       varchar(100) not null,
    adress    varchar(100) not null,
    phone_num varchar(20)  not null,
    email     varchar(100) not null
);

create table copy
(
    id         int auto_increment
        primary key,
    format     varchar(20) not null,
    book_id    int         not null,
    library_id int         not null,
    status     varchar(20) not null,
    constraint book_copy_fk
        foreign key (book_id) references book (id),
    constraint library_copy_fk
        foreign key (library_id) references library (id)
);

create table emprunt
(
    id           int auto_increment
        primary key,
    emprunt_date date                 not null,
    return_date  date                 not null,
    is_extended  tinyint(1) default 0 not null,
    customer_id  int                  not null,
    copy_id      int                  not null,
    constraint copy_emprunt_fk
        foreign key (copy_id) references copy (id),
    constraint customer_emprunt_fk
        foreign key (customer_id) references customer (id)
);

