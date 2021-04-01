select * from fb_topics;
select * from fb_messages;
select * from fb_favorites;
select * from fb_profiles;
select * from fb_imams;

select distinct imam_uid from fb_topics where imam_uid not in (select id from fb_imams);

select distinct sender_name from fb_messages order by 1;

select * from fb_profiles where login not in (select username from users);
