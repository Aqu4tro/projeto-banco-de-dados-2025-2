-- ========================================================================
-- 1. TABELAS INDEPENDENTES (Sem Chaves Estrangeiras)
-- ========================================================================

CREATE TABLE ENDERECO (
    id_endereco SERIAL PRIMARY KEY,
    logradouro VARCHAR(200) NOT NULL,
    numero VARCHAR(20),
    bairro VARCHAR(100) NOT NULL,
    cidade VARCHAR(100) NOT NULL,
    estado VARCHAR(50) NOT NULL,
    pais VARCHAR(50) DEFAULT 'Brasil',
    cep VARCHAR(20) NOT NULL
);

-- ========================================================================
-- 2. TABELAS DEPENDENTES DE ENDEREÇO (Nível 1)
-- ========================================================================

CREATE TABLE ONG (
    id_ONG SERIAL PRIMARY KEY,
    nome VARCHAR(150) NOT NULL,
    cnpj VARCHAR(20) UNIQUE NOT NULL,
    id_endereco INT NOT NULL,
    FOREIGN KEY (id_endereco) REFERENCES ENDERECO(id_endereco)
);

CREATE TABLE EMPRESA (
    id_empresa SERIAL PRIMARY KEY,
    nome VARCHAR(150) NOT NULL,
    cnpj VARCHAR(20) UNIQUE NOT NULL,
    tipo_empresa VARCHAR(50),
    email VARCHAR(100) UNIQUE,
    id_endereco INT NOT NULL,
    FOREIGN KEY (id_endereco) REFERENCES ENDERECO(id_endereco)
);

CREATE TABLE DOADOR (
    id_doador SERIAL PRIMARY KEY,
    nome VARCHAR(150) NOT NULL,
    cpf VARCHAR(20) UNIQUE NOT NULL,
    sexo VARCHAR(20) CHECK (sexo IN ('Masculino', 'Feminino', 'Outro', 'N/A')),
    email VARCHAR(100) UNIQUE,
    id_endereco INT NOT NULL,
    FOREIGN KEY (id_endereco) REFERENCES ENDERECO(id_endereco)
);

CREATE TABLE VOLUNTARIO (
    id_voluntario SERIAL PRIMARY KEY,
    nome VARCHAR(150) NOT NULL,
    email VARCHAR(100) UNIQUE,
    cpf VARCHAR(20) UNIQUE NOT NULL,
    sexo VARCHAR(20) CHECK (sexo IN ('Masculino', 'Feminino', 'Outro', 'N/A')),
    id_endereco INT NOT NULL,
    FOREIGN KEY (id_endereco) REFERENCES ENDERECO(id_endereco)
);

-- ========================================================================
-- 3. TABELAS DE SEGUNDO NÍVEL (Dependem de ONG)
-- ========================================================================

CREATE TABLE BENEFICIARIO (
    id_beneficiario SERIAL PRIMARY KEY,
    id_ONG INT NOT NULL,
    cpf VARCHAR(20) UNIQUE,
    data_nascimento DATE,
    necessidade VARCHAR(200),
    FOREIGN KEY (id_ONG) REFERENCES ONG(id_ONG)
);

CREATE TABLE CAMPANHA (
    id_campanha SERIAL PRIMARY KEY,
    nome VARCHAR(150) NOT NULL,
    data_inicio DATE NOT NULL,
    data_fim DATE,
    categoria VARCHAR(100),
    id_endereco INT NOT NULL,
    id_ONG INT NOT NULL,
    FOREIGN KEY (id_endereco) REFERENCES ENDERECO(id_endereco),
    FOREIGN KEY (id_ONG) REFERENCES ONG(id_ONG)
);

-- ========================================================================
-- 4. TABELAS ASSOCIATIVAS E TRANSAÇÕES (N:N e Relacionamentos)
-- ========================================================================

CREATE TABLE PARTICIPACAO (
    id_participacao SERIAL PRIMARY KEY,
    data_filiacao DATE NOT NULL,
    data_encerramento DATE,
    carga_horaria VARCHAR(50),
    cargo VARCHAR(100),
    id_campanha INT NOT NULL,
    id_voluntario INT NOT NULL,
    FOREIGN KEY (id_campanha) REFERENCES CAMPANHA(id_campanha),
    FOREIGN KEY (id_voluntario) REFERENCES VOLUNTARIO(id_voluntario)
);

CREATE TABLE PATROCINIO (
    id_patrocinio SERIAL PRIMARY KEY,
    data_filiacao DATE NOT NULL,
    data_encerramento DATE,
    tipo VARCHAR(50),
    volume DECIMAL(10,2) CHECK (volume > 0),
    id_campanha INT NOT NULL,
    id_empresa INT NOT NULL,
    FOREIGN KEY (id_campanha) REFERENCES CAMPANHA(id_campanha),
    FOREIGN KEY (id_empresa) REFERENCES EMPRESA(id_empresa)
);

CREATE TABLE DOACAO (
    id_doacao SERIAL PRIMARY KEY,
    tipo VARCHAR(50) NOT NULL,
    volume DECIMAL(10,2) CHECK (volume > 0),
    data DATE NOT NULL DEFAULT CURRENT_DATE,
    descricao TEXT,
    id_doador INT NOT NULL,
    id_campanha INT NOT NULL,
    FOREIGN KEY (id_doador) REFERENCES DOADOR(id_doador),
    FOREIGN KEY (id_campanha) REFERENCES CAMPANHA(id_campanha)
);

-- ========================================================================
-- 5. TABELAS DE TELEFONES (Resolução da 1FN)
-- ========================================================================

CREATE TABLE TELEFONE_ONG (
    id_telefone SERIAL PRIMARY KEY,
    numero VARCHAR(20) NOT NULL,
    ddd INT NOT NULL,
    tipo VARCHAR(20),
    id_ONG INT NOT NULL,
    FOREIGN KEY (id_ONG) REFERENCES ONG(id_ONG) ON DELETE CASCADE
);

CREATE TABLE TELEFONE_DOADOR (
    id_telefone SERIAL PRIMARY KEY,
    numero VARCHAR(20) NOT NULL,
    ddd INT NOT NULL,
    tipo VARCHAR(20),
    id_doador INT NOT NULL,
    FOREIGN KEY (id_doador) REFERENCES DOADOR(id_doador) ON DELETE CASCADE
);

-- Nota: Para encurtar, coloquei apenas 2 tabelas de telefone aqui, 
-- mas você pode replicar a mesma estrutura para VOLUNTARIO, BENEFICIARIO, EMPRESA e CAMPANHA.