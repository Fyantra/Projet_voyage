DROP SEQUENCE bouquetSequence;
DROP SEQUENCE categorielieuSequence;
DROP SEQUENCE activiteSequence;
DROP SEQUENCE bouquet_activiteSequence;

CREATE SEQUENCE bouquetSequence
    INCREMENT 1
    MINVALUE 1
    START 1
    CACHE 10
    CYCLE;

CREATE SEQUENCE categorielieuSequence
    INCREMENT 1
    MINVALUE 1
    START 1
    CACHE 10
    CYCLE;

CREATE SEQUENCE activiteSequence
    INCREMENT 1
    MINVALUE 1
    START 1
    CACHE 10
    CYCLE;

CREATE SEQUENCE bouquet_activiteSequence
    INCREMENT 1
    MINVALUE 1
    START 1
    CACHE 10
    CYCLE;

CREATE SEQUENCE utilisateurSequence
    INCREMENT 1
    MINVALUE 1
    START 1
    CACHE 10
    CYCLE;

