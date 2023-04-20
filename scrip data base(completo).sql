create database pronosticos;
create table pronosticos.pronosticos(
id int auto_increment primary key,
fase int, 
ronda int,
partido int,
equipo1 varchar(45),
ganador int,
equipo2 varchar(45)
);
UPDATE pronosticos
SET persona = 
  CASE 
    WHEN id % 2 = 0 THEN "Pedro"
    ELSE "Mariana"
  END;
  
insert into pronosticos (fase, ronda, partido, equipo1, ganador, equipo2, persona, idpersona)
 values (2,3,6,"Italia",6,"Rusia","pedro",2),
(2,3,6,"Italia",5,"Rusia","mariana",1),
(2,3,6,"Italia",5,"Rusia","nicolas",3),
(2,3,6,"Italia",6,"Rusia","juan",4),
(2,3,6,"Italia",0,"Rusia","dante",5),
(2,3,7,"Holanda",8,"Japon","pedro",2),
(2,3,7,"Holanda",7,"Japon","mariana",1),
(2,3,7,"Holanda",8,"Japon","nicolas",3),
(2,3,7,"Holanda",0,"Japon","juan",4),
(2,3,7,"Holanda",7,"Japon","dante",5),
(2,3,8,"Italia",8,"Japon","pedro",2),
(2,3,8,"Italia",8,"Japon","mariana",1),
(2,3,8,"Italia",0,"Japon","nicolas",3),
(2,3,8,"Italia",5,"Japon","juan",4),
(2,3,8,"Italia",5,"Japon","dante",5),
(2,4,9,"Rusia",7,"Holanda","pedro",2),
(2,4,9,"Rusia",6,"Holanda","mariana",1),
(2,4,9,"Rusia",0,"Holanda","nicolas",3),
(2,4,9,"Rusia",7,"Holanda","juan",4),
(2,4,9,"Rusia",6,"Holanda","dante",5),
(2,4,10,"Japon",6,"Rusia","pedro",2),
(2,4,10,"Japon",0,"Rusia","mariana",1),
(2,4,10,"Japon",8,"Rusia","nicolas",3),
(2,4,10,"Japon",8,"Rusia","juan",4),
(2,4,10,"Japon",0,"Rusia","dante",5),
(2,4,11,"Holanda",5,"Italia","pedro",2),
(2,4,11,"Holanda",7,"Italia","mariana",1),
(2,4,11,"Holanda",0,"Italia","nicolas",3),
(2,4,11,"Holanda",5,"Italia","juan",4),
(2,4,11,"Holanda",7,"Italia","dante",5);

alter table pronosticos
add persona varchar(45);

UPDATE pronosticos
SET persona = 
  CASE 
    WHEN id % 2 = 0 THEN "Pedro"
    ELSE "Mariana"
  END;
  
  alter table pronosticos
add idpersona int;

UPDATE pronosticos
SET idpersona = 
  CASE 
    WHEN id % 2 = 0 THEN 2
    ELSE 1
  END;