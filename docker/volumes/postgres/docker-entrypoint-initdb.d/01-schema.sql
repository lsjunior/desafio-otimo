--CREATE DATABASE otimo
--  WITH OWNER = otimo
--       ENCODING = 'UTF8'
--       TABLESPACE = pg_default
--       LC_COLLATE = 'C'
--       LC_CTYPE = 'C'
--       TEMPLATE = template0
--       CONNECTION LIMIT = -1;

--SET CONNECTION TO otimo;

CREATE TABLE public.empresa (
	id serial NOT NULL,
	cnpj varchar(20) NOT NULL,
	nome varchar(50) NOT NULL,
	razao_social varchar(40) NOT NULL,
	email varchar(40) NOT NULL,
	logradouro varchar(40) NOT NULL,
	estado varchar(40) NOT NULL,
	cidade varchar(40) NOT NULL,
	bairro varchar(40) NOT NULL,
	cep varchar(10) NOT NULL,
	tipo int NOT NULL,
  id_matriz bigint NULL
);
