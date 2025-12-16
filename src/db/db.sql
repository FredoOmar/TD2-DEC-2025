
--ici je ferme toutt les connexion dont la liste de pg-stat-activity
--qui filtre le nom de mon db product_manaement_db
-- pid <> pg_backend_pid() exclue la connexion courante

-- Suppretion la base de données si elle existe
DROP DATABASE IF EXISTS product_management_db;

-- Suppretion de l'utilisateur s'il existe
DROP USER IF EXISTS product_manager_user;

-- Créer le nouvel utilisateur
CREATE USER product_manager_user WITH PASSWORD '123456';

-- Créer la base de données
CREATE DATABASE product_management_db
    OWNER product_manager_user --definit que product_manager_user et la proprietaire de la db
    ENCODING 'UTF8' --encodage
    TEMPLATE template0; -- garanti que le db est propre sans objet ajouter

-- Donner tous les privilèges a product_manager_user sur la base
GRANT ALL PRIVILEGES ON DATABASE product_management_db TO product_manager_user;

-- Se connecter
\c product_management_db

--  les permissions de création dans le schéma public
-- public pour que l'utilisateur puisse avoire la permisssion de creer des tables ,vue , fonction etc.
-- GRANT est la commande pour donner un privilege a l'utilisateur sur des objets de la db
-- GRANT ALL => donner tous les privilege
GRANT CREATE ON SCHEMA public TO product_manager_user;
GRANT USAGE ON SCHEMA public TO product_manager_user;
GRANT ALL PRIVILEGES ON ALL TABLES IN SCHEMA public TO product_manager_user;
GRANT ALL PRIVILEGES ON ALL SEQUENCES IN SCHEMA public TO product_manager_user;

-- Configuration des permissions pour les futures tables et es sequence de code
ALTER DEFAULT PRIVILEGES IN SCHEMA public
    GRANT ALL PRIVILEGES ON TABLES TO product_manager_user;

ALTER DEFAULT PRIVILEGES IN SCHEMA public
    GRANT ALL PRIVILEGES ON SEQUENCES TO product_manager_user;