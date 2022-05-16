INSERT INTO CATEGORIA (id, nombre) VALUES (NEXTVAL('hibernate_sequence'),'Ensaladas');
INSERT INTO CATEGORIA (id, nombre) VALUES (NEXTVAL('hibernate_sequence'),'Carnes');
INSERT INTO PRODUCTO (id, nombre, precio, cantidad_disponible, url, categoria_id) VALUES (NEXTVAL('hibernate_sequence'),'Ensalada César', 54, 54, 'vsrvvsr', 1);
INSERT INTO PRODUCTO (id, nombre, precio, cantidad_disponible, url, categoria_id) VALUES (NEXTVAL('hibernate_sequence'),'Ensalada Normal', 54, 54, 'vsrvvsr', 1);
INSERT INTO PRODUCTO (id, nombre, precio, cantidad_disponible, url, categoria_id) VALUES (NEXTVAL('hibernate_sequence'),'entrecot', 54, 54, 'vsrvvsr', 2);
INSERT INTO PRODUCTO (id, nombre, precio, cantidad_disponible, url, categoria_id) VALUES (NEXTVAL('hibernate_sequence'),'solomillo', 54, 54, 'vsrvvsr', 2);
