create type dish_type as enum ('Start', 'Main', 'Dessert');
create table  Dish(
    id serial primary key,
    name varchar(200),
    dish_type  dish_type
);

create  type ingredient_category as enum ('vegetable','Animal', 'Marine', 'Dairy', 'Other');

create table  Ingredient(
     id serial primary key,
    name varchar(200),
    price numeric,
    category ingredient_category,
    id_dish integer references Dish(id)
);

select * from dish;
select * from ingredient;

SELECT schemaname, tablename, tableowner
FROM pg_tables
WHERE tablename = 'ingredient';

alter table ingredient
rename  column dish_id to id_dish;

