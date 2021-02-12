# API - Library

##### Application web développé dans le cadre du projet 7 OpenClassromms du parcours "Développez le nouveay système d'information de la bibliothèque d'une grande ville"
##### Nom du projet "Library d'OC-City"

##### Objet : Création d'un site web pour les bibliothèque d'une grande ville

##### Déploiement du projet API library
  
Pré-requis :
 
  - Avoir installer Maven
  
Déploiement :
 
  - Télécharger le code de l'application en cliquant sur "Code" et "download ZIP" sur le lien Github. Décompressez le .ZIP. Ouvrez le terminal et placer vous dans le dossier webapp/target et entrer la commande "java -jar webapp-1.0-SNAPSHOT.jar"
  
  - L'api sera lancé.
  
##### Test intégration avec Newman

Pré-requis:

  - Avoir installer Docker 
  - Avoir installer Newman
  
Utilisation des test : 

  Pour lancer les tests d'intégration via Newman, il suffit de lancer l'image docker de base de données MySQL. Ensuite il faut lancer l'api.
  Pour finir lancer un terminal et saisissez la commande "newman run https://www.getpostman.com/collections/a60aa98635a63b97b65a". Cela lancera les tests d'intégration et vous aurez le résumé.
