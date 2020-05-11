--
-- PostgreSQL database dump
--

-- Dumped from database version 10.5
-- Dumped by pg_dump version 10.5

-- Started on 2020-05-10 21:34:41

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET client_min_messages = warning;
SET row_security = off;

--
-- TOC entry 2861 (class 1262 OID 16690)
-- Name: carrito; Type: DATABASE; Schema: -; Owner: postgres
--

CREATE DATABASE carrito WITH TEMPLATE = template0 ENCODING = 'UTF8' LC_COLLATE = 'English_United States.1252' LC_CTYPE = 'English_United States.1252';


ALTER DATABASE carrito OWNER TO postgres;

\connect carrito

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET client_min_messages = warning;
SET row_security = off;

--
-- TOC entry 1 (class 3079 OID 12924)
-- Name: plpgsql; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;


--
-- TOC entry 2863 (class 0 OID 0)
-- Dependencies: 1
-- Name: EXTENSION plpgsql; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';


SET default_tablespace = '';

SET default_with_oids = false;

--
-- TOC entry 197 (class 1259 OID 17257)
-- Name: compras; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.compras (
    id bigint NOT NULL,
    fecha_compra timestamp without time zone,
    valor_total real NOT NULL,
    usuario_id bigint,
    tipo_carro integer
);


ALTER TABLE public.compras OWNER TO postgres;

--
-- TOC entry 196 (class 1259 OID 17255)
-- Name: compras_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.compras_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.compras_id_seq OWNER TO postgres;

--
-- TOC entry 2864 (class 0 OID 0)
-- Dependencies: 196
-- Name: compras_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.compras_id_seq OWNED BY public.compras.id;


--
-- TOC entry 199 (class 1259 OID 17265)
-- Name: detalle_compras; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.detalle_compras (
    id bigint NOT NULL,
    cantidad integer NOT NULL,
    fecha_compra timestamp without time zone,
    compra_id bigint,
    producto_id bigint
);


ALTER TABLE public.detalle_compras OWNER TO postgres;

--
-- TOC entry 198 (class 1259 OID 17263)
-- Name: detalle_compras_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.detalle_compras_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.detalle_compras_id_seq OWNER TO postgres;

--
-- TOC entry 2865 (class 0 OID 0)
-- Dependencies: 198
-- Name: detalle_compras_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.detalle_compras_id_seq OWNED BY public.detalle_compras.id;


--
-- TOC entry 201 (class 1259 OID 17273)
-- Name: dias_promociales; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.dias_promociales (
    id bigint NOT NULL,
    descripcion character varying(255),
    fecha date
);


ALTER TABLE public.dias_promociales OWNER TO postgres;

--
-- TOC entry 200 (class 1259 OID 17271)
-- Name: dias_promociales_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.dias_promociales_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.dias_promociales_id_seq OWNER TO postgres;

--
-- TOC entry 2866 (class 0 OID 0)
-- Dependencies: 200
-- Name: dias_promociales_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.dias_promociales_id_seq OWNED BY public.dias_promociales.id;


--
-- TOC entry 203 (class 1259 OID 17281)
-- Name: productos; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.productos (
    id bigint NOT NULL,
    nombre character varying(255),
    valor real NOT NULL
);


ALTER TABLE public.productos OWNER TO postgres;

--
-- TOC entry 202 (class 1259 OID 17279)
-- Name: productos_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.productos_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.productos_id_seq OWNER TO postgres;

--
-- TOC entry 2867 (class 0 OID 0)
-- Dependencies: 202
-- Name: productos_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.productos_id_seq OWNED BY public.productos.id;


--
-- TOC entry 205 (class 1259 OID 17289)
-- Name: usuarios; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.usuarios (
    id bigint NOT NULL,
    documento_numero character varying(255),
    nombre character varying(255)
);


ALTER TABLE public.usuarios OWNER TO postgres;

--
-- TOC entry 207 (class 1259 OID 17300)
-- Name: usuarios_historia; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.usuarios_historia (
    id bigint NOT NULL,
    fecha_fin timestamp without time zone,
    fecha_inicio timestamp without time zone,
    vip boolean,
    usuario_id bigint
);


ALTER TABLE public.usuarios_historia OWNER TO postgres;

--
-- TOC entry 206 (class 1259 OID 17298)
-- Name: usuarios_historia_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.usuarios_historia_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.usuarios_historia_id_seq OWNER TO postgres;

--
-- TOC entry 2868 (class 0 OID 0)
-- Dependencies: 206
-- Name: usuarios_historia_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.usuarios_historia_id_seq OWNED BY public.usuarios_historia.id;


