# 📡 NotifyHub - Microsserviço de Notificações Escalável

> Um sistema backend robusto para envio de notificações multicanal (E-mail, SMS), construído com foco em Design Patterns, Clean Code e alta testabilidade.

![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white) ![Spring](https://img.shields.io/badge/spring-%236DB33F.svg?style=for-the-badge&logo=spring&logoColor=white) ![Postgres](https://img.shields.io/badge/postgres-%23316192.svg?style=for-the-badge&logo=postgresql&logoColor=white) ![Docker](https://img.shields.io/badge/docker-%230db7ed.svg?style=for-the-badge&logo=docker&logoColor=white)

---

## 📋 Sobre o Projeto

O **NotifyHub** é uma API RESTful desenvolvida para centralizar e gerenciar o envio de notificações em uma arquitetura de microsserviços. O principal objetivo deste projeto foi resolver o problema comum de acoplamento forte e excesso de condicionais (`if/else`) ao lidar com diferentes canais de comunicação.

### ✨ Funcionalidades
* ✅ **Envio Multicanal:** Suporte nativo para E-mail e SMS.
* ✅ **Validação por Canal:** Cada tipo de notificação possui sua própria regra de validação (Regex) executada em tempo de execução.
* ✅ **Arquitetura Plug-and-Play:** Pronto para novos canais (WhatsApp, Push) sem alterar o código existente.
* ✅ **Tratamento de Erros:** Global Exception Handler para respostas padronizadas (JSON).

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


### 3. Tratamento Global de Erros
Utilização de `@RestControllerAdvice` para capturar exceções de negócio (como erros de validação de telefone/email) e retornar status HTTP 400 com mensagens claras, evitando erros 500 genéricos.

---

## 🧪 Testes Unitários
O projeto conta com uma suíte de testes utilizando **JUnit 5** e **Mockito**, garantindo que as estratégias e as validações funcionem corretamente de forma isolada.

Para rodar os testes:
```bash
./mvnw test

---

##🛠️ Tecnologias Utilizadas
Java 17 e Spring Boot 3

Spring Data JPA e PostgreSQL

Hibernate Validator (Bean Validation)

JUnit 5 e Mockito

Docker e Maven Wrapper

---

##🚀 Como Rodar o Projeto
1. Clone o repositório

git clone [https://github.com/seu-usuario/notifyhub.git](https://github.com/seu-usuario/notifyhub.git)
cd notifyhub

---

2. Suba o banco de dados (PostgreSQL) via Docker
Bash
docker run --name notifyhub-db -e POSTGRES_PASSWORD=shizuru -e POSTGRES_USER=lucca -e POSTGRES_DB=notifyhub -p 5432:5432 -d postgres
3. Execute a aplicação

./mvnw spring-boot:run

---

🔌 Exemplos de Uso da API
Enviar E-mail (Sucesso)
POST /notifications

{
  "channel": "EMAIL",
  "destination": "lucca.shizuru@exemplo.com",
  "content": "Bem-vindo ao NotifyHub!"
}

Enviar SMS (Exemplo de Erro de Validação)
POST /notifications

{
  "channel": "SMS",
  "destination": "numero-invalido",
  "content": "Teste de validação"
}

Resposta (400 Bad Request):

{
  "error": "Para SMS, o destino deve ser um número de telefone válido (apenas dígitos)."
}

Desenvolvido por Lucca Shizuru 🎓
