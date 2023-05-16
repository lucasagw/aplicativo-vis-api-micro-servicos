CREATE TABLE usuario (
	id SERIAL PRIMARY KEY,
	nome VARCHAR(100) NOT NULL,
	email VARCHAR(250) NOT NULL,
	telefone VARCHAR(50) NOT NULL,
	senha VARCHAR(250) NOT NULL,
	cpf VARCHAR(11) NOT NULL,
	avatar TEXT,
	primeiro_login boolean DEFAULT true,
	flag_ativo boolean DEFAULT true
);

CREATE TABLE perfil (
	id SERIAL  PRIMARY KEY,
	nome VARCHAR(30) NOT NULL
);

CREATE TABLE usuario_perfis (
	id SERIAL PRIMARY KEY,
	usuario_id int,
	perfil_id int,
	FOREIGN KEY (usuario_id) REFERENCES usuario(id),
	FOREIGN KEY (perfil_id) REFERENCES perfil(id)
);

insert into usuario (nome, email, telefone, senha, cpf, primeiro_login, flag_ativo) values
('Claudio', 'claudio@mail.com', '71999999999', '$2a$10$IgwFpjnxPDQwi0bwwM60PeO5allUGbKBK3WEra/oPosWSwDrJSv/K', '99999999999', false, true),
('Luiza', 'luiza@mail.com', '71999999998', '$2a$10$IgwFpjnxPDQwi0bwwM60PeO5allUGbKBK3WEra/oPosWSwDrJSv/K', '99999999998', false, false),
('Barreto', 'barreto@mail.com', '71999999997', '$2a$10$IgwFpjnxPDQwi0bwwM60PeO5allUGbKBK3WEra/oPosWSwDrJSv/K', '99999999997', true, false),
('Mario', 'mario@mail.com', '71999999996', '$2a$10$IgwFpjnxPDQwi0bwwM60PeO5allUGbKBK3WEra/oPosWSwDrJSv/K', '99999999996', false, true),
('Ramon', 'ramon@mail.com', '71999999995', '$2a$10$IgwFpjnxPDQwi0bwwM60PeO5allUGbKBK3WEra/oPosWSwDrJSv/K', '99999999995', true, false),
('Bonafini', 'bonafini@mail.com', '71999999994', '$2a$10$IgwFpjnxPDQwi0bwwM60PeO5allUGbKBK3WEra/oPosWSwDrJSv/K', '99999999994', false, true),
('Augusto', 'augusto@mail.com', '71999999993', '$2a$10$IgwFpjnxPDQwi0bwwM60PeO5allUGbKBK3WEra/oPosWSwDrJSv/K', '99999999993', true, false),
('Andre', 'andre@mail.com', '71999999992', '$2a$10$IgwFpjnxPDQwi0bwwM60PeO5allUGbKBK3WEra/oPosWSwDrJSv/K', '99999999992', true, false);

insert into perfil (nome) values
('ROLE_ADMIN'),
('ROLE_USER');

