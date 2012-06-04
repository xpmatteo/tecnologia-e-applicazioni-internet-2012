
create table receipts (
	id integer auto_increment,
	minutes integer,
	created_at datetime,
	primary key (id)
);

update schema_info set version = 1;
