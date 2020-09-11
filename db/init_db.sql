CREATE EXTENSION "uuid-ossp";

CREATE OR REPLACE FUNCTION uuid_generate_v4()
  RETURNS UUID AS
'$libdir/uuid-ossp', 'uuid_generate_v4'
LANGUAGE C
VOLATILE
STRICT
COST 1;
ALTER FUNCTION uuid_generate_v4()
OWNER TO postgres;

-- auto-generated definition
create table muscle_group
(
    unique_id bigserial not null
        constraint muscle_group_pkey
            primary key,
    name      varchar(255),
    uuid      uuid
);

alter table muscle_group
    owner to postgres;

-- auto-generated definition
create table workout_type
(
    unique_id bigserial not null
        constraint workout_type_pkey
            primary key,
    name      varchar(255),
    uuid      uuid
);

alter table workout_type
    owner to postgres;

-- auto-generated definition
create table role
(
    unique_id bigserial not null
        constraint role_pkey
            primary key,
    name      varchar(255),
    uuid      uuid
);

alter table role
    owner to postgres;



insert into muscle_group (name, uuid) values ('Chest', '3a5f013e-cdb6-4c57-a532-17b41ac46e54');
insert into muscle_group (name, uuid) values ('Back', '96c8a4b1-e7b6-4531-81e5-3688bf6e59df');
insert into muscle_group (name, uuid) values ('Arms', '4c198535-fb59-4d1c-a2d1-a10830f7f8ef');
insert into muscle_group (name, uuid) values ('Abdominals', '85026218-fa2d-40fc-a23a-9f884f5d4ff5');
insert into muscle_group (name, uuid) values ('Legs', '2e103951-49e3-4180-99b0-812254ede3ff');
insert into muscle_group (name, uuid) values ('Shoulders', 'e9a201e3-4132-456d-9991-3cd95ff7a647');
insert into muscle_group (name, uuid) values ('Calves (lower leg)', '530ddb54-db79-4bc8-83a8-069a90851753');
insert into muscle_group (name, uuid) values ('Hamstrings (back of upper leg)', '99639cc7-76d5-41dc-b596-cad0c24ab4a5');
insert into muscle_group (name, uuid) values ('Quadriceps (front of upper leg)', '07ef59aa-b40c-4841-ba53-960b45e48cbe');
insert into muscle_group (name, uuid) values ('Glutes (butt and hips)', 'c79d2c75-5218-472b-a7d8-32bb8a4476e9');
insert into muscle_group (name, uuid) values ('Biceps (front of upper arms)', '3bd95a79-36f5-4aa7-a95f-3f1865ab5848');
insert into muscle_group (name, uuid) values ('Triceps (back of upper arms)', 'e100bdda-2206-4918-acb1-dd755b2f88dd');
insert into muscle_group (name, uuid) values ('Forearms (lower arm)', '823e139c-eb43-4471-b0b2-9f04229e99bf');
insert into muscle_group (name, uuid) values ('Trapezius (Traps) (top of shoulders)', '1347f4e4-d3c4-4d07-9205-d3cc53f4278b');
insert into muscle_group (name, uuid) values ('Latissimus Dorsi (Lats) (under the armpits)', 'ae9113f9-c6a6-41fa-8adf-31b2ac08218f');

insert into workout_type (name, uuid) values ('Regular', uuid_generate_v4());
insert into workout_type (name, uuid) values ('Stretching', uuid_generate_v4());
insert into workout_type (name, uuid) values ('HIIT', uuid_generate_v4());
insert into workout_type (name, uuid) values ('Tabata', uuid_generate_v4());
insert into workout_type (name, uuid) values ('Cardio', uuid_generate_v4());
insert into workout_type (name, uuid) values ('Dance', uuid_generate_v4());
insert into workout_type (name, uuid) values ('Interval', uuid_generate_v4());
insert into workout_type (name, uuid) values ('Yoga', uuid_generate_v4());
insert into workout_type (name, uuid) values ('Pilates', uuid_generate_v4());
insert into workout_type (name, uuid) values ('Home', uuid_generate_v4());
insert into workout_type (name, uuid) values ('Jym', uuid_generate_v4());

insert into role (name, uuid) values ('ROLE_USER', uuid_generate_v4());
insert into role (name, uuid) values ('ROLE_ADMIN', uuid_generate_v4());
insert into role (name, uuid) values ('ROLE_COACH', uuid_generate_v4());
