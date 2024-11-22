-- テーブルが存在する場合は削除する
drop table if exists health_data;
drop table if exists evaluations;
DROP TABLE IF EXISTS users;
drop type if exists gender;
drop type if exists role;


create type gender as enum ('MAN','WOMAN');

create type role as enum ('ADMIM','USER');


create table users (
	id serial primary key,
	username varchar(50) not null,
	email varchar(100) not null,
	password_hash varchar(255) not null,
	authority role not null,
	created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
	birth_date Date not null,
	gender gender not null
);


CREATE TABLE health_data (
    id SERIAL PRIMARY KEY,
    user_id integer REFERENCES users(id) ON DELETE CASCADE,
    record_date DATE DEFAULT CURRENT_DATE,
    weight DECIMAL(5, 2),
    height DECIMAL(5, 2),
    age integer,
    body_fat_percentage integer,
    sBP integer,
    dBP integer,
    steps integer
    --sleep_start_time TIME,
    --sleep_end_time TIME,
);

create table evaluations(
	user_id integer primary key references users(id) on delete cascade,
	record_date date not null,
	total varchar not null,
	weight_and_fat varchar not null,
	blood_pressure varchar not null,
	steps varchar not null
);
	




















