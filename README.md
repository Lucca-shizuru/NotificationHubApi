# 📡 NotifyHub - Microsserviço de Notificações Escalável

> Um sistema backend robusto para envio de notificações multicanal (E-mail, SMS), construído com foco em Design Patterns, Clean Code e alta testabilidade.

![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white) ![Spring](https://img.shields.io/badge/spring-%236DB33F.svg?style=for-the-badge&logo=spring&logoColor=white) ![Postgres](https://img.shields.io/badge/postgres-%23316192.svg?style=for-the-badge&logo=postgresql&logoColor=white) ![Docker](https://img.shields.io/badge/docker-%230db7ed.svg?style=for-the-badge&logo=docker&logoColor=white)

---

## 📋 Sobre o Projeto

O **NotifyHub** é uma API RESTful desenvolvida para centralizar e gerenciar o envio de notificações. O projeto foi desenhado para resolver o problema de acoplamento forte, utilizando padrões de projeto que permitem a expansão do sistema (ex: adicionar WhatsApp ou Push) sem a necessidade de alterar o código core.

### ✨ Funcionalidades
✅ Segurança Stateless (JWT): Autenticação via JSON Web Token (Auth0), protegendo endpoints sensíveis e garantindo que apenas usuários autorizados enviem notificações.

✅ Versionamento de Banco (Flyway): Histórico de evolução do banco de dados via scripts SQL migratórios, garantindo consistência entre ambientes.

✅ Estratégia Dinâmica (Strategy Pattern): Seleção automática do canal de envio em tempo de execução.

✅ Documentação Automática (Swagger/OpenAPI): Interface interativa para testes e consulta de endpoints.

✅ GraalVM Optimized: Desenvolvido para alta performance utilizando o GraalVM JDK.

---

## 🏗️ Arquitetura e Design Patterns

A maior força deste projeto reside na sua arquitetura desacoplada, utilizando princípios do **SOLID**.

### 1. Strategy Pattern (O Coração do Sistema)
Utilizado para eliminar a explosão de `if/else` no serviço principal. O Spring Boot injeta automaticamente todas as implementações da interface `NotificationStrategy`, e o sistema seleciona a correta dinamicamente.

**Benefícios:**
* **Open/Closed Principle:** Extensível para novos canais sem modificar a lógica de negócio principal.
* **Coesão:** A lógica de envio e a validação específica de cada destino ficam isoladas.

<img width="881" height="709" alt="image" src="https://github.com/user-attachments/assets/46d1a1d0-fbd9-4af1-aa5c-8dfa3fae0b26" />


### 2. Factory Method Pattern
Implementado para centralizar a criação da entidade `Notification`. A Factory encapsula a conversão do DTO de entrada para o modelo de domínio, garantindo que o objeto seja criado de forma consistente.

**Benefícios:**
* **Desacoplamento:** O Controller não conhece detalhes da Entidade, apenas do DTO.
* **Manutenibilidade:** Regras de criação centralizadas em um único componente.

<img width="1319" height="561" alt="image" src="https://github.com/user-attachments/assets/46b81224-f362-40fd-b90c-1856d1384667" />


### 3. Autenticação JWT
Segurança com JWT
Implementação de um filtro customizado (OncePerRequestFilter) que intercepta requisições, valida a assinatura do token e injeta o contexto do usuário no Spring Security de forma Stateless.
---

## 🧪 Testes Unitários
O projeto conta com uma suíte de testes utilizando **JUnit 5** e **Mockito**, garantindo que as estratégias e as validações funcionem corretamente de forma isolada.

Para rodar os testes:
./mvnw test

---

##🛠️ Tecnologias Utilizadas
Java 17 (GraalVM)	Linguagem e Ambiente de Execução de alta performance.

Spring Boot 3	Framework core da aplicação.

Spring Security + JWT	Autenticação e Autorização.

PostgreSQL	Banco de dados relacional robusto.

Flyway	Gerenciamento de Migrações (Database Version Control).

Swagger UI	Documentação e testes da API.

Docker	Conteinerização do banco de dados.

---

## 🚀 Como Rodar o Projeto
1. Clone o repositório:
git clone https://github.com/seu-usuario/notifyhub.git
cd notifyhub

2. Suba o PostgreSQL via Docker:
docker run --name notifyhub-db -e POSTGRES_PASSWORD=shizuru -e POSTGRES_USER=lucca -e POSTGRES_DB=notifyhub -p 5432:5432 -d postgres

3. Compile e rode a aplicação:
./mvnw clean install
./mvnw spring-boot:run

---

## 🔌 Guia de Uso (Swagger)
Acesse a documentação interativa em: http://localhost:8080/swagger-ui/index.html

Fluxo de Autenticação:

Use o endpoint /auth/register para criar seu usuário.

Use o /auth/login para receber seu Token JWT.

Clique no botão "Authorize" no topo do Swagger e cole o token para liberar os envios.

## 🎓 Desenvolvido por Lucca Shizuru
