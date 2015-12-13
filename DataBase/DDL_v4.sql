DROP SEQUENCE IF EXISTS paisos_id_seq CASCADE;
CREATE SEQUENCE paisos_id_seq;

CREATE TABLE PAISOS (
pais_id integer default nextval('paisos_id_seq') not null,
codi_pais char(2),
codi_idioma char(2),
nom varchar(255),
CONSTRAINT PK_PAIS PRIMARY KEY (pais_id)
);

DROP SEQUENCE IF EXISTS idioma_id_seq CASCADE;
CREATE SEQUENCE idioma_id_seq ;

CREATE TABLE IDIOMA (
idioma_id integer default nextval('idioma_id_seq') not null,
idioma_nom varchar(2),
CONSTRAINT PK_IDIOMA PRIMARY KEY(idioma_id)
);

DROP SEQUENCE IF EXISTS rol_id_seq CASCADE;
CREATE SEQUENCE rol_id_seq ;

CREATE TABLE ROL (
id integer default nextval('rol_id_seq') not null,
descripcio varchar(30),
CONSTRAINT PK_ROL PRIMARY KEY(id)
);

DROP SEQUENCE IF EXISTS usuari_id_seq CASCADE;
CREATE SEQUENCE usuari_id_seq ;

CREATE TABLE USUARI (
usuari_id integer default nextval('usuari_id_seq') not null,
nom_usuari varchar(64) NOT NULL,
contrasenya text NOT NULL,
noms varchar(128) NOT NULL,
cognoms varchar(128) NOT NULL,
nombre_doc_identif varchar(64) NOT NULL,
pais_doc_identif integer NOT NULL,
adreca varchar(255) NOT NULL,
poblacio varchar(255) NOT NULL,
codi_postal varchar(32) NOT NULL,
pais_residencia integer NOT NULL,
telefono varchar(16),
email varchar(128) NOT NULL,
data_alta date NOT NULL,
data_baixa date,
rol integer,
idioma_id integer,
actiu boolean,
universitat_id integer,
CONSTRAINT PK_USUARI PRIMARY KEY(usuari_id),
CONSTRAINT FK_IDIOMA FOREIGN KEY(idioma_id) REFERENCES IDIOMA (idioma_id),
CONSTRAINT FK_ROL FOREIGN KEY(rol) REFERENCES ROL(id)
);

DROP SEQUENCE IF EXISTS universitat_id_seq CASCADE;
CREATE SEQUENCE universitat_id_seq ;

CREATE TABLE UNIVERSITAT (
universitat_id integer default nextval('universitat_id_seq') not null,
acronim varchar(10) NOT NULL,
nom varchar(255) NOT NULL,
adreca varchar(255) NOT NULL,
poblacio varchar(255) NOT NULL,
codi_postal varchar(32) NOT NULL,
pais varchar(2) NOT NULL,
telefono varchar(16),
email varchar(128) NOT NULL,
url varchar(255),
data_alta date NOT NULL,
data_baixa date,
CONSTRAINT PK_UNIVERSITAT PRIMARY KEY(universitat_id)
);

DROP SEQUENCE IF EXISTS nom_universitat_id_seq CASCADE;
CREATE SEQUENCE nom_universitat_id_seq ;

CREATE TABLE NOM_UNIVERSITAT (
nom_universitat_id integer default nextval('nom_universitat_id_seq') NOT NULL,
universitat_id integer NOT NULL,
codi_idioma varchar(2) NOT NULL,
nom varchar(255) NOT NULL,
CONSTRAINT PK_NOM_UNIVERSITAT PRIMARY KEY(nom_universitat_id),
CONSTRAINT FK_UNIVERSITAT FOREIGN KEY (universitat_id) REFERENCES UNIVERSITAT(universitat_id)
);

DROP SEQUENCE IF EXISTS centre_id_seq CASCADE;
CREATE SEQUENCE centre_id_seq ;

CREATE TABLE CENTRE (
centre_id integer default nextval('centre_id_seq') not null,
universitat_id integer NOT NULL,
nom_centre varchar(255) NOT NULL,
adreca varchar(255) NOT NULL,
poblacio varchar(255) NOT NULL,
codi_postal varchar(32) NOT NULL,
pais integer NOT NULL,
telefono varchar(16),
email varchar(128) NOT NULL,
url varchar(255),
data_alta date NOT NULL,
data_baixa date,
CONSTRAINT PK_CENTRE PRIMARY KEY(centre_id),
CONSTRAINT FK_CENTRE_UNIVERSITAT FOREIGN KEY (universitat_id) REFERENCES UNIVERSITAT(universitat_id),
CONSTRAINT FK_CENTRE_PAIS FOREIGN KEY(pais) REFERENCES PAISOS(pais_id)
);

DROP SEQUENCE IF EXISTS aula_id_seq CASCADE;
CREATE SEQUENCE aula_id_seq ;

CREATE TABLE AULA (
aula_id integer default nextval('aula_id_seq') not null,
centre_id integer NOT NULL,
codi_aula varchar(128) NOT NULL,
nom varchar(255) NOT NULL,
capacitat integer NOT NULL,
ubicacio varchar(255) NOT NULL,
data_alta date NOT NULL,
data_baixa date,
CONSTRAINT PK_AULA PRIMARY KEY(aula_id),
CONSTRAINT FK_AULA_CENTRE FOREIGN KEY (centre_id) REFERENCES CENTRE(centre_id)
);

