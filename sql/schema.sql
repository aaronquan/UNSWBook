drop table UNSWBOOKUSER;

create table UNSWBOOKUSER(
  ID integer NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1) PRIMARY KEY,
  USERNAME varchar(30) UNIQUE,
  PWD varchar(30) NOT NULL,
  NAME varchar(30) NOT NULL,
  EMAIL varchar(30) UNIQUE,
  GENDER varchar(6) CHECK (GENDER IN ('Male', 'Female', 'Other')),
  AGE integer
);
  
drop table UNSWBOOKPOST;

create table UNSWBOOKPOST(
  ID integer NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1) PRIMARY KEY,
  USERID integer references UNSWBOOKUSER(ID),
  POST varchar(30),
  POSTED timestamp
);