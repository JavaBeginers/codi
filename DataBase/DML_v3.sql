INSERT INTO PAISOS (pais_id, codi_pais, codi_idioma, nom) VALUES (1, 'DE','ES','Alemania' );
INSERT INTO PAISOS (pais_id, codi_pais, codi_idioma, nom) VALUES (2, 'AT','ES','Austria' );
INSERT INTO PAISOS (pais_id, codi_pais, codi_idioma, nom) VALUES (3, 'BE','ES','Belgica' );
INSERT INTO PAISOS (pais_id, codi_pais, codi_idioma, nom) VALUES (4, 'ES','ES','España' );

INSERT INTO IDIOMA (idioma_id, idioma_nom) VALUES (1, 'ES');
INSERT INTO IDIOMA (idioma_id, idioma_nom) VALUES (2, 'CA');
INSERT INTO IDIOMA (idioma_id, idioma_nom) VALUES (3, 'EN');

INSERT INTO ROL(id, descripcio) VALUES(1, 'administrador');
INSERT INTO ROL(id, descripcio) VALUES(2, 'secretaria');
INSERT INTO ROL(id, descripcio) VALUES(3, 'professor');
INSERT INTO ROL(id, descripcio) VALUES(4, 'usuari');

INSERT INTO USUARI(usuari_id, nom_usuari, contrasenya, noms, cognoms, nombre_doc_identif, pais_doc_identif, adreca, poblacio, codi_postal, pais_residencia, email, data_alta, idioma_id, actiu)
VALUES (1, 'jdominguez', '1234', 'Joan Antoni', 'Dominguez', '12345678A', 4, 'Carrer 1', 'Ciutat 1', '54321', 4, 'jdominguez@uoc.edu', '01/12/2015', 2, 'true');
INSERT INTO USUARI(usuari_id, nom_usuari, contrasenya, noms, cognoms, nombre_doc_identif, pais_doc_identif, adreca, poblacio, codi_postal, pais_residencia, email, data_alta, idioma_id, actiu)
VALUES (2, 'cceballos', '1111', 'Cristian', 'Ceballos', '23456789B', 4, 'Carrer 2', 'Ciutat 2', '12345', 4, 'cceballos@uoc.edu', '01/12/2015', 1, 'true');
INSERT INTO USUARI(usuari_id, nom_usuari, contrasenya, noms, cognoms, nombre_doc_identif, pais_doc_identif, adreca, poblacio, codi_postal, pais_residencia, email, data_alta, idioma_id, actiu)
VALUES (3, 'imargarit', '4321', 'Ivan', 'Margarit', '34567890C', 4, 'Carrer 3', 'Ciutat 3', '12121', 4, 'imargarit@uoc.edu', '01/12/2015', 1, 'true');
INSERT INTO USUARI(usuari_id, nom_usuari, contrasenya, noms, cognoms, nombre_doc_identif, pais_doc_identif, adreca, poblacio, codi_postal, pais_residencia, email, data_alta, idioma_id, actiu)
VALUES (4, 'mperez', '2222', 'Marc', 'Perez', '45678901D', 4, 'Carrer 4', 'Ciutat 4', '23232', 4, 'mperezcalm@uoc.edu', '01/12/2015', 2, 'true');

INSERT INTO UNIVERSITAT(universitat_id, acronim, nom, adreca, poblacio, codi_postal, pais, email, data_alta)
VALUES(1, 'UOC', 'Universitat Oberta de Catalunya', 'Adreça 1', 'Població 1', '01234', 4, 'uoc@uoc.edu', '01/12/2015');
INSERT INTO UNIVERSITAT(universitat_id, acronim, nom, adreca, poblacio, codi_postal, pais, email, data_alta)
VALUES(2, 'UAB', 'Universitat Autònoma de Barcelona', 'Adreça 2', 'Població 2', '12345', 4, 'uab@uab.edu', '01/12/2015');
INSERT INTO UNIVERSITAT(universitat_id, acronim, nom, adreca, poblacio, codi_postal, pais, email, data_alta)
VALUES(3, 'UPC', 'Universitat Politècnica de Catalunya', 'Adreça 3', 'Població 3', '23456', 4, 'upc@upc.edu', '01/12/2015');
INSERT INTO UNIVERSITAT(universitat_id, acronim, nom, adreca, poblacio, codi_postal, pais, email, data_alta)
VALUES(4, 'UPF', 'Universitat Pompeu Fabra', 'Adreça 4', 'Població 4', '34567', 4, 'upf@upf.edu', '01/12/2015');

