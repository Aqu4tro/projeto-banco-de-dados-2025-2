-- inserir 1 Endereço Base
INSERT INTO ENDERECO (logradouro, numero, bairro, cidade, estado, cep) 
VALUES ('Rua São Pedro', '123', 'Centro', 'Juazeiro do Norte', 'CE', '63000-000');

-- inserir 1 ONG
INSERT INTO ONG (nome, cnpj, id_endereco) 
VALUES ('Amigos do Bem', '11.222.333/0001-44', 1);

-- inserir 1 Campanha
INSERT INTO CAMPANHA (nome, data_inicio, categoria, id_endereco, id_ONG) 
VALUES ('Verão Solidário', '2026-03-01', 'Arrecadação de Calçados', 1, 1);

-- inserir 1 Doador
INSERT INTO DOADOR (nome, cpf, sexo, email, id_endereco) 
VALUES ('Carlos Almeida', '123.456.789-00', 'Masculino', 'carlos@email.com', 1);

-- Inserir 1 Doação
INSERT INTO DOACAO (tipo, volume, data, descricao, id_doador, id_campanha) 
VALUES ('Chinelos', 15.5, '2026-03-17', 'Sacola com 10 chinelos da bad boy', 1, 1);