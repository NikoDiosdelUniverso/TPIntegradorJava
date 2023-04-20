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
  
insert into pronosticos (fase, ronda, partido, equipo1, ganador, equipo2)
 values (1, 1, 1, "Argentia", 1, "Arabaria Saudita"),
 (1, 1, 1, "Argentia", 1, "Arabaria Saudita"),
(1, 1, 2, "Polonia", 3, "México"),
(1, 1, 2, "Polonia", 3, "México"),
(1, 1, 3, "Argentina", 1, "México"),
(1, 1, 3, "Argentina", 1, "México"),
(1, 2, 4, "Polonia", 0 ,"México"),
(1, 2, 4, "Polonia", 0 ,"México"),
(1, 2, 5, "Arabia Saudita", 2, "México"),
(1, 2, 5, "Arabia Saudita", 2, "México");

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