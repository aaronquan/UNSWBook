--TRUNCATE TABLE UNSWBOOKFRIENDS;
--TRUNCATE TABLE UNSWBOOKPOSTLIKES;
--TRUNCATE TABLE UNSWBOOKPOST;
--TRUNCATE TABLE UNSWBOOKUSER;
TRUNCATE TABLE ENTITYSTORE;
TRUNCATE TABLE GRAPHTRIPLESTORE;


--INSERT into UNSWBOOKUSER (username, pwd, name, email) values ('jsamir', 'qwer', 'Jerome Samir', 'jerome.samir2@gmail.com');
--INSERT into UNSWBOOKUSER (username, pwd, name, email) values ('olseng', 'qwer', 'Olsen Gambit', 'olsen.gambit@grr.la');
--INSERT into UNSWBOOKUSER (username, pwd, name, email) values ('timc', 'qwer', 'Timothy Chillington', 'timothy.chillington@gmail.com');
--INSERT into UNSWBOOKUSER (username, pwd, name, email, isadmin) values ('admin', 'qwer', 'Admin Wazowski', 'awazowski@grr.la', true);
--INSERT into UNSWBOOKUSER (username, pwd, name, email) values ('testuser', 'qwer', 'Test Surname', 'test4321@grr.la');
--INSERT into UNSWBOOKUSER (username, pwd, name, email) values ('jsmith', 'qwer', 'John Smith', 'johnnoSmith@sharklasers.com');
--INSERT into UNSWBOOKUSER (username, pwd, name, email) values ('thomasK', 'qwer', 'Thomas Kelly', 'thomaskelly@grr.la');


--INSERT into UNSWBOOKFRIENDS (person_a, person_b, confirmed) values (7, 6, true); -- thomas and john
--INSERT into UNSWBOOKFRIENDS (person_a, person_b, confirmed) values (1, 3, true); -- jerome and tim
--INSERT into UNSWBOOKFRIENDS (person_a, person_b, confirmed) values (1, 2, true); -- jerome and olsen
--INSERT into UNSWBOOKFRIENDS (person_a, person_b, confirmed) values (1, 4, true); -- jerome and admin


-- SET UP ENTITY STORE
-- add posts
insert into ENTITYSTORE (subject, predicate, object) 
select 'P' || cast(ID as CHAR(3)), 'Type', 'Post' from UNSWBOOKPOST
union select 'P' || cast(ID as CHAR(3)), 'Class', 'entityNode' from UNSWBOOKPOST
union select 'P' || cast(ID as CHAR(3)), 'Title', POST from UNSWBOOKPOST;
-- add users
insert into ENTITYSTORE (subject, predicate, object) 
select 'U' || cast(ID as CHAR(3)), 'Type', 'User' from UNSWBOOKUSER
union select 'U' || cast(ID as CHAR(3)), 'Class', 'entityNode' from UNSWBOOKUSER
union select 'U' || cast(ID as CHAR(3)), 'Title', USERNAME from UNSWBOOKUSER;
-- Insert edges
insert into ENTITYSTORE (subject, predicate, object) values
('E1', 'Type', 'directedLink'),
('E1', 'Class', 'Edge'),
('E1', 'Title', 'FriendsWith');
insert into ENTITYSTORE (subject, predicate, object) values
('E2', 'Type', 'directedLink'),
('E2', 'Class', 'Edge'),
('E2', 'Title', 'Posted');
insert into ENTITYSTORE (subject, predicate, object) values
('E3', 'Type', 'directedLink'),
('E3', 'Class', 'Edge'),
('E3', 'Title', 'Likes');

-- SET UP GRAPHTRIPLESTORE
-- add friend connections
insert into GRAPHTRIPLESTORE (subject, predicate, object)
select distinct 'U' || cast(person_a as CHAR(3)),
'E1',
'U' || cast(person_b as CHAR(3))
from UNSWBOOKFRIENDS
where confirmed = true;
-- add posted connections
insert into GRAPHTRIPLESTORE (subject, predicate, object)
select 'U' || cast(USERID as CHAR(3)),
'E2',
'P' || cast(ID as CHAR(3))
from UNSWBOOKPOST;
-- add liked connections
insert into GRAPHTRIPLESTORE (subject, predicate, object)
select 'U' || cast(LIKEDBY as CHAR(3)),
'E3',
'P' || cast(POST as CHAR(3))
from UNSWBOOKPOSTLIKES;