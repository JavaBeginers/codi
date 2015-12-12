INSERT INTO PAISOS (codi_pais, codi_idioma, nom) VALUES ('DE','ES','Alemania' );
INSERT INTO PAISOS (codi_pais, codi_idioma, nom) VALUES ('AT','ES','Austria' );
INSERT INTO PAISOS (codi_pais, codi_idioma, nom) VALUES ('BE','ES','Belgica' );
INSERT INTO PAISOS (codi_pais, codi_idioma, nom) VALUES ('ES','ES','España' );

INSERT INTO IDIOMA (idioma_id, idioma_nom) VALUES (1, 'ES');
INSERT INTO IDIOMA (idioma_id, idioma_nom) VALUES (2, 'CA');
INSERT INTO IDIOMA (idioma_id, idioma_nom) VALUES (3, 'EN');

INSERT INTO ROL(id, descripcio) VALUES(1, 'administrador');
INSERT INTO ROL(id, descripcio) VALUES(2, 'secretaria');
INSERT INTO ROL(id, descripcio) VALUES(3, 'professor');
INSERT INTO ROL(id, descripcio) VALUES(4, 'usuari');

INSERT INTO USUARI(usuari_id, nom_usuari, contrasenya, noms, cognoms, nombre_doc_identif, pais_doc_identif, adreca, poblacio, codi_postal, pais_residencia, email, data_alta, idioma_id, actiu)
VALUES (1, 'jdominguez', '1234', 'Joan Antoni', 'Dominguez', '12345678A', 'ES', 'Carrer 1', 'Ciutat 1', '54321', 'ES', 'jdominguez@uoc.edu', '01/12/2015', 2, 'true');
INSERT INTO USUARI(usuari_id, nom_usuari, contrasenya, noms, cognoms, nombre_doc_identif, pais_doc_identif, adreca, poblacio, codi_postal, pais_residencia, email, data_alta, idioma_id, actiu)
VALUES (2, 'cceballos', '1111', 'Cristian', 'Ceballos', '23456789B', 'ES', 'Carrer 2', 'Ciutat 2', '12345', 'ES', 'cceballos@uoc.edu', '01/12/2015', 1, 'true');
INSERT INTO USUARI(usuari_id, nom_usuari, contrasenya, noms, cognoms, nombre_doc_identif, pais_doc_identif, adreca, poblacio, codi_postal, pais_residencia, email, data_alta, idioma_id, actiu)
VALUES (3, 'imargarit', '4321', 'Ivan', 'Margarit', '34567890C', 'ES', 'Carrer 3', 'Ciutat 3', '12121', 'ES', 'imargarit@uoc.edu', '01/12/2015', 1, 'true');
INSERT INTO USUARI(usuari_id, nom_usuari, contrasenya, noms, cognoms, nombre_doc_identif, pais_doc_identif, adreca, poblacio, codi_postal, pais_residencia, email, data_alta, idioma_id, actiu)
VALUES (4, 'mperez', '2222', 'Marc', 'Perez', '45678901D', 'ES', 'Carrer 4', 'Ciutat 4', '23232', 'ES', 'mperezcalm@uoc.edu', '01/12/2015', 2, 'true');

INSERT INTO UNIVERSITAT(universitat_id, acronim, nom, adreca, poblacio, codi_postal, pais, email, data_alta)
VALUES(1, 'UOC', 'Universitat Oberta de Catalunya', 'Adreça 1', 'Població 1', '01234', 'ES', 'uoc@uoc.edu', '01/12/2015');
INSERT INTO UNIVERSITAT(universitat_id, acronim, nom, adreca, poblacio, codi_postal, pais, email, data_alta)
VALUES(2, 'UAB', 'Universitat Autònoma de Barcelona', 'Adreça 2', 'Població 2', '12345', 'ES', 'uab@uab.edu', '01/12/2015');
INSERT INTO UNIVERSITAT(universitat_id, acronim, nom, adreca, poblacio, codi_postal, pais, email, data_alta)
VALUES(3, 'UPC', 'Universitat Politècnica de Catalunya', 'Adreça 3', 'Població 3', '23456', 'ES', 'upc@upc.edu', '01/12/2015');
INSERT INTO UNIVERSITAT(universitat_id, acronim, nom, adreca, poblacio, codi_postal, pais, email, data_alta)
VALUES(4, 'UPF', 'Universitat Pompeu Fabra', 'Adreça 4', 'Població 4', '34567', 'ES', 'upf@upf.edu', '01/12/2015');

INSERT INTO NOM_UNIVERSITAT(nom_universitat_id, universitat_id, codi_idioma, nom) VALUES(1, 1,'ES', 'Universitat Oberta de Catalunya');
INSERT INTO NOM_UNIVERSITAT(nom_universitat_id, universitat_id, codi_idioma, nom) VALUES(2, 2,'ES', 'Universitat Autònoma de Barcelona');
INSERT INTO NOM_UNIVERSITAT(nom_universitat_id, universitat_id, codi_idioma, nom) VALUES(3, 3,'ES', 'Universitat Politècnica de Catalunya');
INSERT INTO NOM_UNIVERSITAT(nom_universitat_id, universitat_id, codi_idioma, nom) VALUES(4, 4,'ES', 'Universitat Pompeu Fabra');

