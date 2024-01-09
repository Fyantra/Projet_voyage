-----------------TABLE UTILISATEUR-----------------
INSERT INTO utilisateur(email, mdp) VALUES
('user1@gmail.com','0000'),
('user2@gmail.com','0000');

INSERT INTO sejour(description, jourmin, jourmax) VALUES
('court', 1, 5),
('moyen', 6, 10),
('long', 11, 15);

-------------------------------------------------------------------
INSERT INTO bouquet_activite(idbouquet, idactivite, idcategorielieu) VALUES
('bouquet1', 'activite81', 'categorielieu11'),
('bouquet1', 'activite91', 'categorielieu11'),
('bouquet1', 'activite101', 'categorielieu11');

INSERT INTO bouquet_activite(idbouquet, idactivite, idcategorielieu) VALUES
('bouquet11', 'activite21', 'categorielieu21'),
('bouquet11', 'activite31', 'categorielieu21'),
('bouquet11', 'activite41', 'categorielieu21');

INSERT INTO bouquet_activite(idbouquet, idactivite, idcategorielieu) VALUES
('bouquet21', 'activite51', 'categorielieu1'),
('bouquet21', 'activite61', 'categorielieu1'),
('bouquet21', 'activite71', 'categorielieu1');
-------------------------------------------------------------

------------------------------------------------------------------------
INSERT INTO detailBouquet_activite(idbouquetActivite, idsejour, nombreActivite) VALUES
('bouquet_activite1', 'sejour1', 2),
('bouquet_activite1', 'sejour2', 4);

INSERT INTO detailBouquet_activite(idbouquetActivite, idsejour, nombreActivite) VALUES
('bouquet_activite2', 'sejour1', 2),
('bouquet_activite2', 'sejour2', 3);

INSERT INTO detailBouquet_activite(idbouquetActivite, idsejour, nombreActivite) VALUES
('bouquet_activite3', 'sejour1', 1),
('bouquet_activite3', 'sejour2', 3);

INSERT INTO detailBouquet_activite(idbouquetActivite, idsejour, nombreActivite) VALUES
('bouquet_activite4', 'sejour1', 3),
('bouquet_activite4', 'sejour2', 5),
('bouquet_activite4', 'sejour3', 7);

INSERT INTO detailBouquet_activite(idbouquetActivite, idsejour, nombreActivite) VALUES
('bouquet_activite5', 'sejour1', 2),
('bouquet_activite5', 'sejour2', 3),
('bouquet_activite5', 'sejour3', 5);

INSERT INTO detailBouquet_activite(idbouquetActivite, idsejour, nombreActivite) VALUES
('bouquet_activite6', 'sejour1', 2),
('bouquet_activite6', 'sejour2', 4),
('bouquet_activite6', 'sejour2', 8);

-----------------------------------------------------------------------
