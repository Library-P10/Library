# Library

Projet 10 - Améliorez le système d’information de la bibliothèque

##### Les contraintes fonctionnelles 

* Application web avec un framework MVC
* API web en REST: les clients (site web et batchs) communiqueront avec cette API web
* Packaging avec Maven

##### Développement 

Les outils pour le développement sont :
- IDE : IntelliJ IDEA 
- Java 15
- Tomcat 9
- Spring Boot version t2.3.3
- Thymeleaf 
- Base de données MySQL  8

##### Déploiement Base de données

	- L'application à été configurée pour une base de données MySQL.
	
	- Installer  MySQL Workbench. Téléchargement et documentation sur https://www.mysql.com/fr/products/workbench/.
	
	- Créer un profil utilisateur avec les droits de création et de gestion de base de données. (username='admin' & password='admin')
	
	- Utiliser les scripts SQL, placé dans le dossier Database du MS API, dans MySQLWorkbench dans l'ordre ( 01_create_schema.sql, 02_create_table.sql , 03_insert_data_demo.sql ) pour pouvoir utiliser les données. 

##### Déploiement Application
	
Pour le déploiement de l'application chaque composant à un ReadMe à suivre.Il est important de d'abord lancé l'api et après soit le client web ou le batch.
Si problème, fermé vos terminal et relancer en suivant l'ordre API, client web et batch.

##### Auteur 

BRICHET Benoît 
