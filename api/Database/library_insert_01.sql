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

INSERT INTO library.library (id, nom, adress, phone_num, email) VALUES (1, 'Bibliothèque Sud', '3 Rue de la résistance ', '0924651820', 'bibliothèqueSud@hotmail.fr');
INSERT INTO library.library (id, nom, adress, phone_num, email) VALUES (2, 'Bibliothèque Nord', '10 rue de la liberté', '0945185481', 'bibliothèqueNord@hotmail.fr');

INSERT INTO library.customer (id, first_name, last_name, adress, postal_code, city, email, password) VALUES (3, 'toto', 'test', '3 rue de ici', '56500', 'totoland', 'test@test.fr', '$2a$10$AoKHZdtNnmqcb9B0J588m.WXJGUql3J38KYVYSdJ/VbxnRWWiIy2.');
INSERT INTO library.customer (id, first_name, last_name, adress, postal_code, city, email, password) VALUES (8, 'Bruno', 'Brichet', '3 rue de labas', '75200', 'Devland', 'benoit@test.fr', '$2a$10$4bEdMuYgdQEKBJdK2mUMOuw9QC4UVPh9uKr6rtGKQf3.NKHsHmdPS');
INSERT INTO library.customer (id, first_name, last_name, adress, postal_code, city, email, password) VALUES (9, 'admin', 'admin', 'admin', 'admin', 'admin', 'admin@admin.fr', '$2a$10$a32qexgK4tce33bNBmOSdeu88Q8BNRRwQCGfx0gjSm.ZQ.6LWgI0u');