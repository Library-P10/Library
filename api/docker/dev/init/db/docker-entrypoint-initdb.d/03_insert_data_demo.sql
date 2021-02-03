INSERT INTO library.author (id, first_name, last_name) VALUES (1, 'Olivier', 'Norek');
INSERT INTO library.author (id, first_name, last_name) VALUES (2, 'Franck', 'Thilliez');
INSERT INTO library.author (id, first_name, last_name) VALUES (3, 'Laurell K', 'Hamilton');
INSERT INTO library.author (id, first_name, last_name) VALUES (4, 'Elie', 'Buzyn');
INSERT INTO library.author (id, first_name, last_name) VALUES (5, 'Bernard', 'Minier');
INSERT INTO library.author (id, first_name, last_name) VALUES (6, 'Fabien', 'Clauw');

INSERT INTO library.categorie (id, label) VALUES (1, 'Policier');
INSERT INTO library.categorie (id, label) VALUES (2, 'Action');
INSERT INTO library.categorie (id, label) VALUES (3, 'Guerre');
INSERT INTO library.categorie (id, label) VALUES (4, 'Fantastique');

INSERT INTO library.customer (id, first_name, last_name, adress, postal_code, city, email, password) VALUES (3, 'toto', 'test', '3 rue de ici', '56500', 'totoland', 'test@test.fr', '$2a$10$AoKHZdtNnmqcb9B0J588m.WXJGUql3J38KYVYSdJ/VbxnRWWiIy2.');
INSERT INTO library.customer (id, first_name, last_name, adress, postal_code, city, email, password) VALUES (8, 'Bruno', 'Brichet', '3 rue de labas', '75200', 'Devland', 'benoit@test.fr', '$2a$10$4bEdMuYgdQEKBJdK2mUMOuw9QC4UVPh9uKr6rtGKQf3.NKHsHmdPS');
INSERT INTO library.customer (id, first_name, last_name, adress, postal_code, city, email, password) VALUES (9, 'admin', 'admin', 'admin', 'admin', 'admin', 'admin@admin.fr', '$2a$10$a32qexgK4tce33bNBmOSdeu88Q8BNRRwQCGfx0gjSm.ZQ.6LWgI0u');

INSERT INTO library.library (id, nom, adress, phone_num, email) VALUES (1, 'Bibliothèque Sud', '3 Rue de la résistance ', '0924651820', 'bibliothèqueSud@hotmail.fr');
INSERT INTO library.library (id, nom, adress, phone_num, email) VALUES (2, 'Bibliothèque Nord', '10 rue de la liberté', '0945185481', 'bibliothèqueNord@hotmail.fr');

