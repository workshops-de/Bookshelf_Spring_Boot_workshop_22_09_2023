drop table if exists book cascade;
create table book (id bigserial not null, author varchar(255), description varchar(255), isbn varchar(255), title varchar(255), primary key (id));
