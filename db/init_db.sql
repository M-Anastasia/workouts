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

insert into workout_type (name, uuid) values ('Regular', '5cb62e57-1daf-4c85-b15b-a207573ae290');
insert into workout_type (name, uuid) values ('Stretching', '3d8b4374-8d34-47ce-8f80-adde6ab8c6e9');
insert into workout_type (name, uuid) values ('HIIT', '046fc9fc-a967-4561-8bc2-2718f179a7f7');
insert into workout_type (name, uuid) values ('Tabata', 'b6f714c4-207a-4faa-827d-a98d8318d624');
insert into workout_type (name, uuid) values ('Cardio', '0cb79fe0-ad0b-40db-ac86-08f2fd941fbf');
insert into workout_type (name, uuid) values ('Dance', 'd5a939d8-159c-47f2-9508-338006a4b052');
insert into workout_type (name, uuid) values ('Interval', '34673bdb-c25e-497e-b5d9-8f82f75561a2');
insert into workout_type (name, uuid) values ('Yoga', '92952607-25e6-4a2e-b649-33457c340738');
insert into workout_type (name, uuid) values ('Pilates', 'f24d91d5-4634-4876-ba2a-39ce8fea7621');
insert into workout_type (name, uuid) values ('Home', 'b0d3d18b-24a3-46c1-bd83-69dac5ddbbf6');
insert into workout_type (name, uuid) values ('Jym', 'e01d5b5b-2536-43a0-9501-c2b673841f00');

insert into role (name, uuid) values ('ROLE_USER', uuid_generate_v4());
insert into role (name, uuid) values ('ROLE_ADMIN', uuid_generate_v4());
insert into role (name, uuid) values ('ROLE_COACH', uuid_generate_v4());

insert into users (unique_id, uuid, name, password) values(1, '717f3b89-32ca-46ab-a754-428db6bb2836', 'admin', '{pIfa02qkUgYAQFe9OmYbjm7GarnxtwAvfaipR/EFf7k=}ea018527efb21ea1846d48b18c73011f');
insert into users_roles (users_unique_id, roles_unique_id) values (1, 2);
insert into workout (unique_id, description, is_private, name, uuid, video_link, creator, workout_type_id)
values (1, 'how I trained for my 1/2 marathon plus a free downloadable 12 week training schedule ✨
Here''s the link to the download if you guys want it: https://allthingskoze.com/blogs/kozec...

Follow more adventures:
▼ Instagram: https://www.instagram.com/kalynnichol...
▼ Together Apart We Run Insta: https://www.instagram.com/togetherapa...
▼ Rozanna''s Insta: https://www.instagram.com/rozannapurc...

Video Chapters 👟:
00:30 - race day morning routine
02:15 - real talk: why I decided to do this
04:47 - carb loading 🤤
05:45 - my recent meditation routine
06:25 - race day BEGINS!
08:10 - run COMPLETE!
10:10 - post run routine + dinner
12:09 - the 3 big things this taught me

Subscribe + hit the notification bell so you don''t miss the next vlog back in September ❤', false,'how to train for a 1/2 marathon', '3b33a088-1a11-4964-ad06-751bcccd15a9', 'https://www.youtube.com/embed/qRM3vKDLWhw', 1, 5);
insert into workout (unique_id, description, is_private, name, uuid, video_link, creator, workout_type_id)
values (2, 'Do this full body DANCE cardio workout #WithMe to some of the popular/trending #TikTok songs! This mashup of 8 different songs is sure to get you sweating and guaranteed to put a smile on your face!
#FullBodyWorkout

⭐️ SHOP MY COOKBOOKS!: https://goo.gl/XHwUJg

Blueberry Faygo  00:00 - 1:43
Savage Love 1:43 - 3:39
Stunnin'' 3:39 - 5:02
Lose Control - 5:02 - 7:50
Coño - 7:50 - 9:02
Do It 9:02 - 10:48
ILY 10:48 - 12:34
Rockstar 12:34 - 14:35

👉🏼THE MAT I USE (Exercise 6X4): http://gorillamats.com?aff=19  (MADFIT10 for 10% off)

👉🏼SUBSCRIBE TO MY PERSONAL CHANNEL (what i eat, recipes, vlogs): https://goo.gl/WTpDQk', false,'DANCE PARTY WORKOUT', '54ad3d15-b4ff-4bfa-a702-849d03027307', 'https://www.youtube.com/embed/ZNkMD78UjRs', 1, 6);
insert into workout (unique_id, description, is_private, name, uuid, video_link, creator, workout_type_id)
values (3, 'works perfectly as a cool down after ANY kind of workout, before bed or in the morning after waking up! ♥︎/ Werbung

To be honest: I don''t stretch enough. At the moment I just have 1 or 2 (that''s optimistic) relaxed stretching sessions per week.
With this cool down routine, I might change my habits though :D We all have 5min after a workout. And 5min (= 10 exercises) already go a long way.
We stretch the sides of our body, our legs, tummy and work on our spine and hip mobility. No new or fancy exercises - but finally a super quick video we can stick to :D

Let''s calm down & give our bodies some love in a different kind of way.
PS: I also have longer Stretching Routines on my channel ♥︎ 15min Daily Stretch & 2x 30min Stretching ', false,'5 MIN DAILY STRETCH', '4e49dcdc-39ef-42af-909f-781e41cac404', 'https://www.youtube.com/embed/KPG1tJW8dwQ', 1, 2);
insert into workout (unique_id, description, is_private, name, uuid, video_link, creator, workout_type_id)
values (4, 'All you need is a little bit of floor space and a mat for todays REAL TIME 20minute pilates based core workout! Today I''m workout out with my very own pilates instructor Simone, founder of the Loft Pilates and Barre. Throw on your activewear, roll out your mat and let''s get that core firing up!', false,'Core & Ab Pilates Home Workout', '7ce013b4-3d43-47ed-9e23-163e81dd6e2b', 'https://www.youtube.com/embed/ta2snBX8roU', 1, 2);