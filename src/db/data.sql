insert into Dish (id,name,dish_type) values
(1,'salade Fraiche', 'Start'),
(2,'Poulet grillé','Main'),
(3,'Riz au legume','Main'),
(4,'Gateau au chocolat','Dessert'),
(5,'Salade de fruit', 'Dessert');


INSERT INTO ingredient (id, name, price, category, id_dish) VALUES
(1, 'Laitue', 800.00, 'VEGETABLE', 1),
(2, 'Tomate', 600.00, 'VEGETABLE', 1),
(3, 'Poulet', 4500.00, 'ANIMAL', 2),
(4, 'Chocolat', 3000.00, 'OTHER', 4),
(5, 'Beurre', 2500.00, 'DAIRY', 4);

select * from ingredient;

alter table ingredient
    add column  required_quqntity integer ;

select * from ingredient;


alter table ingredient
rename column required_quqntity to required_quantity;


UPDATE ingredient
SET required_quantity = 1
where id =1;


UPDATE ingredient
SET required_quantity = 2
where id =2;


UPDATE ingredient
SET required_quantity = 0.5
where id =3;

ALTER TABLE ingredient
    ALTER COLUMN required_quantity type Numeric;