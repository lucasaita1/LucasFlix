# 🎬 LucasFlix

<div align="center">
  <img src="https://img.shields.io/badge/Java-17+-orange?style=for-the-badge&logo=java" />
  <img src="https://img.shields.io/badge/Spring%20Boot-3.2+-brightgreen?style=for-the-badge&logo=springboot" />
  <img src="https://img.shields.io/badge/Spring%20Security-6+-green?style=for-the-badge&logo=springsecurity" />
  <img src="https://img.shields.io/badge/JWT-Authentication-blue?style=for-the-badge&logo=jsonwebtokens" />
  <img src="https://img.shields.io/badge/Swagger-OpenAPI-85EA2D?style=for-the-badge&logo=swagger" />
  <img src="https://img.shields.io/badge/Maven-Build-C71A36?style=for-the-badge&logo=apachemaven" />
</div>

<div align="center">
  <h3>🚀 API RESTful moderna para gerenciamento de filmes</h3>
  <p>Uma solução robusta e escalável construída com as melhores práticas de desenvolvimento Java</p>
</div>

---

## 📋 Índice

- [Sobre o Projeto](#-sobre-o-projeto)
- [Tecnologias](#-tecnologias)
- [Funcionalidades](#-funcionalidades)
- [Autenticação](#-autenticação)
- [Documentação da API](#-documentação-da-api)
- [Estrutura do Projeto](#-estrutura-do-projeto)
- [Uso](#-uso)
- [Instalação](#-como-rodar-o-projeto-localmente)
- [Variáveis de Ambiente](#-variáveis-de-ambiente)
- [Contribuição](#-contribuição)
- [Licença](#-licença)
- [Contato](#-contato)

## 🎯 Sobre o Projeto

LucasFlix é uma aplicação **RESTful API** desenvolvida em **Java com Spring Boot**, que simula uma plataforma de gerenciamento de filmes e serviços de streaming.  
O projeto foi criado para consolidar conhecimentos em **APIs REST, autenticação JWT, documentação com Swagger e boas práticas de arquitetura**.
 ---

### ✨ Diferenciais
🔐 Segurança robusta com Spring Security e JWT

📚 Documentação interativa com Swagger/OpenAPI

✅ Validações abrangentes em todas as entradas

🚀 Arquitetura escalável seguindo princípios SOLID

🧪 Testes automatizados para garantir qualidade

🔄 Integração contínua pronta para deploy

---

## 🛠 Tecnologias

### Core
- **Java 17+** - Linguagem principal
- **Spring Boot 3.2+** - Framework principal
- **Spring Security 6** - Segurança e autenticação
- **Spring Data JPA** - Persistência de dados
- **Spring Validation** - Validação de dados

### Banco de Dados
- **PostgreSQL** - Via Docker-compose


### Documentação & Testing
- **Swagger/OpenAPI 3** - Documentação da API


### Segurança & Autenticação
- **JWT (JSON Web Tokens)** - Autenticação stateless
- **BCrypt** - Hash de senhas


### Build 
- **Maven** - Gerenciamento de dependências
- **Docker** - Containerização

---

## 📟 Funcionalidades

### Autenticação e Autorização
- Registro de novos usuários.
- Login de usuários com geração de **JWT**.
- Proteção de rotas com autenticação.

### Gerenciamento de Filmes
- Criação, leitura, atualização e exclusão de filmes.
- Listagem de todos os filmes.
- Busca de filmes por ID.

### Gerenciamento de Categorias
- Criação, leitura, atualização e exclusão de categorias de filmes.
- Listagem de todas as categorias.
- Busca de categorias por ID.

### Gerenciamento de Plataformas de Streaming
- Criação, leitura, atualização e exclusão de plataformas de streaming.
- Listagem de todas as plataformas de streaming.
- Busca de plataformas de streaming por ID.

---

## 🔑 **Autenticação**

A API utiliza **JWT (JSON Web Token)** para autenticação e autorização.  
Após o login, o usuário recebe um token que deve ser enviado no **header Authorization** para acessar os endpoints protegidos.

Exemplo de uso do token:
```
Authorization: Bearer {seu_token_jwt}
```

---

## 📖 **Documentação da API**

Acesse a documentação interativa (Swagger UI) após rodar o projeto:
```
http://localhost:8080/swagger/index.html
```

### Principais Endpoints

| Método   | Endpoint                                        | Descrição                      | Auth |
|----------|-------------------------------------------------|--------------------------------|---|
| `POST`   | `http://localhost:8080/lucasflix/auth/register` | Registrar usuário              | ❌ |
| `POST`   | `http://localhost:8080/lucasflix/auth/login`    | Fazer login                    | ❌ |
| `GET`    | `http://localhost:8080/movie`                   | Listar filmes                  | ✅ |
| `POST`   | `http://localhost:8080/movie`                   | Criar filme                    | ✅ |
| `GET`    | `http://localhost:8080/movie{id}`               | Buscar filme                   | ✅ |
| `PUT`    | `http://localhost:8080/movie{id}`               | Atualizar filme                | ✅ |
| `DELETE` | `http://localhost:8080/movie{id}`               | Remover filme                  | ✅ |
| `GET`    | `http://localhost:8080/category/list`           | listar categorias              | ✅ |
| `POST`   | `http://localhost:8080/category/create`         | criar categoria                | ✅ |
| `GET`    | `http://localhost:8080/streaming/list`          | lista os servicos de streaming | ✅ |
| `POST`   | `http://localhost:8080/streaming/create`        | criar servico de streaming     | ✅ |


## 🏗 Estrutura do Projeto

```
src/
├── main/
│   ├── java/com/lucas/lucasflix/
│   │   ├── config/           # Configurações
│   │   ├── controller/       # Controllers REST
│   │   ├── dto/             # Data Transfer Objects
│   │   ├── entity/          # Entidades JPA
│   │   ├── exception/       # Tratamento de exceções
│   │   ├── repository/      # Repositórios JPA
│   │   ├── security/        # Configurações de segurança
│   │   ├── service/         # Lógica de negócio
│   │   └── LucasFlixApplication.java
│   └── resources/
│       ├── application.properties
│       ├── application-dev.properties
│       ├── application-prod.properties
│       └── data.sql
└── test/
    └── java/com/lucas/lucasflix/
        ├── controller/      # Testes dos controllers
        ├── service/         # Testes dos services
        └── integration/     # Testes de integração
```

---

## 📖 Uso

Após iniciar a aplicação, ela estará disponível em:
- **API Base URL**: `http://localhost:8080/lucasflix/auth/register`
- **Swagger UI**: `http://localhost:8080/swagger-ui.html`


### Exemplos de Uso

#### Autenticação
```bash
# Registrar novo usuário
curl -X POST http://localhost:8080/lucasflix/auth/register \
  -H "Content-Type: application/json" \
  -d '{
    "nome": "João Silva",
    "email": "joao@email.com",
    "senha": "senha123"
  }'

# Login
curl -X POST http://localhost:8080/lucasflix/auth/login \
  -H "Content-Type: application/json" \
  -d '{
    "email": "joao@email.com",
    "senha": "senha123"
  }'
```
## 🛠 **Como Rodar o Projeto Localmente**

```bash
# Clone o repositório
git clone https://github.com/lucasaita1/LucasFlix.git

# Acesse o diretório do projeto
cd LucasFlix

# Execute a aplicação com Maven
mvn spring-boot:run
```

A aplicação será executada em:
```
http://localhost:8080
```

---

## 🔧 Variáveis de Ambiente
Configure as seguintes variáveis em `application.properties`:


```properties
# Database
spring.datasource.url=${DB_URL:jdbc:postgresql://localhost:5432/lucasflix}
spring.datasource.username=${DB_USERNAME:seu-usuario}
spring.datasource.password=${DB_PASSWORD:sua-senha}

# JWT
jwt.secret=${JWT_SECRET:sua-chave-secreta-super-segura}
```
---
## 🤝 Contribuição

Contribuições são sempre bem-vindas! Para contribuir:

1. **Fork** o projeto
2. **Clone** o seu fork
3. Crie uma **branch** para sua feature (`git checkout -b feature/sua-branch`)
4. **Commit** suas mudanças (`git commit -m 'Add some new Feature'`)
5. **Push** para a branch (`git push origin feature/sua-branch`)
6. Abra um **Pull Request**

### Diretrizes
- Siga o padrão de código existente
- Adicione testes para novas funcionalidades
- Atualize a documentação quando necessário
- Use commits semânticos

---
## 📄 Licença

Este projeto está sob a licença MIT. Veja o arquivo [LICENSE](LICENSE) para mais detalhes.

---
## 📞 Contato

**Lucas Aita**
- GitHub: [@lucasaita1](https://github.com/lucasaita1)
- LinkedIn: [Lucas Aita](https://linkedin.com/in/lucasaita)
- Email: lucasaita4000@gmail.com

---

<div align="center">
  <p>⭐ Se este projeto te ajudou, considere dar uma estrela!</p>
  <p>Feito com ❤️ por <a href="https://github.com/lucasaita1">Lucas Aita</a></p>
</div>
