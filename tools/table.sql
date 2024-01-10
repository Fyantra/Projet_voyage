--user : postgres -- 
--database : voyage --

DROP table personne;
DROP table genre;
DROP table nationalite;

DROP table bouquet_activite;
drop table bouquet;
DROP table categorielieu;
DROP table activite;

-- CREATE TABLE genre(
--     idgenre VARCHAR(250) DEFAULT 'genre' || nextval('genreSequence')::TEXT PRIMARY KEY,
--     description VARCHAR(250)
-- );

-- CREATE TABLE nationalite(
--     idnationalite VARCHAR(250) DEFAULT 'nationalite' || nextval('nationaliteSequence')::TEXT PRIMARY KEY,
--     description VARCHAR(250)
-- );

-- CREATE TABLE personne(
--     idpersonne VARCHAR(250) DEFAULT 'personne' || nextval('personneSequence')::TEXT PRIMARY KEY,
--     idgenre VARCHAR(250),
--     datedenaissance date,
--     adresse VARCHAR(250),
--     idnationalite VARCHAR(250),
--     Foreign key(idgenre) REFERENCES genre(idgenre),
--     Foreign key (idnationalite) REFERENCES nationalite(idnationalite)
-- );

-- CREATE TABLE utilisateur(
--     idutilisateur VARCHAR(250) DEFAULT 'utilisateur' || nextval('utilisateurSequence')::TEXT PRIMARY KEY,
--     email VARCHAR(250),
--     mdp VARCHAR(250)
-- );
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
    description VARCHAR(250),
);

alter table activite add COLUMN prixUnitaire double precision;

CREATE TABLE bouquet_activite(  --bouquet_activite_lieu
    idbouquetActivite VARCHAR(250) DEFAULT 'bouquet_activite' || nextval('bouquet_activiteSequence')::TEXT PRIMARY KEY,
    idbouquet VARCHAR(250),
    idactivite VARCHAR(250),
    idcategorielieu VARCHAR(250),
    Foreign key (idbouquet) REFERENCES bouquet(idbouquet),
    Foreign Key (idactivite) REFERENCES activite(idactivite),
    Foreign key (idcategorielieu) REFERENCES categorielieu(idcategorielieu)
);



CREATE TABLE sejour(
    idsejour VARCHAR(250) DEFAULT 'sejour' || nextval('sejourSequence')::TEXT PRIMARY KEY,
    description VARCHAR(250),
    jourmin integer,
    jourmax integer
);

CREATE TABLE voyage(
    idvoyage VARCHAR(250) DEFAULT 'voyage' || nextval('voyageSequence')::TEXT PRIMARY KEY,
    idcategorielieu VARCHAR(250),
    idbouquet VARCHAR(250),
    idsejour VARCHAR(250),
    Foreign key (idcategorielieu) REFERENCES categorielieu(idcategorielieu),
    Foreign key (idbouquet) REFERENCES bouquet(idbouquet),
    Foreign key (idsejour) REFERENCES sejour(idsejour)
);

CREATE TABLE detailBouquet_activite(    --bouquet_activite_lieu fa miaraka @ sejour sy ny nb activite
    iddetailbouquetactivite VARCHAR(250) DEFAULT 'detailBouquet_activite' || nextval('detailBouquet_activiteSequence')::TEXT PRIMARY KEY,
    idbouquetActivite VARCHAR(250),
    idsejour VARCHAR(250),
    nombreActivite int,
    Foreign key (idbouquetActivite) REFERENCES bouquet_activite (idbouquetActivite),
    Foreign key (idsejour) REFERENCES sejour (idsejour)
);