--
-- TOC entry 204 (class 1259 OID 17287)
-- Name: usuarios_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.usuarios_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.usuarios_id_seq OWNER TO postgres;

--
-- TOC entry 2869 (class 0 OID 0)
-- Dependencies: 204
-- Name: usuarios_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.usuarios_id_seq OWNED BY public.usuarios.id;


--
-- TOC entry 2701 (class 2604 OID 17260)
-- Name: compras id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.compras ALTER COLUMN id SET DEFAULT nextval('public.compras_id_seq'::regclass);


--
-- TOC entry 2702 (class 2604 OID 17268)
-- Name: detalle_compras id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.detalle_compras ALTER COLUMN id SET DEFAULT nextval('public.detalle_compras_id_seq'::regclass);


--
-- TOC entry 2703 (class 2604 OID 17276)
-- Name: dias_promociales id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.dias_promociales ALTER COLUMN id SET DEFAULT nextval('public.dias_promociales_id_seq'::regclass);


--
-- TOC entry 2704 (class 2604 OID 17284)
-- Name: productos id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.productos ALTER COLUMN id SET DEFAULT nextval('public.productos_id_seq'::regclass);


--
-- TOC entry 2705 (class 2604 OID 17292)
-- Name: usuarios id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.usuarios ALTER COLUMN id SET DEFAULT nextval('public.usuarios_id_seq'::regclass);


--
-- TOC entry 2706 (class 2604 OID 17303)
-- Name: usuarios_historia id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.usuarios_historia ALTER COLUMN id SET DEFAULT nextval('public.usuarios_historia_id_seq'::regclass);


--
-- TOC entry 2845 (class 0 OID 17257)
-- Dependencies: 197
-- Data for Name: compras; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.compras (id, fecha_compra, valor_total, usuario_id, tipo_carro) VALUES (22, '2020-03-02 21:00:00', 11500, 2, 2);
INSERT INTO public.compras (id, fecha_compra, valor_total, usuario_id, tipo_carro) VALUES (23, '2020-04-14 21:00:00', 1350, 2, 0);
INSERT INTO public.compras (id, fecha_compra, valor_total, usuario_id, tipo_carro) VALUES (29, '2020-05-10 00:00:00', 2550, 1, 1);


--
-- TOC entry 2847 (class 0 OID 17265)
-- Dependencies: 199
-- Data for Name: detalle_compras; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.detalle_compras (id, cantidad, fecha_compra, compra_id, producto_id) VALUES (1, 11, NULL, 22, 5);
INSERT INTO public.detalle_compras (id, cantidad, fecha_compra, compra_id, producto_id) VALUES (2, 1, NULL, 22, 4);
INSERT INTO public.detalle_compras (id, cantidad, fecha_compra, compra_id, producto_id) VALUES (3, 1, NULL, 22, 3);
INSERT INTO public.detalle_compras (id, cantidad, fecha_compra, compra_id, producto_id) VALUES (4, 4, NULL, 23, 1);
INSERT INTO public.detalle_compras (id, cantidad, fecha_compra, compra_id, producto_id) VALUES (5, 3, NULL, 23, 2);
INSERT INTO public.detalle_compras (id, cantidad, fecha_compra, compra_id, producto_id) VALUES (6, 3, NULL, 23, 3);
INSERT INTO public.detalle_compras (id, cantidad, fecha_compra, compra_id, producto_id) VALUES (7, 2, NULL, 23, 4);
INSERT INTO public.detalle_compras (id, cantidad, fecha_compra, compra_id, producto_id) VALUES (8, 3, NULL, 29, 3);
INSERT INTO public.detalle_compras (id, cantidad, fecha_compra, compra_id, producto_id) VALUES (9, 4, NULL, 29, 2);
INSERT INTO public.detalle_compras (id, cantidad, fecha_compra, compra_id, producto_id) VALUES (10, 2, NULL, 29, 4);
INSERT INTO public.detalle_compras (id, cantidad, fecha_compra, compra_id, producto_id) VALUES (11, 1, NULL, 29, 1);
INSERT INTO public.detalle_compras (id, cantidad, fecha_compra, compra_id, producto_id) VALUES (12, 1, NULL, 29, 5);


--
-- TOC entry 2849 (class 0 OID 17273)
-- Dependencies: 201
-- Data for Name: dias_promociales; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.dias_promociales (id, descripcion, fecha) VALUES (1, 'Dia de la Madre Colombia', '2020-05-10');


