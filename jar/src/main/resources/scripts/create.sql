create table IF NOT EXISTS USER(
	id bigint auto_increment PRIMARY KEY,
	name varchar(25) ,
	password varchar(25)
);
commit;

		merge into user key(id,name,password) values (1,'user','user');
		merge into user key(id,name,password) values (2,'admin','admin');
commit;


create table IF NOT EXISTS SNIPPET(
	id bigint auto_increment PRIMARY KEY,
	username SMALLINT ,
	text CLOB
);
commit;

create table IF NOT EXISTS BOOK(
	id bigint auto_increment PRIMARY KEY,
	title varchar(25) ,
	isbn varchar(25),
	yearEdition int,
	creationDate date
);
commit;

create table IF NOT EXISTS AUTHOR(
	id bigint auto_increment PRIMARY KEY,
	name varchar(25) ,
	book bigint,
	 FOREIGN KEY (book) REFERENCES BOOK(id)
);
commit;
