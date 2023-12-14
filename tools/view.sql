CREATE VIEW vue_bouquet_activite AS
SELECT
    ba.idbouquetActivite,
    b.idbouquet,
    b.description AS description_bouquet,
    a.idactivite,
    a.description AS description_activite
FROM
    bouquet_activite ba
JOIN
    bouquet b ON ba.idbouquet = b.idbouquet
JOIN
    activite a ON ba.idactivite = a.idactivite;