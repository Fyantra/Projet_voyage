SELECT
     idvoyage,bouquet,categorielieu,sejour,
    SUM(prixUnitaire * nombreactivite) AS somme_activites
FROM
    vue_voyage_detail   
GROUP BY
    idvoyage,bouquet,categorielieu,sejour
HAVING
    SUM(prixUnitaire * nombreactivite) BETWEEN 2 AND 20000000000;
