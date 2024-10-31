# 📊 Agregador de Investimentos

Este projeto é um backend para um **Agregador de Investimentos**, desenvolvido com **Java 21**, **Spring Boot** e **MySQL**. O sistema permite criar, consultar, atualizar e deletar usuários, servindo como uma REST API CRUD.

## 🎯 Objetivo do Projeto

Construir uma REST API que realiza operações CRUD para gerenciar usuários de uma plataforma de investimentos. Este projeto aborda:

- 📌 **Annotations do Spring Boot**
- 🔄 **Conceitos de injeção de dependência e inversão de controle**
- 🏗️ **Criação de camadas Controller, Service e Repository**
- 🗄️ **Mapeamento de entidades com Hibernate e integração com MySQL**

## 🛠️ Tecnologias Utilizadas

- ☕ **Java 21**
- 🚀 **Spring Boot**
- 🐬 **MySQL** (rodando com Docker Compose)
- 🗂️ **Hibernate** (para ORM - mapeamento objeto-relacional)

## 🔗 Endpoints

A API inclui os seguintes endpoints para manipulação de usuários:

| Método | Endpoint              | Descrição                    |
|--------|------------------------|------------------------------|
| POST   | `/api/usuarios`        | Criação de um novo usuário   |
| GET    | `/api/usuarios`        | Consulta todos os usuários   |
| GET    | `/api/usuarios/{id}`   | Consulta um usuário por ID   |
| PUT    | `/api/usuarios/{id}`   | Atualiza um usuário          |
| DELETE | `/api/usuarios/{id}`   | Deleta um usuário            |

## 🏗️ Estrutura do Projeto

- **Controller**: Responsável por gerenciar as requisições HTTP.
- **Service**: Camada de lógica de negócios, onde a lógica de manipulação dos dados é aplicada.
- **Repository**: Interface para acesso ao banco de dados, utilizando o JPA/Hibernate para mapear as entidades automaticamente.
