
create table line_items (
	id integer auto_increment,
	description varchar(255),
	amount decimal,
	primary key (id)
);

update schema_info set version = 1;
