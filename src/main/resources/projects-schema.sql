DROP TABLE IF EXISTS project_category;
-- hi
DROP TABLE IF EXISTS material;
DROP TABLE IF EXISTS step;
DROP TABLE IF EXISTS category;
DROP TABLE IF EXISTS project;

-- tables with primary keys
-- not null means they must contain a variable 
-- DECIMAL PLAYS THE SAME ROLE AS INT
-- tables with keys and primery keys
-- deleted on casGrapefrGcade means..........................................
-- primary keys connect to the foreign key
-- ids must match
-- primary keys and foriegn key are related if they are the same having the same number will link tables togther
-- when do refrence kesys come in place..............................................
-- REFRENCES are as important than primary and forgein keys when a foreign key is refrence connecting another table
-- foreign keys usually have a refrences key after the key.



-- join table is the primary key tables it is seperated by an underscore.


-- DELETE CASCADE
-- deleting child rows ON DELETE CASCADE syntax is usually at the end .
-- what does delete child refer to or delete in this case.. ..................
-- delete casacade will allow the table(EX: recipes)  to be delete and will delete the steps in int as well (EX: matching foregin keys)

-- When would you not use DELETE CASCADE
-- " would you want to delete a unit if you delete a ingrediant ... "



-- QUESTIONS 
-- Why do i use comas @ the end of some foreign and primary keys but not all 
-- how to define a unique key to a table

 
CREATE TABLE project (
project_id INT AUTO_INCREMENT NOT NULL,
project_name VARCHAR(128) NOT NULL,
estimated_hours DECIMAL(7,2),
actual_hours DECIMAL (7,2),
difficulty INT,
notes  TEXT, 
PRIMARY KEY (project_id) 
);
-- NOTE: this priamry key does not contain a comma

CREATE TABLE material(
material_id INT AUTO_INCREMENT NOT NULL,
project_id INT NOT NULL,
material_name VARCHAR(128) NOT NULL,
num_required INT,
cost DECIMAL(7,2),
PRIMARY KEY (material_id),
FOREIGN KEY (project_id) REFERENCES project (project_id) ON DELETE CASCADE
);

CREATE TABLE step(
step_id INT AUTO_INCREMENT NOT NULL, 
project_id INT NOT NULL,
step_text TEXT NOT NULL,
step_over INT NOT NULL,
PRIMARY KEY ( step_id),
FOREIGN KEY (project_id) REFERENCES project (project_id) ON DELETE CASCADE

);

CREATE TABLE category(
category_id INT AUTO_INCREMENT NOT NULL,
category_name VARCHAR(128) NOT NULL,

PRIMARY KEY ( category_id) 
);

CREATE TABLE project_category(
project_id INT NOT NULL,
category_id INT NOT NULL,
FOREIGN KEY (project_id) REFERENCES project (project_id) ON DELETE CASCADE,
FOREIGN KEY (category_id) REFERENCES category (category_id) ON DELETE CASCADE,
UNIQUE KEY (project_id, category_id)

);
 