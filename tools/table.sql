--user : postgres -- 
--database : voyage --

DROP table personne;
DROP table genre;
DROP table nationalite;

DROP table bouquet_activite;
drop table bouquet;
DROP table categorielieu;
DROP table activite;

CREATE TABLE genre(
    idgenre VARCHAR(250) DEFAULT 'genre' || nextval('genreSequence')::TEXT PRIMARY KEY,
    description VARCHAR(250)
);

CREATE TABLE nationalite(
    idnationalite VARCHAR(250) DEFAULT 'nationalite' || nextval('nationaliteSequence')::TEXT PRIMARY KEY,
    description VARCHAR(250)
);

CREATE TABLE personne(
    idpersonne VARCHAR(250) DEFAULT 'personne' || nextval('personneSequence')::TEXT PRIMARY KEY,
    idgenre VARCHAR(250),
    datedenaissance date,
    adresse VARCHAR(250),
    idnationalite VARCHAR(250),
    Foreign key(idgenre) REFERENCES genre(idgenre),
    Foreign key (idnationalite) REFERENCES nationalite(idnationalite)
);

CREATE TABLE utilisateur(
    idutilisateur VARCHAR(250) DEFAULT 'utilisateur' || nextval('utilisateurSequence')::TEXT PRIMARY KEY,
    email VARCHAR(250),
    mdp VARCHAR(250)
);
-----------------------------------------------------------------------------------------------------------------

CREATE TABLE categorielieu(
    idcategorielieu VARCHAR(250) DEFAULT 'categorielieu' || nextval('categorielieuSequence')::TEXT PRIMARY KEY,
    description VARCHAR(250)
);

CREATE TABLE bouquet(
    idbouquet VARCHAR(250) DEFAULT 'bouquet' || nextval('bouquetSequence')::TEXT PRIMARY KEY,
    description VARCHAR(250)
);

CREATE TABLE activite(
    idactivite VARCHAR(250) DEFAULT 'activite' || nextval('activiteSequence')::TEXT PRIMARY KEY,
    description VARCHAR(250)
);

CREATE TABLE bouquet_activite(
    idbouquetActivite VARCHAR(250) DEFAULT 'bouquet_activite' || nextval('bouquet_activiteSequence')::TEXT PRIMARY KEY,
    idbouquet VARCHAR(250),
    idactivite VARCHAR(250),
    Foreign key (idbouquet) REFERENCES bouquet(idbouquet),
    Foreign Key (idactivite) REFERENCES activite(idactivite)
);