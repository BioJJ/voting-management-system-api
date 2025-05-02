# APP

## Descrição

Este projeto é uma aplicação web para gerenciar a sessão de votação dentro de uma cooperativa, permitindo que associados votem em pautas de forma organizada e segura.

## Funcionalidades

### Parte 1: Gerenciamento de Pauta

1. **Cadastrar nova Pauta:**
    - Permite adicionar uma nova pauta para a votação.

2. **Abrir Sessão de Votação:**
    - Inicia uma nova sessão de votação em uma pauta, com um tempo determinado ou um minuto por padrão.

3. **Receber Votos:**
    - Permite que associados votem com 'Sim' ou 'Não'. Cada associado é identificado por um ID único e pode votar apenas uma vez por pauta.

4. **Contabilizar Votos:**
    - Ao final da sessão de votação, contabiliza os votos e informa o resultado (quantos votos foram 'Sim' e 'Não').

### Bônus (opcional):

- **Integração com Sistema Externo:**
    - Verificação da elegibilidade do associado para votar através de uma API externa (GET `https://user-info.herokuapp.com/users/{cpf}`).

- **Mensageria e Filas:**
    - O resultado da votação é enviado para o restante da plataforma através de mensageria.

- **Performance:**
    - A aplicação é otimizada para lidar com um grande volume de votos.

- **Versionamento da API:**
    - Estratégia para versionamento da API, possivelmente utilizando um prefixo na URL (e.g., `/v1/api/...`).

## Tecnologias Utilizadas

- Java 17
- Spring Boot 3.3.2
- Spring Data JPA
- Spring Security
- Spring Validation
- MySQL como banco de dados
- Lombok para facilitar a criação de classes
- Mensageria (opcional para tarefas bônus)
- Integracao com sistemas externos (opcional para tarefas bônus)
- JWT para autenticação (opcional)

## Execução do Projeto local

Para executar o projeto, você precisará ter o Java 17 e o Maven instalados. Siga os passos abaixo:

Clone este repositório:
```shell
git clone <URL_DO_REPOSITORIO>
cd APP
```

## Installation DB app with docker(Mysql)

```bash
# Criando e rodando imagem docker com o MYSQL
$ docker run --name mysql-container -e MYSQL_ROOT_PASSWORD=senha_docker -e MYSQL_DATABASE=db_docker -p 3306:3306 -d mysql:8.0 --default-authentication-plugin=mysql_native_password
```


```shell
mvn spring-boot:run
```

## Execução do Projeto com Docker

```bash
# development
$ docker-compose up -d

```

## Verifique se as 2 imagens subiram:
```bash
# voting-management-system-api
# voting-management-system-api-db-1
$ docker ps

```
### OBS: caso as 2 não apareçam, repita o comando 'docker-compose up -d'


## Acesse a aplicação pelo browser: http://localhost:8080/swagger-ui/index.html#/

## Estrutura do Projeto

O projeto segue o padrão de estrutura do Maven. Abaixo, um resumo do arquivo `pom.xml`:

```xml
<project xmlns="http://maven.apache.org/POM/4.0.0" ... >
<modelVersion>4.0.0</modelVersion>
<parent>
<groupId>org.springframework.boot</groupId>
<artifactId>spring-boot-starter-parent</artifactId>
<version>3.3.2</version>
<relativePath/> <!-- lookup parent from repository -->
</parent>
<properties>
<java.version>17</java.version>
</properties>
<dependencies>
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-data-jpa</artifactId>
</dependency>
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-security</artifactId>
</dependency>
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-validation</artifactId>
</dependency>
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-web</artifactId>
</dependency>
<dependency>
    <groupId>mysql</groupId>
    <artifactId>mysql-connector-java</artifactId>
    <version>8.0.33</version>
</dependency>
<dependency>
    <groupId>org.springdoc</groupId>
    <artifactId>springdoc-openapi-starter-webmvc-ui</artifactId>
    <version>2.0.2</version>
</dependency>
</dependencies>
        </project>

```


## Rotas da Aplicação

### Login

| Método | Rota                     | Descrição                                  |
|--------|--------------------------|--------------------------------------------|
| POST   | `/login`                 | Logar na aplicação                         |
| POST   | `/collaborator/register` | Criar usuario.                             |


### Colaboradores

| Método | Rota                  | Descrição                                    |
|--------|-----------------------|----------------------------------------------|
| GET    | `/collaborator/{id}`  | Busca um colaborador pelo ID.                |
| GET    | `/collaborator`       | Lista todos os colaboradores.                |
| POST   | `/collaborator`       | Cria um novo colaborador.                    |
| PUT    | `/collaborator/{id}`  | Atualiza um colaborador existente pelo ID.   |
| DELETE | `/collaborator/{id}`  | Remove um colaborador pelo ID.               |

### Pauta

| Método | Rota                               | Descrição            |
|--------|------------------------------------|----------------------|
| POST   | `/voting/agenda`                   | Abrir sessão.        |
| POST   | `/voting/agenda/{agendaId}/vote`   | Votar na sessão.     |
| GET    | `/voting/agenda/{agendaId}/result` | Verificar resultado. |
| PUT    | `/voting/agenda{id}/close`         | Encerrar sessão.     |


## Stay in touch

- Author - https://www.linkedin.com/in/jefferson-coelho/
- Website - https://github.com/BioJJ
- Twitter - https://twitter.com/bio_jefferson
