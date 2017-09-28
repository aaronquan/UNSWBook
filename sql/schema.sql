create table UNSWBOOKUSER(
  ID integer NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
  USERNAME varchar(30),
  PWD varchar(30),
  NAME varchar(30),
  EMAIL varchar(30));