--
-- TOC entry 2851 (class 0 OID 17281)
-- Dependencies: 203
-- Data for Name: productos; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.productos (id, nombre, valor) VALUES (1, 'producto1', 50);
INSERT INTO public.productos (id, nombre, valor) VALUES (2, 'producto2', 100);
INSERT INTO public.productos (id, nombre, valor) VALUES (3, 'producto3', 200);
INSERT INTO public.productos (id, nombre, valor) VALUES (4, 'producto4', 500);
INSERT INTO public.productos (id, nombre, valor) VALUES (5, 'producto5', 1000);


--
-- TOC entry 2853 (class 0 OID 17289)
-- Dependencies: 205
-- Data for Name: usuarios; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.usuarios (id, documento_numero, nombre) VALUES (1, '123456', 'abc');
INSERT INTO public.usuarios (id, documento_numero, nombre) VALUES (2, '123456', 'julian');


--
-- TOC entry 2855 (class 0 OID 17300)
-- Dependencies: 207
-- Data for Name: usuarios_historia; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 2870 (class 0 OID 0)
-- Dependencies: 196
-- Name: compras_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.compras_id_seq', 29, true);


--
-- TOC entry 2871 (class 0 OID 0)
-- Dependencies: 198
-- Name: detalle_compras_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.detalle_compras_id_seq', 12, true);


--
-- TOC entry 2872 (class 0 OID 0)
-- Dependencies: 200
-- Name: dias_promociales_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.dias_promociales_id_seq', 1, false);


--
-- TOC entry 2873 (class 0 OID 0)
-- Dependencies: 202
-- Name: productos_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.productos_id_seq', 1, false);


--
-- TOC entry 2874 (class 0 OID 0)
-- Dependencies: 206
-- Name: usuarios_historia_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.usuarios_historia_id_seq', 1, false);


--
-- TOC entry 2875 (class 0 OID 0)
-- Dependencies: 204
-- Name: usuarios_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.usuarios_id_seq', 2, true);


--
-- TOC entry 2708 (class 2606 OID 17262)
-- Name: compras compras_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.compras
    ADD CONSTRAINT compras_pkey PRIMARY KEY (id);


--
-- TOC entry 2710 (class 2606 OID 17270)
-- Name: detalle_compras detalle_compras_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.detalle_compras
    ADD CONSTRAINT detalle_compras_pkey PRIMARY KEY (id);


--
-- TOC entry 2712 (class 2606 OID 17278)
-- Name: dias_promociales dias_promociales_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.dias_promociales
    ADD CONSTRAINT dias_promociales_pkey PRIMARY KEY (id);


--
-- TOC entry 2714 (class 2606 OID 17286)
-- Name: productos productos_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.productos
    ADD CONSTRAINT productos_pkey PRIMARY KEY (id);


--
-- TOC entry 2718 (class 2606 OID 17305)
-- Name: usuarios_historia usuarios_historia_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.usuarios_historia
    ADD CONSTRAINT usuarios_historia_pkey PRIMARY KEY (id);


--
-- TOC entry 2716 (class 2606 OID 17297)
-- Name: usuarios usuarios_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.usuarios
    ADD CONSTRAINT usuarios_pkey PRIMARY KEY (id);


--
-- TOC entry 2719 (class 2606 OID 17306)
-- Name: compras fk4lnkw9964v2gf3b2sjbaealqw; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.compras
    ADD CONSTRAINT fk4lnkw9964v2gf3b2sjbaealqw FOREIGN KEY (usuario_id) REFERENCES public.usuarios(id);


--
-- TOC entry 2722 (class 2606 OID 17321)
-- Name: usuarios_historia fko4s163569rd0qgxffa8m8rquy; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.usuarios_historia
    ADD CONSTRAINT fko4s163569rd0qgxffa8m8rquy FOREIGN KEY (usuario_id) REFERENCES public.usuarios(id);


--
-- TOC entry 2721 (class 2606 OID 17316)
-- Name: detalle_compras fkobinn960qd4nxk1b3b0n6aps1; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.detalle_compras
    ADD CONSTRAINT fkobinn960qd4nxk1b3b0n6aps1 FOREIGN KEY (producto_id) REFERENCES public.productos(id);


--
-- TOC entry 2720 (class 2606 OID 17311)
-- Name: detalle_compras fks7ksdchwhuo5bv58t28iti0f0; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.detalle_compras
    ADD CONSTRAINT fks7ksdchwhuo5bv58t28iti0f0 FOREIGN KEY (compra_id) REFERENCES public.compras(id);


-- Completed on 2020-05-10 21:34:42

--
-- PostgreSQL database dump complete
--