INSERT INTO CENTRE(centre_id, universitat_id, nom_centre, adreca, poblacio, codi_postal, pais, email, data_alta) 
VALUES(1, 1, 'Centre 1', 'Adreça 1', 'Poblacio 1', '01234', 'ES', 'centre1@centre1.edu', '01/12/2015');
INSERT INTO CENTRE(centre_id, universitat_id, nom_centre, adreca, poblacio, codi_postal, pais, email, data_alta) 
VALUES(2, 2, 'Centre 2', 'Adreça 2', 'Poblacio 2', '12345', 'ES', 'centre2@centre2.edu', '01/12/2015');
INSERT INTO CENTRE(centre_id, universitat_id, nom_centre, adreca, poblacio, codi_postal, pais, email, data_alta) 
VALUES(3, 3, 'Centre 3', 'Adreça 3', 'Poblacio 3', '23456', 'ES', 'centre3@centre3.edu', '01/12/2015');
INSERT INTO CENTRE(centre_id, universitat_id, nom_centre, adreca, poblacio, codi_postal, pais, email, data_alta) 
VALUES(4, 4, 'Centre 4', 'Adreça 4', 'Poblacio 4', '34567', 'ES', 'centre4@centre4.edu', '01/12/2015');

INSERT INTO AULA(aula_id, centre_id, codi_aula, nom, capacitat, ubicacio, data_alta) VALUES(1, 1, 'A1C1', 'Aula Centre 1', 50, 'Edifici Centre 1', '01/12/2015');
INSERT INTO AULA(aula_id, centre_id, codi_aula, nom, capacitat, ubicacio, data_alta) VALUES(2, 2, 'A1C2', 'Aula Centre 2', 50, 'Edifici Centre 2', '01/12/2015');
INSERT INTO AULA(aula_id, centre_id, codi_aula, nom, capacitat, ubicacio, data_alta) VALUES(3, 3, 'A1C3', 'Aula Centre 3', 50, 'Edifici Centre 3', '01/12/2015');
INSERT INTO AULA(aula_id, centre_id, codi_aula, nom, capacitat, ubicacio, data_alta) VALUES(4, 4, 'A1C4', 'Aula Centre 4', 50, 'Edifici Centre 4', '01/12/2015');

INSERT INTO RECURS(recurs_id, aula_id, codi_recurs, nom, descripcio, data_alta) VALUES(1, 1, 'R1A1', 'Recurs 1', 'Descripcio del recurs 1 de aula 1', '01/12/2015');
INSERT INTO RECURS(recurs_id, aula_id, codi_recurs, nom, descripcio, data_alta) VALUES(2, 2, 'R1A2', 'Recurs 1', 'Descripcio del recurs 1 de aula 2', '01/12/2015');
INSERT INTO RECURS(recurs_id, aula_id, codi_recurs, nom, descripcio, data_alta) VALUES(3, 3, 'R1A3', 'Recurs 1', 'Descripcio del recurs 1 de aula 3', '01/12/2015');
INSERT INTO RECURS(recurs_id, aula_id, codi_recurs, nom, descripcio, data_alta) VALUES(4, 4, 'R1A4', 'Recurs 1', 'Descripcio del recurs 1 de aula 4', '01/12/2015');

INSERT INTO ACTIVIDAD(id, universitat_id, centre_id, aula_id, titol, data_inici, data_max_inscripcio, minim_percentatge) VALUES(1,1,1,1,'Activitat 1', '10/12/2015', '05/12/2015', '60');
INSERT INTO ACTIVIDAD(id, universitat_id, centre_id, aula_id, titol, data_inici, data_max_inscripcio, minim_percentatge) VALUES(2,2,2,2,'Activitat 2', '15/12/2015', '05/12/2015', '50');
INSERT INTO ACTIVIDAD(id, universitat_id, centre_id, aula_id, titol, data_inici, data_max_inscripcio, minim_percentatge) VALUES(3,3,3,3,'Activitat 3', '20/12/2015', '15/12/2015', '40');
INSERT INTO ACTIVIDAD(id, universitat_id, centre_id, aula_id, titol, data_inici, data_max_inscripcio, minim_percentatge) VALUES(4,4,4,4,'Activitat 4', '25/12/2015', '15/12/2015', '70');

INSERT INTO ASSISTENCIA(id, activitat_id, usuari_id, assistencia) VALUES(1, 1, 1, '1');
INSERT INTO ASSISTENCIA(id, activitat_id, usuari_id, assistencia) VALUES(2, 2, 2, '1');
INSERT INTO ASSISTENCIA(id, activitat_id, usuari_id, assistencia) VALUES(3, 3, 3, '1');
INSERT INTO ASSISTENCIA(id, activitat_id, usuari_id, assistencia) VALUES(4, 4, 4, '1');

INSERT INTO MATRICULA(id, usuari_id, activitat_id, data, estat, beca, numero_Compte) VALUES(1, 1, 1, '01/12/2015', '2', '0', 1234);
INSERT INTO MATRICULA(id, usuari_id, activitat_id, data, estat, beca, numero_Compte) VALUES(2, 2, 2, '01/12/2015', '2', '0', 1212);
INSERT INTO MATRICULA(id, usuari_id, activitat_id, data, estat, beca, numero_Compte) VALUES(3, 3, 3, '01/12/2015', '2', '0', 3434);
INSERT INTO MATRICULA(id, usuari_id, activitat_id, data, estat, beca, numero_Compte) VALUES(4, 4, 4, '01/12/2015', '2', '0', 4321);