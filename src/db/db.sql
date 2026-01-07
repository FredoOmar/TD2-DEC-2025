
--ici je ferme toutt les connexion dont la liste de pg-stat-activity
--qui filtre le nom de mon db product_manaement_db
-- pid <> pg_backend_pid() exclue la connexion courante

-- Suppretion la base de données si elle existe
DROP DATABASE IF EXISTS mini_dish_db;

-- Suppretion de l'utilisateur s'il existe
DROP USER IF EXISTS mini_dish_db_manager;

-- Créer le nouvel utilisateur
CREATE USER mini_dish_db_manager WITH PASSWORD 'new185231';

-- Créer la base de données
CREATE DATABASE mini_dish_db
    OWNER mini_dish_db_manager --definit que product_manager_user et la proprietaire de la db
    ENCODING 'UTF8' --encodage
     -- garanti que le db est propre sans objet ajouter

ALTER DATABASE mini_dish_db  OWNER TO mini_dish_db_manager ;
-- Donner tous les privilèges a product_manager_user sur la base
GRANT ALL PRIVILEGES ON DATABASE mini_dish_db TO mini_dish_db_manager with password 'new185231';

-- Se connecter
\c mini_dish_db

--  les permissions de création dans le schéma public
-- public pour que l'utilisateur puisse avoire la permisssion de creer des tables ,vue , fonction etc.
-- GRANT est la commande pour donner un privilege a l'utilisateur sur des objets de la db
-- GRANT ALL => donner tous les privilege
GRANT CREATE ON SCHEMA public TO pmini_dish_db_manager;
GRANT USAGE ON SCHEMA public TO mini_dish_db_manager;
GRANT ALL PRIVILEGES ON ALL TABLES IN SCHEMA public TO mini_dish_db_manager;
GRANT ALL PRIVILEGES ON ALL SEQUENCES IN SCHEMA public TO mini_dish_db_manager;

-- Configuration des permissions pour les futures tables et es sequence de code
ALTER DEFAULT PRIVILEGES IN SCHEMA public
    GRANT ALL PRIVILEGES ON TABLES TO mini_dish_db_manager;

ALTER DEFAULT PRIVILEGES IN SCHEMA public
    GRANT ALL PRIVILEGES ON SEQUENCES TO mini_dish_db_manager;