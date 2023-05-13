create table usuarios(
id serial,
name varchar(50),
password varchar,
email varchar(100),
primary key (id));

create table carteira(
id serial,
name varchar(60),
amount numeric(10,2),
primary key (id));

create table usuarios_carteira(
id serial,
carteira integer,
usuarios integer,
primary key(id),
foreign key(usuarios) references usuarios(id),
foreign key(carteira) references carteira(id));

create table carteira_itens(
id serial,
carteira integer,
creates_at date,
type varchar(2),
description varchar(500),
amount numeric(10,2),
primary key(id),
foreign key(carteira) references carteira(id));

