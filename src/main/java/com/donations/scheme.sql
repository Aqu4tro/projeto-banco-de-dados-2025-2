CREATE TABLE ENDERECO (
    id_endereco SERIAL PRIMARY KEY,
    ruua VARCHAR(255) NOT NULL,
    numero VARCHAR(20),
    bairro VARCHAR(100),
    cidade VARCHAR(100)
);

CREATE TABLE ONG (
    id_ong SERIAL PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    cnpj VARCHAR(20) UNIQUE NOT NULL,
    id_endereco INT,
    FOREIGN KEY (id_endereco) REFERENCES ENDERECO(id_endereco)
);

CREATE TABLE DOADOR (
    id_doador SERIAL PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    cpf VARCHAR(15) UNIQUE NOT NULL,
    id_endereco INT,
    FOREIGN KEY (id_endereco) REFERENCES ENDERECO(id_endereco)
);

CREATE TABLE BENEFICIARIO (
    id_beneficiario SERIAL PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    cpf VARCHAR(15) UNIQUE NOT NULL,
    dependentes INT DEFAULT 0,
    id_endereco INT,
    FOREIGN KEY (id_endereco) REFERENCES ENDERECO(id_endereco)
);

CREATE TABLE VOLUNTARIO (
    id_voluntario SERIAL PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    telefone_emergencia VARCHAR(20),
    especialidade VARCHAR(100),
    id_endereco INT,
    FOREIGN KEY (id_endereco) REFERENCES ENDERECO(id_endereco)
);

CREATE TABLE EMPRESA (
    id_empresa SERIAL PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    cnpj VARCHAR(20) UNIQUE NOT NULL,
    tipo_empresa VARCHAR(100),
    email VARCHAR(150),
    id_endereco INT,
    FOREIGN KEY (id_endereco) REFERENCES ENDERECO(id_endereco)
);

CREATE TABLE CAMPANHA (
    id_campanha SERIAL PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    data_inicio DATE,
    categoria VARCHAR(100),
    id_ong INT,
    id_endereco INT,
    FOREIGN KEY (id_ong) REFERENCES ONG(id_ong),
    FOREIGN KEY (id_endereco) REFERENCES ENDERECO(id_endereco)
);

CREATE TABLE ITEM_ESTOQUE (
    id_item SERIAL PRIMARY KEY,
    nome_item VARCHAR(150) NOT NULL,
    quantidade DECIMAL(10,2) NOT NULL,
    unidade_medida VARCHAR(20),
    id_ong INT,
    FOREIGN KEY (id_ong) REFERENCES ONG(id_ong)
);

CREATE TABLE TELEFONE_ONG (
    id_telefone SERIAL PRIMARY KEY,
    numero VARCHAR(15) NOT NULL,
    ddd INT NOT NULL,
    tipo VARCHAR(50),
    id_ong INT,
    FOREIGN KEY (id_ong) REFERENCES ONG(id_ong)
);

CREATE TABLE TELEFONE_DOADOR (
    id_telefone SERIAL PRIMARY KEY,
    numero VARCHAR(15) NOT NULL,
    ddd INT NOT NULL,
    tipo VARCHAR(50),
    id_doador INT,
    FOREIGN KEY (id_doador) REFERENCES DOADOR(id_doador)
);

CREATE TABLE DOACAO (
    id_doacao SERIAL PRIMARY KEY,
    tipo VARCHAR(100) NOT NULL,
    volume DECIMAL(10,2) NOT NULL,
    data_doacao DATE NOT NULL,
    origem VARCHAR(100),
    id_doador INT,
    id_campanha INT,
    FOREIGN KEY (id_doador) REFERENCES DOADOR(id_doador),
    FOREIGN KEY (id_campanha) REFERENCES CAMPANHA(id_campanha)
);

CREATE TABLE ENTREGA (
    id_entrega SERIAL PRIMARY KEY,
    data_entrega DATE NOT NULL,
    quantidade DECIMAL(10,2) NOT NULL,
    id_beneficiario INT,
    id_item_estoque INT,
    FOREIGN KEY (id_beneficiario) REFERENCES BENEFICIARIO(id_beneficiario),
    FOREIGN KEY (id_item_estoque) REFERENCES ITEM_ESTOQUE(id_item)
);

