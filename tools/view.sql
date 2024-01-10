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


CREATE or replace VIEW vue_bouquet_activite_detail AS
SELECT
    ba.idbouquetactivite,
    ba.idbouquet,
    ba.idactivite,
    ba.idcategorielieu,
    dba.iddetailbouquetactivite,
    dba.idsejour,
    cl.description as categorielieu,
    b.description as bouquet,
    s.description as sejour,
    a.description as activite,
    a.prixUnitaire,
    dba.nombreactivite
FROM
    bouquet_activite ba
JOIN

    detailbouquet_activite dba ON ba.idbouquetactivite = dba.idbouquetactivite
JOIN
    categorielieu cl ON ba.idcategorielieu = cl.idcategorielieu
JOIN
    bouquet b ON ba.idbouquet = b.idbouquet
JOIN
    activite a ON ba.idactivite = a.idactivite
JOIN
    sejour s ON dba.idsejour = s.idsejour;


CREATE or replace VIEW vue_voyage_detail as
SELECT 
    v.idvoyage,vba.bouquet,vba.categorielieu,vba.sejour,vba.activite,vba.prixUnitaire,vba.nombreActivite
    from vue_bouquet_activite_detail vba JOIN voyage v ON vba.idcategorielieu = v.idcategorielieu and vba.idbouquet = v.idbouquet and vba.idsejour = v.idsejour; 