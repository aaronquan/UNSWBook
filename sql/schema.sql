drop table UNSWBOOKFRIENDS;
drop table UNSWBOOKPOSTLIKES;
drop table UNSWBOOKPOST;
drop table UNSWBOOKUSERACTIVITY;
drop table GRAPHTRIPLESTORE;
drop table ENTITYSTORE;
drop table UNSWBOOKUSER;
			
create table UNSWBOOKUSER (
  ID integer NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1) PRIMARY KEY,
  USERNAME varchar(30) UNIQUE,
  PWD varchar(30) NOT NULL,
  NAME varchar(30) NOT NULL,
  EMAIL varchar(30) UNIQUE,
  GENDER varchar(6) CHECK (GENDER IN ('Male', 'Female', 'Other')),
  AGE integer,
  ISADMIN boolean DEFAULT FALSE,
  BANNED boolean DEFAULT FALSE
);

create table UNSWBOOKPOST (
  ID integer NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1) PRIMARY KEY,
  USERID integer references UNSWBOOKUSER(ID),
  ONWALL integer references UNSWBOOKUSER(ID),
  POST varchar(255),
  POSTED timestamp
);

create table UNSWBOOKFRIENDS (
  ID integer NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1) PRIMARY KEY,
  PERSON_A integer references UNSWBOOKUSER(ID),
  PERSON_B integer references UNSWBOOKUSER(ID),
  CONFIRMED boolean DEFAULT FALSE
);

create table UNSWBOOKPOSTLIKES (
  ID integer NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1) PRIMARY KEY,
  POST integer references UNSWBOOKPOST(ID),
  LIKEDBY integer references UNSWBOOKUSER(ID)
);

create table UNSWBOOKUSERACTIVITY (
	USERID integer references UNSWBOOKUSER(ID),
	REPORT varchar(255),
	REPORTED timestamp
);

SELECT report, reported from UNSWBOOKUSERACTIVITY where userid = 1 ORDER BY reported ASC;

create table ENTITYSTORE (
	SUBJECT varchar(255),
	PREDICATE varchar(5) CHECK (PREDICATE IN ('Type', 'Class', 'Title')),
	OBJECT varchar(255),
	primary key (SUBJECT, PREDICATE, OBJECT)
);

create table GRAPHTRIPLESTORE (
	SUBJECT varchar(255),
	PREDICATE varchar(255),
	OBJECT varchar(255),
	primary key (SUBJECT, PREDICATE, OBJECT)
);