
create table receipts (
	id integer auto_increment,
	descripiton varchar(255),
	amount decimal,
	primary key (id)
);

update schema_info set version = 1;