DROP SEQUENCE IF EXISTS recurs_id_seq CASCADE;
CREATE SEQUENCE recurs_id_seq ;

CREATE TABLE RECURS (
recurs_id integer default nextval('recurs_id_seq') not null,
aula_id integer NOT NULL,
codi_recurs varchar(128) NOT NULL,
nom varchar(255) NOT NULL,
descripcio varchar(255) NOT NULL,
data_alta date NOT NULL,
data_baixa date,
CONSTRAINT PK_RECURS PRIMARY KEY(recurs_id),
CONSTRAINT FK_RECURS_AULA FOREIGN KEY (aula_id) REFERENCES AULA(aula_id)
);

DROP SEQUENCE IF EXISTS activitat_id_seq CASCADE;
CREATE SEQUENCE activitat_id_seq ;

CREATE TABLE ACTIVIDAD
(
 id integer default nextval('activitat_id_seq') NOT NULL,
 universitat_id bigint NOT NULL,
 centre_id bigint NOT NULL,
 aula_id bigint NOT NULL,
 tipus smallint,
 titol text NOT NULL,
 area text,
 especialitat text,
 decanatura text,
 investigador text,
 data_inici date NOT NULL,
 data_fi date,
 data_max_inscripcio date NOT NULL,
 preu double precision,
 minim_percentatge double precision NOT NULL,
 cancelada smallint, -- Indica si la activitat estÃ  cancelÂ·lada o no
 CONSTRAINT ACTIVITIES_PK PRIMARY KEY (id)
)
WITH (
 OIDS=FALSE
);

DROP SEQUENCE IF EXISTS assistencia_id_seq CASCADE;
CREATE SEQUENCE assistencia_id_seq ;

CREATE TABLE ASSISTENCIA
(
 id integer default nextval('assistencia_id_seq') NOT NULL, -- Identificador
 activitat_id bigint NOT NULL, -- Identificador de la activitat.
 usuari_id bigint NOT NULL, -- Identificador del usuari.
 assistencia boolean NOT NULL,
 CONSTRAINT ASSISTANCE_PK PRIMARY KEY (id),
 CONSTRAINT ASSISTANCE_FK1 FOREIGN KEY (activitat_id) REFERENCES ACTIVIDAD
(id) MATCH SIMPLE ON UPDATE CASCADE ON DELETE CASCADE,
 CONSTRAINT ASSISTENCE_FK2 FOREIGN KEY (usuari_id) REFERENCES USUARI(usuari_id) MATCH
SIMPLE ON UPDATE CASCADE ON DELETE CASCADE
)
WITH (
 OIDS=FALSE
);

DROP SEQUENCE IF EXISTS matricula_id_seq CASCADE;
CREATE SEQUENCE matricula_id_seq ;

CREATE TABLE MATRICULA
(
 id integer default nextval('matricula_id_seq') NOT NULL, -- Identificador
 usuari_id bigint NOT NULL, -- Identificador de l'usuari
 activitat_id bigint NOT NULL, -- Identificador de l'activitat
 data date NOT NULL, -- Data de la matricula
 estat int NOT NULL, -- Estat de la matricula
 beca bit NOT NULL, -- Flag per si hi ha beca
 numero_Compte bigint NOT NULL, -- Numero de compte de pagament
 CONSTRAINT MATRICULA_PK PRIMARY KEY (id),
 CONSTRAINT MATRICULA_FK1 FOREIGN KEY (activitat_id) REFERENCES ACTIVIDAD
(id) MATCH SIMPLE ON UPDATE CASCADE ON DELETE CASCADE,
 CONSTRAINT MATRICULA_FK2 FOREIGN KEY (usuari_id) REFERENCES USUARI (usuari_id) MATCH
SIMPLE ON UPDATE CASCADE ON DELETE CASCADE
)
WITH (
 OIDS=FALSE
);

CREATE TABLE curso
(
  id serial NOT NULL,
  nombre character varying(50),
  minasistencia integer,
  plandocente text,
  bibliografia text,
  activo bit(1),
  fecha_inicio date,
  fecha_finalizacion date,
  fechainactividad date,
  idprofesor integer,
  idaula integer,
  CONSTRAINT curso_pkey PRIMARY KEY (id),
  CONSTRAINT curso_idaula_fkey FOREIGN KEY (idaula)
      REFERENCES aula (aula_id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT curso_idprofesor_fkey FOREIGN KEY (idprofesor)
      REFERENCES usuari (usuari_id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);

CREATE TABLE grupo
(
  grupoid integer NOT NULL,
  nombre character varying(30),
  maxalumnos integer,
  plazasdisponibles integer,
  fechainicio date,
  fechafin date,
  grupovigente bit(1),
  turno integer,
  idaula integer,
  idprofesor integer,
  idcurso integer,
  CONSTRAINT grupo_pkey PRIMARY KEY (grupoid),
  CONSTRAINT grupo_idaula_fkey FOREIGN KEY (idaula)
      REFERENCES aula (aula_id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT grupo_idcurso_fkey FOREIGN KEY (idcurso)
      REFERENCES curso (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT grupo_idprofesor_fkey FOREIGN KEY (idprofesor)
      REFERENCES usuari (usuari_id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);
