-- insert into jsontb (id,friend_list) values(1,'{"friend":["hung beo","thao Hip","Thi Vo"]}');

-- select * from jsontb;

-- select json_length(friend_list,'$.friend') from jsontb where id=1;

update jsontb set friend_list=json_set(friend_list,'$.friend[0]','jack') where id=1;