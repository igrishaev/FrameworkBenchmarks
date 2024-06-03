
create user benchmarkdbuser with password 'benchmarkdbpass';

create database tfb with owner benchmarkdbuser;

\connect tfb;

create table WORLD (id int primary key, randomnumber int not null);

truncate WORLD;

insert into WORLD
select
    x,
    (random() * 9999)::int
from
    generate_series(0, 9999) as gen(x);
