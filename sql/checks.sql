-- Checks
select distinct imam_uid from fb_topics where imam_uid not in (select id from fb_imams) and imam_uid is not null;

select distinct sender_name from fb_messages where replace(sender_name, '\n', '') not in (select name from fb_imams) order by 1;

-- Helpers
select distinct sender_name, imam_uid
  from fb_messages m
  join fb_topics t on t.id = m.topic_id
 -- where imam_uid is not null and sender_name is not null
 order by 1, 2;

select * from fb_profiles where login not in (select username from users);

select * from fb_topics;
select * from fb_messages;
select * from fb_favorites;
select * from fb_profiles;
select * from fb_imams;
