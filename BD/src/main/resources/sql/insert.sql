insert into prof values (s_prof.nextval, 'bio')

insert into prof values (s_prof.nextval, 'chem')

insert into lab values (s_lab.nextval, 'first', 5, 10)

insert into lab values (s_lab.nextval, 'second', 6, 11)

insert into doctors values (s_doctors.nextval, 'Ivanov', 7, 'surgeon')

insert into doctors values (s_doctors.nextval, 'Smirnov', 8, 'therapist')

insert into patient values (s_patient.nextval, 'Ivan', 1, 1, 1,'norm')

insert into patient values (s_patient.nextval, 'Petr', 2, 2, 2,'bad')

insert into polyclinic values (s_polyclinic.nextval, 'First', 1, 1, 1, 1)

insert into polyclinic values (s_polyclinic.nextval, 'Second', 2, 2, 2, 2)

insert into hospital values (s_hospital.nextval, 'firsT', 'prof1', 1, 1, 0)

insert into hospital values (s_hospital.nextval, 'seconD', 'prof2', 2, 2, 1)

insert into ward values (s_ward.nextval, 2)

insert into ward values (s_ward.nextval, 3)