CREATE TABLE PAISOS (
codi_pais char(2),
codi_idioma char(2),
nom varchar(255),
CONSTRAINT pais_idioma PRIMARY KEY (codi_pais, codi_idioma)
);

CREATE TABLE IDIOMA (
idioma_id integer,
idioma_nom varchar(2),
CONSTRAINT PK_IDIOMA PRIMARY KEY(idioma_id)
);

CREATE TABLE USUARI (
usuari_id integer,
nom_usuari varchar(64) NOT NULL,
contrasenya text NOT NULL,
noms varchar(128) NOT NULL,
cognoms varchar(128) NOT NULL,
nombre_doc_identif varchar(64) NOT NULL,
pais_doc_identif varchar(2) NOT NULL,
adreca varchar(255) NOT NULL,
poblacio varchar(255) NOT NULL,
codi_postal varchar(32) NOT NULL,
pais_residencia varchar(2) NOT NULL,
telefono varchar(16),
email varchar(128) NOT NULL,
data_alta date NOT NULL,
data_baixa date,
rol varchar(8),
idioma_id integer,
activo boolean,
CONSTRAINT PK_USUARI PRIMARY KEY(usuari_id),
CONSTRAINT FK_IDIOMA FOREIGN KEY(idioma_id) REFERENCES IDIOMA (idioma_id)
);

CREATE TABLE UNIVERSITAT (
universitat_id integer,
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

CREATE TABLE NOM_UNIVERSITAT (
nom_universitat_id integer NOT NULL,
universitat_id integer NOT NULL,
codi_idioma varchar(2) NOT NULL,
nom varchar(255) NOT NULL,
CONSTRAINT PK_NOM_UNIVERSITAT PRIMARY KEY(nom_universitat_id),
CONSTRAINT FK_UNIVERSITAT FOREIGN KEY (universitat_id) REFERENCES UNIVERSITAT(universitat_id)
);

CREATE TABLE USUARI_UNIVERSITAT_GESTOR (
usuari_universitat_gestor_id integer NOT NULL, 
universitat_id integer NOT NULL,
usuari_id integer NOT NULL,
CONSTRAINT PK_USUARI_UNIVERSITAT_GESTOR PRIMARY
KEY(usuari_universitat_gestor_id),
CONSTRAINT FK_UUG_UNIVERSITAT FOREIGN KEY (universitat_id) REFERENCES UNIVERSITAT(universitat_id),
CONSTRAINT FK_UUG_USUARI FOREIGN KEY (usuari_id) REFERENCES USUARI(usuari_id)
);

CREATE TABLE USUARI_UNIVERSITAT_ROL (
usuari_universitat_rol_id integer NOT NULL,
universitat_id integer NOT NULL,
usuari_id integer NOT NULL,
rol varchar(16) NOT NULL,
CONSTRAINT PK_USUARI_UNIVERSITAT_ROL PRIMARY KEY(usuari_universitat_rol_id ),
CONSTRAINT FK_UUR_UNIVERSITAT FOREIGN KEY (universitat_id) REFERENCES UNIVERSITAT(universitat_id),
CONSTRAINT FK_UUR_USUARI FOREIGN KEY (usuari_id) REFERENCES USUARI(usuari_id)
);

CREATE TABLE CENTRE (
centre_id integer,
universitat_id integer NOT NULL,
nom_centre varchar(255) NOT NULL,
adreca varchar(255) NOT NULL,
poblacio varchar(255) NOT NULL,
codi_postal varchar(32) NOT NULL,
pais varchar(2) NOT NULL,
telefono varchar(16),
email varchar(128) NOT NULL,
url varchar(255),
data_alta date NOT NULL,
data_baixa date,
CONSTRAINT PK_CENTRE PRIMARY KEY(centre_id),
CONSTRAINT FK_CENTRE_UNIVERSITAT FOREIGN KEY (universitat_id) REFERENCES UNIVERSITAT(universitat_id)
);

CREATE TABLE AULA (
aula_id integer,
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

CREATE TABLE RECURS (
recurs_id integer,
aula_id integer NOT NULL,
codi_recurs varchar(128) NOT NULL,
nom varchar(255) NOT NULL,
descripcio varchar(255) NOT NULL,
data_alta date NOT NULL,
data_baixa date,
CONSTRAINT PK_RECURS PRIMARY KEY(recurs_id),
CONSTRAINT FK_RECURS_AULA FOREIGN KEY (aula_id) REFERENCES AULA(aula_id)
);

CREATE TABLE ACTIVITATS
(
 id bigint NOT NULL,
 universitat_id bigint NOT NULL,
 centre_id bigint NOT NULL,
 aula_id bigint NOT NULL,
 tipus smallint,
 titol text NOT NULL,
 area text,
 especialitat text,
 decanatura text,
 investigator text,
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

CREATE TABLE ASSISTENCIA
(
 id bigint NOT NULL, -- Identificador
 activitat_id bigint NOT NULL, -- Identificador de la activitat.
 usuari_id bigint NOT NULL, -- Identificador del usuari.
 data_assistencia date NOT NULL, -- Data de lÂ´assistencia
 CONSTRAINT ASSISTANCE_PK PRIMARY KEY (id),
 CONSTRAINT ASSISTANCE_FK1 FOREIGN KEY (activitat_id) REFERENCES ACTIVITATS
(id) MATCH SIMPLE ON UPDATE CASCADE ON DELETE CASCADE,
 CONSTRAINT ASSISTENCE_FK2 FOREIGN KEY (usuari_id) REFERENCES USUARI(usuari_id) MATCH
SIMPLE ON UPDATE CASCADE ON DELETE CASCADE
)
WITH (
 OIDS=FALSE
);

CREATE TABLE MATRICULA
(
 id bigint NOT NULL, -- Identificador
 usuari_id bigint NOT NULL, -- Identificador de l'usuari
 activitat_id bigint NOT NULL, -- Identificador de l'activitat
 data date NOT NULL, -- Data de la matricula
 estat int NOT NULL, -- Estat de la matricula
 beca bit NOT NULL, -- Flag per si hi ha beca
 numero_Compte bigint NOT NULL, -- Numero de compte de pagament
 CONSTRAINT MATRICULA_PK PRIMARY KEY (id),
 CONSTRAINT MATRICULA_FK1 FOREIGN KEY (activitat_id) REFERENCES ACTIVITATS
(id) MATCH SIMPLE ON UPDATE CASCADE ON DELETE CASCADE,
 CONSTRAINT MATRICULA_FK2 FOREIGN KEY (usuari_id) REFERENCES USUARI (usuari_id) MATCH
SIMPLE ON UPDATE CASCADE ON DELETE CASCADE
)
WITH (
 OIDS=FALSE
);