INSERT INTO library.book (id, title, pub_date, page, synopsis, cover, author_id, categorie_id) VALUES (1, 'Le Cadavre Rieur', '2009-03-06', 384, 'Savez-vous ce que c’est qu’une « chèvre blanche » ? Eh bien, en jargon vaudou, c’est un doux euphémisme pour désigner la victime d’un sacrifice humain. Et quand ces types sont venus me demander de relever un mort de deux cents ans et des poussières, j’ai tout de suite compris ce que ça impliquait. Je veux bien égorger des poulets, un mouton, voire un buffle dans les cas désespérés... mais ça, non ! Pas question... Mais je les ai envoyés promener, eux et leur chèque d’un million de dollars. L’ennui, c’est que tout le monde n’a pas mon sens moral. Que ces salauds vont bien dégoter quelqu’un pour faire le boulot. Qu’on va se retrouver avec un mort-vivant raide dingue, tout sauf végétarien, et semant la panique. Et que c’est encore la petite Anita qui va devoir se le coltiner ! Comme si je n’avais pas déjà assez de problèmes avec les vampires...', 'Le cadavre Rieur', 3, 2);
INSERT INTO library.book (id, title, pub_date, page, synopsis, cover, author_id, categorie_id) VALUES (2, 'Entre Deux Mondes', '2017-10-05', 413, 'Engagé dans l’humanitaire pendant la guerre en ex-Yougoslavie, puis lieutenant à la section Enquête et Recherche de la police judiciaire du 93 depuis dix-huit ans, OLIVIER NOREK est l’auteur de trois romans largement salués par la critique et traduits dans plusieurs pays, ainsi que le lauréat de nombreux prix littéraires. Après Code 93, Territoires et Surtensions, il nous invite dans un monde Entre deux mondes que nul ne peut imaginer, où se rencontrent deux inspecteurs que tout semble opposer et qui devront unir leurs forces pour sauver un enfant.  ', 'Entre deux mondes', 1, 1);
INSERT INTO library.book (id, title, pub_date, page, synopsis, cover, author_id, categorie_id) VALUES (3, 'Code 93', '2014-10-09', 360, 'Un cadavre, émasculé, qui rouvre les yeux sur la table d''autopsie. Un portable qui se met à sonner dans le corps d''un jeune toxico, mort de brûlures inexplicables. Malgré quinze ans de terrain en Seine-Saint-Denis, Victor Coste, capitaine de police, se prépare au pire.
Et que penser de ces lettres anonymes qui dessinent une première piste : celle d''un mystérieux dossier, le " Code 93 " ?
Une piste qui, des cercles huppés parisiens aux quartiers déshérités, fera franchir à Coste les limites du périphérique, et de la raison...', 'Code 93', 1, 1);
INSERT INTO library.book (id, title, pub_date, page, synopsis, cover, author_id, categorie_id) VALUES (4, 'Il était deux fois', '2020-06-04', 528, 'En 2008, Julie, dix-sept ans, disparaît en ne laissant comme trace que son vélo posé contre un arbre. Le drame agite Sagas, petite ville au cœur des montagnes, et percute de plein fouet le père de la jeune fille, le lieutenant de gendarmerie Gabriel Moscato. Ce dernier se lance alors dans une enquête aussi désespérée qu''effrénée.
Jusqu''à ce jour où ses pas le mènent à l''hôtel de la Falaise... Là, le propriétaire lui donne accès à son registre et lui propose de le consulter dans la chambre 29, au deuxième étage. Mais exténué par un mois de vaines recherches, il finit par s''endormir avant d''être brusquement réveillé en pleine nuit par des impacts sourds contre sa fenêtre...
Dehors, il pleut des oiseaux morts. Et cette scène a d''autant moins de sens que Gabriel se trouve à présent au rez-de-chaussée, dans la chambre 7. Désorienté, il se rend à la réception où il apprend qu''on est en réalité en 2020 et que ça fait plus de douze ans que sa fille a disparu...', 'Il était deux fois', 2, 1);
INSERT INTO library.book (id, title, pub_date, page, synopsis, cover, author_id, categorie_id) VALUES (5, 'J''avais 15 ans', '2019-05-21', 157, 'Août 1944. Après une enfance heureuse en Pologne, Élie Buzyn subit l’indicible : la déportation, l’assassinat des siens, Auschwitz puis la marche de la mort jusqu’à Buchenwald. Il a 15 ans.
Le camp est libéré le 11 avril 1945. Comment, alors, retourner à la vie ? Porté par les voix du passé, il reconstruit ailleurs ce qui a été détruit. Étrange périple de Buchenwald à la France, en passant par la Palestine et l’Algérie, étrange voyage de la mort à la vie.
Un jour, il comprend qu’il est temps de témoigner.
De l’ombre à la lumière, du silence à la parole, un chemin de vie unique retracé avec etty buzyn, son épouse, écrivaine et psychanalyste.

Né à Lodz (Pologne) en 1929, Élie Buzyn est l’un des rares adolescents rescapés d’Auschwitz. Il est aujourd’hui, l’un des derniers témoins de la Shoah. Il est également l’auteur de Ce que je voudrais transmettre. Son épouse, Etty Buzyn, est psychologue et psychanalyste. Elle est l’auteure de nombreux ouvrages dont Quand l’enfant nous délivre du passé, et Quand les mères craquent. ', 'J''avais 15 ans', 4, 3);
INSERT INTO library.book (id, title, pub_date, page, synopsis, cover, author_id, categorie_id) VALUES (6, 'La vallée', '2020-05-20', 522, '" Je crois que quelqu''un est en train d''agir comme s''il se prenait pour Dieu... "

Un appel au secours au milieu de la nuit
Une vallée coupée du monde
Une abbaye pleine de secrets
Une forêt mystérieuse
Une série de meurtres épouvantables
Une population terrifiée qui veut se faire justice
Un corbeau qui accuse
Une communauté au bord du chaos
Une nouvelle enquête de Martin Servaz
Le nouveau thriller de Bernard Minier', 'La vallée', 5, 4);
INSERT INTO library.book (id, title, pub_date, page, synopsis, cover, author_id, categorie_id) VALUES (7, 'Pour les trois couleurs', '2018-01-11', 356, 'Mars 1798. Robespierre a perdu la tête et Napoléon est encore dans les langes de Bonaparte. En ces temps tourmentés, intrigues et trahisons sont le quotidien d’hommes de pouvoir cherchant autant à sauver leur tête qu’à faire fortune. En guerre contre la plupart des monarchies d’Europe, la France révolutionnaire compte l’Angleterre et sa puissante Royal Navy parmi ses ennemis. Hélas, à court de crédits, la flotte française souffre d’une corruption généralisée et une grande partie de ses officiers, de noble lignée, a migré. La jeune Marine républicaine se consume sur les cendres de la défunte Royale&hellip


Dans cette ambiance bouillonnante du Directoire, décor inédit du genre, l’auteur plante le fougueux Gilles Belmonte, capitaine de frégate. Engagé dans la Marine royale à l’âge de 13 ans, il en a 29 lorsqu’il se voit confier un nouveau navire, l’Égalité, et une mission cruciale pour l’avenir de la France. Entre machinations des services secrets, combats en mer et amours naissants, son sens de l’honneur et son formidable instinct auront-ils raison d’un ennemi prêt à tout ?
', 'Pour les trois couleurs', 6, 4);

INSERT INTO library.copy (id, format, book_id, library_id, status) VALUES (1, 'Broché', 1, 1, 'Disponible');
INSERT INTO library.copy (id, format, book_id, library_id, status) VALUES (2, 'Broché', 1, 2, 'Disponible');
INSERT INTO library.copy (id, format, book_id, library_id, status) VALUES (3, 'Poche', 1, 1, 'Disponible');
INSERT INTO library.copy (id, format, book_id, library_id, status) VALUES (4, 'Poche', 1, 2, 'Disponible');
INSERT INTO library.copy (id, format, book_id, library_id, status) VALUES (5, 'Roman', 1, 1, 'Disponible');
INSERT INTO library.copy (id, format, book_id, library_id, status) VALUES (6, 'Roman', 1, 2, 'Disponible');
INSERT INTO library.copy (id, format, book_id, library_id, status) VALUES (7, 'Poche', 2, 1, 'Disponible');
INSERT INTO library.copy (id, format, book_id, library_id, status) VALUES (8, 'Poche', 2, 2, 'Disponible');
INSERT INTO library.copy (id, format, book_id, library_id, status) VALUES (9, 'Roman', 2, 1, 'Disponible');
INSERT INTO library.copy (id, format, book_id, library_id, status) VALUES (10, 'Roman', 2, 2, 'Disponible');
INSERT INTO library.copy (id, format, book_id, library_id, status) VALUES (11, 'Broché', 3, 1, 'Disponible');
INSERT INTO library.copy (id, format, book_id, library_id, status) VALUES (12, 'Broché', 3, 2, 'Disponible');
INSERT INTO library.copy (id, format, book_id, library_id, status) VALUES (13, 'Roman', 3, 2, 'Disponible');
INSERT INTO library.copy (id, format, book_id, library_id, status) VALUES (14, 'Roman', 3, 2, 'Disponible');
INSERT INTO library.copy (id, format, book_id, library_id, status) VALUES (15, 'Roman', 4, 1, 'Indisponible');
INSERT INTO library.copy (id, format, book_id, library_id, status) VALUES (16, 'Roman', 4, 2, 'Waiting');
INSERT INTO library.copy (id, format, book_id, library_id, status) VALUES (17, 'Poche', 5, 1, 'Disponible');
INSERT INTO library.copy (id, format, book_id, library_id, status) VALUES (18, 'Poche', 5, 2, 'Disponible');
INSERT INTO library.copy (id, format, book_id, library_id, status) VALUES (19, 'Roman', 5, 1, 'Disponible');
INSERT INTO library.copy (id, format, book_id, library_id, status) VALUES (20, 'Roman', 5, 2, 'Disponible');
INSERT INTO library.copy (id, format, book_id, library_id, status) VALUES (21, 'Broché', 6, 1, 'Indisponible');
INSERT INTO library.copy (id, format, book_id, library_id, status) VALUES (22, 'Broché', 6, 2, 'Disponible');
INSERT INTO library.copy (id, format, book_id, library_id, status) VALUES (23, 'Poche', 7, 1, 'Disponible');
INSERT INTO library.copy (id, format, book_id, library_id, status) VALUES (24, 'Poche', 7, 2, 'Disponible');
INSERT INTO library.copy (id, format, book_id, library_id, status) VALUES (25, 'Roman', 7, 1, 'Disponible');
INSERT INTO library.copy (id, format, book_id, library_id, status) VALUES (26, 'Roman', 7, 2, 'Disponible');

INSERT INTO library.emprunt (id, emprunt_date, return_date, is_extended, customer_id, copy_id) VALUES (11, '2021-01-07', '2021-02-04', 1, 9, 21);
INSERT INTO library.emprunt (id, emprunt_date, return_date, is_extended, customer_id, copy_id) VALUES (13, '2021-01-12', '2021-02-10', 1, 3, 15);

INSERT INTO library.waiting_list (id, customer_id, date_request, date_sending_mail, book_id, date_recovery_limit) VALUES (32, 3, '2021-01-10 15:58:30', null, 1, null);
INSERT INTO library.waiting_list (id, customer_id, date_request, date_sending_mail, book_id, date_recovery_limit) VALUES (36, 9, '2021-01-03 10:22:13', '2021-02-03 10:22:20', 1, '2021-02-17 10:22:30');