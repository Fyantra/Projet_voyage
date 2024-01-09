CREATE VIEW vue_bouquet_activite AS
SELECT
    ba.idbouquetActivite,
    b.idbouquet,
    b.description AS description_bouquet,
    a.idactivite,
    a.description AS description_activite,
    cl.idcategorielieu,
    cl.description
FROM
    bouquet_activite ba
JOIN
    bouquet b ON ba.idbouquet = b.idbouquet
JOIN
    activite a ON ba.idactivite = a.idactivite
JOIN
    categorielieu cl ON ba.idcategorielieu = cl.idcategorielieu;

CREATE VIEW vue_relationnelle AS
SELECT
    ba.idbouquetactivite,
    ba.idbouquet,
    ba.idactivite,
    ba.idcategorielieu,
    c.description AS categorielieu_description,
    b.description AS bouquet_description,
    a.description AS activite_description
    -- dba.iddetailbouquetactivite,
    -- dba.idsejour,
    -- dba.nombreactivite
FROM
    bouquet_activite ba
JOIN bouquet b ON ba.idbouquet = b.idbouquet
JOIN activite a ON ba.idactivite = a.idactivite
JOIN categorielieu c ON ba.idcategorielieu = c.idcategorielieu
JOIN detailbouquet_activite dba ON ba.idbouquetactivite = dba.idbouquetactivite;


CREATE VIEW vue_voyage_detail AS
SELECT
    v.idvoyage,
    cl.description AS categorielieu_description,
    b.description AS bouquet_description,
    a.description AS activite_description,
    s.description AS sejour_description,
    dba.nombreactivite,
    ba.idbouquetactivite,
    ba.idactivite,
    ba.idcategorielieu AS ba_idcategorielieu,
    dba.iddetailbouquetactivite
FROM
    voyage v
JOIN
    categorielieu cl ON v.idcategorielieu = cl.idcategorielieu
JOIN
    bouquet b ON v.idbouquet = b.idbouquet
JOIN
    sejour s ON v.idsejour = s.idsejour
JOIN
    bouquet_activite ba ON v.idcategorielieu = ba.idcategorielieu AND v.idbouquet = ba.idbouquet
JOIN
    activite a ON ba.idactivite = a.idactivite
JOIN
    detailbouquet_activite dba ON ba.idbouquetactivite = dba.idbouquetactivite;
