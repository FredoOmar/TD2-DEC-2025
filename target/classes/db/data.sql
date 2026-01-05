insert into Dish (id,name,dish_type) values
(1,'salade Fraiche', 'Start'),
(2,'Poulet grillé','Main'),
(3,'Riz au legume','Main'),
(4,'Gateau au chocolat','Dessert'),
(5,'Salade de fruit', 'Dessert')


INSERT INTO ingredient (id, name, price, category, id_dish) VALUES
(1, 'Laitue', 800.00, 'VEGETABLE', 1),
(2, 'Tomate', 600.00, 'VEGETABLE', 1),
(3, 'Poulet', 4500.00, 'ANIMAL', 2),
(4, 'Chocolat', 3000.00, 'OTHER', 4),
(5, 'Beurre', 2500.00, 'DAIRY', 4);