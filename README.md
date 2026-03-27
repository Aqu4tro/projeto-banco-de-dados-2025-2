# 🎁 Sistema de Gerenciamento de Doações

Um sistema desktop desenvolvido em **Java** com interface gráfica em **JavaFX**, integrado a um banco de dados **PostgreSQL**. Este projeto foi construído para gerenciar o ecossistema completo de doações, interligando ONGs, campanhas, doadores, beneficiários, empresas parceiras e voluntários.

---

## 📋 Sobre o Projeto (Contexto Acadêmico)

Este projeto atende aos requisitos da **Segunda Etapa** da disciplina de Banco de Dados. O sistema foi projetado sob a premissa de uso de **SQL Puro**, sem a utilização de frameworks de mapeamento objeto-relacional (ORM), garantindo a manipulação direta e eficiente do banco de dados relacional via **JDBC**.

### Cumprimento dos Requisitos Obrigatórios:
* **SGBD Utilizado:** PostgreSQL.
* **Implementação em SQL:** O banco está funcional e pode ser construído inteiramente pelo script disponibilizado neste repositório (`script_criacao.sql`), contendo chaves primárias, estrangeiras e restrições (`NOT NULL`, `UNIQUE`, `CHECK`).
* **Integração e ORM:** A aplicação foi desenvolvida em Java (JavaFX). A conexão e as operações são feitas via JDBC com instruções SQL diretas nas classes `DAO`, respeitando a proibição de ORMs.
* **Operações Exigidas:**
    * **Inserção (Mínimo 3 tabelas, 1 sendo N:N):** Implementado em diversas tabelas, incluindo `Doacao`, `Beneficiario` e tabelas associativas N:N como `Participacao` (Voluntário x Campanha) e `Patrocinio` (Empresa x Campanha).
    * **Consulta (Mínimo 6, 3 parametrizadas, 1 múltiplos parâmetros):** Implementadas nos relatórios da interface e carregamento das *TableViews* (ver classes `DAO`).
    * **Atualização (Mínimo 1 tabela):** Implementado no controle de `ItemEstoque` (onde itens são somados ou baixados após entregas).
* **Interface:** Aplicação Desktop com menus estruturados em abas (*TabPane*) garantindo navegação fluida.

---

## 🚀 Funcionalidades Mapeadas

O sistema é dividido em abas intuitivas para facilitar o controle de todas as frentes de uma campanha de doação:

* **🎁 Doações:** Registro de doações vinculadas a doadores e campanhas.
* **📦 Estoque:** Controle de armazenamento de itens nas ONGs.
* **🚚 Entregas:** Baixa de estoque e repasse de itens para beneficiários.
* **🏢 ONGs & 🤝 Parceiros:** Cadastro de instituições e empresas patrocinadoras.
* **💼 Patrocínios:** Gestão de aportes financeiros/materiais de empresas em campanhas específicas (Relação N:N).
* **📢 Campanhas:** Criação de eventos de arrecadação.
* **👥 Doadores & 👨‍👩‍👧 Beneficiários:** Gestão das pontas da rede de solidariedade.
* **🙋 Voluntários & ⏱️ Participações:** Alocação de voluntários em campanhas específicas (Relação N:N).
* **📞 Contatos & 📍 Endereços:** Normalização de dados de contato.

---

## 🛠️ Tecnologias Utilizadas

* **Linguagem:** Java (JDK 11 ou superior recomendado)
* **Interface Gráfica:** JavaFX
* **Banco de Dados:** PostgreSQL
* **Comunicação com BD:** JDBC (`java.sql.*`)

---

## ⚙️ Instruções de Execução

### 1. Preparando o Banco de Dados
1. Certifique-se de ter o **PostgreSQL** instalado e rodando na porta `5432`.
2. Crie um banco de dados chamado `donations`:
   ```sql
   CREATE DATABASE donations;