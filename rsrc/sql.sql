-- Database generated with pgModeler (PostgreSQL Database Modeler).
-- pgModeler version: 1.0.0
-- PostgreSQL version: 15.0
-- Project Site: pgmodeler.io
-- Model Author: ---

-- Database creation must be performed outside a multi lined SQL file. 
-- These commands were put in this file only as a convenience.
-- 
-- object: revize | type: DATABASE --
-- DROP DATABASE IF EXISTS revize;
CREATE DATABASE revize;
-- ddl-end --


-- object: public.revize | type: TABLE --
-- DROP TABLE IF EXISTS public.revize CASCADE;
CREATE TABLE public.revize (
	id_revize bigserial NOT NULL,
	datum_vypracovani date NOT NULL,
	datum_ukonceni_revize date NOT NULL,
	datum_predani_revize date NOT NULL,
	je_nova_instalace bool NOT NULL,
	distribucni_sit text NOT NULL,
	pocet_fazi smallint NOT NULL,
	pocet_stringu smallint NOT NULL,
	jisteni text NOT NULL,
	prepetova_ochrana smallint NOT NULL,
	fotka_src text NOT NULL,
	objednatele_id_objednatele bigint,
	id_revidovane_objekty_revidovane_objekty bigint,
	CONSTRAINT revize_pk PRIMARY KEY (id_revize)
);
-- ddl-end --
ALTER TABLE public.revize OWNER TO postgres;
-- ddl-end --

-- object: public.stridace | type: TABLE --
-- DROP TABLE IF EXISTS public.stridace CASCADE;
CREATE TABLE public.stridace (
	id_stridace bigserial NOT NULL,
	pocet smallint NOT NULL,
	nazev text NOT NULL,
	vyrobce text NOT NULL,
	vyrobni_cislo text NOT NULL,
	id_revize_revize bigint,
	CONSTRAINT stridace_pk PRIMARY KEY (id_stridace)
);
-- ddl-end --
ALTER TABLE public.stridace OWNER TO postgres;
-- ddl-end --

-- object: public.objednatele | type: TABLE --
-- DROP TABLE IF EXISTS public.objednatele CASCADE;
CREATE TABLE public.objednatele (
	objednatele_id bigserial NOT NULL,
	nazev text NOT NULL,
	zeme text NOT NULL,
	mesto text NOT NULL,
	psc text NOT NULL,
	ulice text NOT NULL,
	CONSTRAINT objednatele_pk PRIMARY KEY (objednatele_id)
);
-- ddl-end --
ALTER TABLE public.objednatele OWNER TO postgres;
-- ddl-end --

-- object: public.revidovane_objekty | type: TABLE --
-- DROP TABLE IF EXISTS public.revidovane_objekty CASCADE;
CREATE TABLE public.revidovane_objekty (
	id_revidovane_objekty bigserial NOT NULL,
	zeme text NOT NULL,
	mesto text NOT NULL,
	psc text NOT NULL,
	ulice text NOT NULL,
	je_bytovy_dum bool NOT NULL,
	CONSTRAINT revidovane_objekty_pk PRIMARY KEY (id_revidovane_objekty)
);
-- ddl-end --
ALTER TABLE public.revidovane_objekty OWNER TO postgres;
-- ddl-end --

-- object: public.panely | type: TABLE --
-- DROP TABLE IF EXISTS public.panely CASCADE;
CREATE TABLE public.panely (
	id_panely bigserial NOT NULL,
	vyrobni_cislo text NOT NULL,
	id_typy_panelu_typy_panelu bigint,
	id_revize_revize bigint,
	CONSTRAINT panely_pk PRIMARY KEY (id_panely)
);
-- ddl-end --
ALTER TABLE public.panely OWNER TO postgres;
-- ddl-end --

-- object: public.typy_panelu | type: TABLE --
-- DROP TABLE IF EXISTS public.typy_panelu CASCADE;
CREATE TABLE public.typy_panelu (
	id_typy_panelu bigserial NOT NULL,
	typ text NOT NULL,
	vykon smallint NOT NULL,
	CONSTRAINT typy_panelu_pk PRIMARY KEY (id_typy_panelu)
);
-- ddl-end --
ALTER TABLE public.typy_panelu OWNER TO postgres;
-- ddl-end --

-- object: revize_fk | type: CONSTRAINT --
-- ALTER TABLE public.stridace DROP CONSTRAINT IF EXISTS revize_fk CASCADE;
ALTER TABLE public.stridace ADD CONSTRAINT revize_fk FOREIGN KEY (id_revize_revize)
REFERENCES public.revize (id_revize) MATCH FULL
ON DELETE SET NULL ON UPDATE CASCADE;
-- ddl-end --

-- object: objednatele_fk | type: CONSTRAINT --
-- ALTER TABLE public.revize DROP CONSTRAINT IF EXISTS objednatele_fk CASCADE;
ALTER TABLE public.revize ADD CONSTRAINT objednatele_fk FOREIGN KEY (objednatele_id_objednatele)
REFERENCES public.objednatele (objednatele_id) MATCH FULL
ON DELETE SET NULL ON UPDATE CASCADE;
-- ddl-end --

-- object: revidovane_objekty_fk | type: CONSTRAINT --
-- ALTER TABLE public.revize DROP CONSTRAINT IF EXISTS revidovane_objekty_fk CASCADE;
ALTER TABLE public.revize ADD CONSTRAINT revidovane_objekty_fk FOREIGN KEY (id_revidovane_objekty_revidovane_objekty)
REFERENCES public.revidovane_objekty (id_revidovane_objekty) MATCH FULL
ON DELETE SET NULL ON UPDATE CASCADE;
-- ddl-end --

-- object: revize_uq | type: CONSTRAINT --
-- ALTER TABLE public.revize DROP CONSTRAINT IF EXISTS revize_uq CASCADE;
ALTER TABLE public.revize ADD CONSTRAINT revize_uq UNIQUE (id_revidovane_objekty_revidovane_objekty);
-- ddl-end --

-- object: typy_panelu_fk | type: CONSTRAINT --
-- ALTER TABLE public.panely DROP CONSTRAINT IF EXISTS typy_panelu_fk CASCADE;
ALTER TABLE public.panely ADD CONSTRAINT typy_panelu_fk FOREIGN KEY (id_typy_panelu_typy_panelu)
REFERENCES public.typy_panelu (id_typy_panelu) MATCH FULL
ON DELETE SET NULL ON UPDATE CASCADE;
-- ddl-end --

-- object: revize_fk | type: CONSTRAINT --
-- ALTER TABLE public.panely DROP CONSTRAINT IF EXISTS revize_fk CASCADE;
ALTER TABLE public.panely ADD CONSTRAINT revize_fk FOREIGN KEY (id_revize_revize)
REFERENCES public.revize (id_revize) MATCH FULL
ON DELETE SET NULL ON UPDATE CASCADE;
-- ddl-end --