INSERT INTO NOM_UNIVERSITAT(nom_universitat_id, universitat_id, codi_idioma, nom) VALUES(1, 1,4, 'Universitat Oberta de Catalunya');
INSERT INTO NOM_UNIVERSITAT(nom_universitat_id, universitat_id, codi_idioma, nom) VALUES(2, 2,4, 'Universitat Autònoma de Barcelona');
INSERT INTO NOM_UNIVERSITAT(nom_universitat_id, universitat_id, codi_idioma, nom) VALUES(3, 3,4, 'Universitat Politècnica de Catalunya');
INSERT INTO NOM_UNIVERSITAT(nom_universitat_id, universitat_id, codi_idioma, nom) VALUES(4, 4,4, 'Universitat Pompeu Fabra');

INSERT INTO CENTRE(centre_id, universitat_id, nom_centre, adreca, poblacio, codi_postal, pais, email, data_alta) 
VALUES(1, 1, 'Centre 1', 'Adreça 1', 'Poblacio 1', '01234', 4, 'centre1@centre1.edu', '01/12/2015');
INSERT INTO CENTRE(centre_id, universitat_id, nom_centre, adreca, poblacio, codi_postal, pais, email, data_alta) 
VALUES(2, 2, 'Centre 2', 'Adreça 2', 'Poblacio 2', '12345', 4, 'centre2@centre2.edu', '01/12/2015');
INSERT INTO CENTRE(centre_id, universitat_id, nom_centre, adreca, poblacio, codi_postal, pais, email, data_alta) 
VALUES(3, 3, 'Centre 3', 'Adreça 3', 'Poblacio 3', '23456', 4, 'centre3@centre3.edu', '01/12/2015');
INSERT INTO CENTRE(centre_id, universitat_id, nom_centre, adreca, poblacio, codi_postal, pais, email, data_alta) 
VALUES(4, 4, 'Centre 4', 'Adreça 4', 'Poblacio 4', '34567', 4, 'centre4@centre4.edu', '01/12/2015');

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

INSERT INTO CURSO(id, nombre, minasistencia, activo, fecha_inicio, fecha_finalizacion) VALUES(1, 'Curso 1', 100, '1', '01/12/2015', '01/06/2016');
INSERT INTO CURSO(id, nombre, minasistencia, activo, fecha_inicio, fecha_finalizacion) VALUES(2, 'Curso 2', 100, '1', '01/12/2015', '01/06/2016');
INSERT INTO CURSO(id, nombre, minasistencia, activo, fecha_inicio, fecha_finalizacion) VALUES(3, 'Curso 3', 100, '1', '01/12/2015', '01/06/2016');
INSERT INTO CURSO(id, nombre, minasistencia, activo, fecha_inicio, fecha_finalizacion) VALUES(4, 'Curso 4', 100, '1', '01/12/2015', '01/06/2016');

INSERT INTO GRUPO(grupoid, nombre, maxalumnos, plazasdisponibles, fechainicio, fechafin, grupovigente, turno) VALUES(1, 'Grupo 1', 50, 50, '01/12/2015', '01/06/2016', '1', 1);
INSERT INTO GRUPO(grupoid, nombre, maxalumnos, plazasdisponibles, fechainicio, fechafin, grupovigente, turno) VALUES(2, 'Grupo 2', 50, 50, '01/12/2015', '01/06/2016', '1', 1);
INSERT INTO GRUPO(grupoid, nombre, maxalumnos, plazasdisponibles, fechainicio, fechafin, grupovigente, turno) VALUES(3, 'Grupo 3', 50, 50, '01/12/2015', '01/06/2016', '1', 1);
INSERT INTO GRUPO(grupoid, nombre, maxalumnos, plazasdisponibles, fechainicio, fechafin, grupovigente, turno) VALUES(4, 'Grupo 4', 50, 50, '01/12/2015', '01/06/2016', '1', 1);