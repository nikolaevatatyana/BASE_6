begin
execute immediate 'drop table prof';
exception
    when others then
        if sqlcode != -942 then
            raise;
end if;
end;

begin
execute immediate 'drop sequence s_prof';
exception
    when others then
        if sqlcode != -2289 then
            raise;
end if;
end;

create sequence s_prof start with 1 increment by 1 nomaxvalue

create table prof (
                          id int,
                          name varchar(50) not null,
                          primary key(id)
)

begin
execute immediate 'drop table lab';
exception
    when others then
        if sqlcode != -942 then
            raise;
end if;
end;

begin
execute immediate 'drop sequence s_lab';
exception
    when others then
        if sqlcode != -2289 then
            raise;
end if;
end;

create sequence s_lab start with 1 increment by 1 nomaxvalue

create table lab (
                         id int,
                         name varchar(50) not null,
                         a_date int not null,
                         survey int,
                         primary key(id)
)

begin
execute immediate 'drop table ward';
exception
    when others then
        if sqlcode != -942 then
            raise;
end if;
end;

begin
execute immediate 'drop sequence s_ward';
exception
    when others then
        if sqlcode != -2289 then
            raise;
end if;
end;

create sequence s_ward start with 1 increment by 1 nomaxvalue

create table ward
(
    id  int,
    bed int,
    primary key (id)
)

begin
execute immediate 'drop table doctors';
exception
    when others then
        if sqlcode != -942 then
            raise;
end if;
end;

begin
execute immediate 'drop sequence s_doctors';
exception
    when others then
        if sqlcode != -2289 then
            raise;
end if;
end;

create sequence s_doctors start with 1 increment by 1 nomaxvalue

create table doctors (
                     id int,
                     name varchar(50) not null,
                     op int,
                     profile varchar(50) not null,
                     primary key(id)
)

begin
execute immediate 'drop table patient';
exception
    when others then
        if sqlcode != -942 then
            raise;
end if;
end;

begin
execute immediate 'drop sequence s_patient';
exception
    when others then
        if sqlcode != -2289 then
            raise;
end if;
end;

create sequence s_patient start with 1 increment by 1 nomaxvalue

create table patient (
                         id int,
                         name varchar(50) not null,
                         polyclinic_id int,
                         doctor_id int,
                         ward_id int,
                         state varchar(50) not null,
                         primary key(id)
)

begin
execute immediate 'drop table polyclinic';
exception
    when others then
        if sqlcode != -942 then
            raise;
end if;
end;

begin
execute immediate 'drop sequence s_polyclinic';
exception
    when others then
        if sqlcode != -2289 then
            raise;
end if;
end;

create sequence s_polyclinic start with 1 increment by 1 nomaxvalue

create table polyclinic (
                         id int,
                         name varchar(50) not null,
                         hospital_id int,
                         doctor int,
                         cabinet int,
                         n_cabinet int,
                         primary key(id)
)

begin
execute immediate 'drop table hospital';
exception
    when others then
        if sqlcode != -942 then
            raise;
end if;
end;

begin
execute immediate 'drop sequence s_hospital';
exception
    when others then
        if sqlcode != -2289 then
            raise;
end if;
end;

create sequence s_hospital start with 1 increment by 1 nomaxvalue

create table hospital (
                            id int,
                            name varchar(50) not null,
                            prof varchar(50) not null,
                            corpus int,
                            ward int,
                            f_ward int,
                            primary key(id)
)