drop database if exists matricula;

create database matricula;

use matricula; 

drop table if exists alumnos;
drop table if exists asignaturas;
drop table if exists alumnos_asignaturas;

create table alumnos (
   id_alumno integer not null,
   apellidos varchar(24) not null,
   nombre varchar(18) not null,
   curso integer not null,
   titulacion integer not null,
   constraint id_pk primary key (id_alumno)
);

create table asignaturas (
   id_asignatura integer not null,
   tipo varchar(2) not null,
   nombre varchar(60) not null,
   creditos float not null,
   constraint id_pk primary key (id_asignatura)
);

create table alumnos_asignaturas (
   id_alumno integer not null,
   id_asignatura integer not null,
   cursada bool not null,
   constraint as_pk foreign key (id_asignatura) references asignaturas (id_asignatura) on delete cascade,
constraint al_pk foreign key (id_alumno) references alumnos (id_alumno) on delete cascade
);

insert into alumnos values(9119705,"JIMENEZ ALONSO"	,"DIEGO",4,3);
insert into alumnos values(4338289,"MANGAS SANZ","CESAR",1,12);
insert into alumnos values(5345629,"BARRIGA ASENJO","JOSE",2,7);
insert into alumnos values(5198695,"RODRIGUEZ ROBLEDO","FCO. JAVIER",3,5);
insert into alumnos values(5434159,"BLAZQUEZ BLANCO","SONIA",2,7);


insert into asignaturas values(31540,"OB","AMPLIACION DE SISTEMAS OPERATIVOS",4.5);
insert into asignaturas values(32330,"OP","APLICACIONES DISTRIBUIDAS PARA BIOINGENIERIA",3);
insert into asignaturas values(33033,"OP","APLICACIONES TELEMATICAS",4.5);
insert into asignaturas values(20598,"TR","ARQUITECTURA DE COMPUTADORES",7.5);
insert into asignaturas values(78200,"OP","ARQUITECTURA E INGENIERIA DE COMPUTADORES",6);

insert into alumnos_asignaturas values(9119705,31540,0);
insert into alumnos_asignaturas values(9119705,32330,0);
insert into alumnos_asignaturas values(4338289,31540,1);
insert into alumnos_asignaturas values(4338289,32330,0);
insert into alumnos_asignaturas values(5345629,33033,0);
insert into alumnos_asignaturas values(5345629,32330,1);
insert into alumnos_asignaturas values(5345629,20598,1);
insert into alumnos_asignaturas values(5198695,31540,1);
insert into alumnos_asignaturas values(5198695,32330,0);
insert into alumnos_asignaturas values(5198695,33033,0);
insert into alumnos_asignaturas values(5198695,20598,0);
insert into alumnos_asignaturas values(5434159,32330,0);
insert into alumnos_asignaturas values(5434159,33033,1);
insert into alumnos_asignaturas values(5434159,20598,0);
insert into alumnos_asignaturas values(5434159,78200,1);
insert into alumnos_asignaturas values(4338289,20598,0);
insert into alumnos_asignaturas values(9119705,20598,0);
insert into alumnos_asignaturas values(9119705,78200,1);