CREATE TABLE PATROCINIO (
    id_patrocinio SERIAL PRIMARY KEY,
    data_filiacao DATE NOT NULL,
    data_encerramento DATE,
    tipo VARCHAR(100),
    volume DECIMAL(10,2),
    id_campanha INT,
    id_empresa INT,
    FOREIGN KEY (id_campanha) REFERENCES CAMPANHA(id_campanha),
    FOREIGN KEY (id_empresa) REFERENCES EMPRESA(id_empresa)
);

CREATE TABLE PARTICIPACAO (
    id_participacao SERIAL PRIMARY KEY,
    data_inicio DATE NOT NULL,
    data_fim DATE,
    carga_horaria VARCHAR(50),
    cargo VARCHAR(100),
    id_campanha INT,
    id_voluntario INT,
    FOREIGN KEY (id_campanha) REFERENCES CAMPANHA(id_campanha),
    FOREIGN KEY (id_voluntario) REFERENCES VOLUNTARIO(id_voluntario)
);

INSERT INTO ENDERECO (ruua, numero, bairro, cidade) VALUES 
('Av. Paulista', '1000', 'Bela Vista', 'São Paulo'),
('Rua das Flores', '123', 'Centro', 'Campinas'),
('Av. Brasil', '500', 'Jardim América', 'Rio de Janeiro');

INSERT INTO ONG (nome, cnpj, id_endereco) VALUES 
('Mãos Solidárias', '12.345.678/0001-99', 1);

INSERT INTO DOADOR (nome, cpf, id_endereco) VALUES 
('Carlos Almeida', '111.222.333-44', 2);

INSERT INTO BENEFICIARIO (nome, cpf, dependentes, id_endereco) VALUES 
('Família Silva (Dona Maria)', '999.888.777-66', 4, 3);

INSERT INTO VOLUNTARIO (nome, telefone_emergencia, especialidade, id_endereco) VALUES 
('Ana Souza', '11987654321', 'Enfermagem', 1);

INSERT INTO EMPRESA (nome, cnpj, tipo_empresa, email, id_endereco) VALUES 
('Supermercados Boa Compra', '98.765.432/0001-11', 'Varejo Alimentício', 'contato@boacompra.com', 2);

INSERT INTO CAMPANHA (nome, data_inicio, categoria, id_ong, id_endereco) VALUES 
('Inverno Aquecido 2024', '2024-05-01', 'Agasalhos', 1, 1);

INSERT INTO ITEM_ESTOQUE (nome_item, quantidade, unidade_medida, id_ong) VALUES 
('Cesta Básica', 50.00, 'Unidade', 1),
('Cobertor', 120.00, 'Peça', 1);

INSERT INTO TELEFONE_ONG (numero, ddd, tipo, id_ong) VALUES 
('3333-4444', 11, 'Fixo', 1);

INSERT INTO TELEFONE_DOADOR (numero, ddd, tipo, id_doador) VALUES 
('91111-2222', 19, 'Celular', 1);

INSERT INTO DOACAO (tipo, volume, data_doacao, origem, id_doador, id_campanha) VALUES 
('Agasalhos Diversos', 5.00, '2024-05-10', 'Balcão', 1, 1);

INSERT INTO ENTREGA (data_entrega, quantidade, id_beneficiario, id_item_estoque) VALUES 
('2024-05-15', 2.00, 1, 1);

INSERT INTO PATROCINIO (data_filiacao, data_encerramento, tipo, volume, id_campanha, id_empresa) VALUES 
('2024-05-02', '2024-08-30', 'Alimentos', 10000.00, 1, 1);

INSERT INTO PARTICIPACAO (data_inicio, data_fim, carga_horaria, cargo, id_campanha, id_voluntario) VALUES 
('2024-05-01', NULL, '20h Semanais', 'Coordenadora de Triagem', 1, 1);