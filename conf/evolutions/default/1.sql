# --- !Ups
create table "people" (
  "id" bigint generated by default as identity(start with 1) not null primary key,
  "name" varchar not null,
  "surname" varchar not null
);

# --- !Downs

drop table "people" if exists;
