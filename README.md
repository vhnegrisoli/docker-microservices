# Microsserviços com Docker, Spring Boot, Express.js e RabbitMQ

Este projeto é um exemplo de um pequeno microsserviço de venda e estoque de produtos criado 
com Spring Boot, Express.js, RabbitMQ e Docker.

## Objetivos

O objetivo para a criação deste projeto foi após a finalização de uma certificação da IBM do curso [Docker Essentials: A Developer Introduction](https://cognitiveclass.ai/courses/docker-essentials), em que
eu quis colocar em prática os conhecimentos obtidos com Docker e containers, e meus conhecimentos no desenvolvimento de API REST,
microsserviços, Spring-Boot e Express.

![Certificado](https://uploaddeimagens.com.br/images/002/720/872/original/Victor_Hugo_Negrisoli_Badge_Docker_IBM.jpeg?1592829624)

O certificado pode ser validado [aqui](https://www.youracclaim.com/badges/d73185bf-7890-4409-a14d-d2dac48af7cc).

## Tecnologias

O projeto utiliza 5 containers, e é dividido em 2 APIS: `produto-api` e `venda-api`.

#### Produto-API

* Java 11
* Spring Boot 2.3.1
* Spring Cloud OpenFeign
* API REST
* RabbitMQ
* PostgreSQL 11

#### Venda-API

* Javascript ES6
* Nodemon e Sucrase
* Express.js
* Axios
* RabbitMQ
* MongoDB
* Autenticação com JWT

## Arquitetura do projeto

O projeto utiliza uma arquitetura de microsserviços. São 2 APIs que se comunicam através de chamadas síncronas, utilizando
HTTP Request via padrão REST e chamadas assíncronas, utilizando comunicação de mensagens via RabbitMQ.

A arquitetura é descrita conforme a imagem abaixo:

![Arquitetura da aplicação](https://uploaddeimagens.com.br/images/002/723/394/original/microsservices.io%281%29.png?1592923147)

Serão 5 containers rodando, um para a aplicação Spring-Boot, um para o RabbitMQ, um para a aplicação Express.js, um para o 
banco de dados relacional PostgreSQL e um para o banco de dados NoSQL MongoDB. 

A aplicação `Venda-API` irá gerenciar as vendas e os usuário. Esta é a aplicação que irá criar e autenticar usuários, validando assim seu token de acesso JWT. A aplicação `Produto-API` irá gerenciar os produtos, categorias e fornecedores. A cada requisição feita no `Produto-API`, será necessário informar um `header` com o nome `Authorization` contendo uma token JWT no padrão `Bearer`, exemplo:

`Authorization: Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiaWF0IjoxNTE2MjM5MDIyfQ.SflKxwRJSMeKKF2QT4fwpMeJf36POk6yJV_adQssw5c`

## Instalação

A instalação é feita por partes por se tratarem de projetos com tecnologias diferentes.

#### Produto-API:

Para instalar e dar build na aplicação:

```
mvn clean install
```

Para rodar localmente (recomendo rodar diretamente com os containers do Docker, caso queira rodar local, será necessário 
instalar o banco de dados PostgreSQL e o Rabbit em sua máquina e alterar o arquivo [application.yml](https://github.com/vhnegrisoli/docker-microservices/blob/master/produto-api/src/main/resources/application.yml)
com as configurações locais da máquina):

```
mvn spring-boot:run
```

Estará disponível localmente no endereço: 

`http://localhost:8080

#### Venda-API:

Para instalar as dependências:

```
yarn
```

Para rodar localmente (assim como o `Produto-API`, recomendo rodar diretamente com os containers do Docker, caso queira rodar local, será necessário 
instalar o banco de dados MongoDB e o Rabbit em sua máquina e alterar o arquivo de configuração):

```
yarn start
```

Estará disponível localmente no endereço: 

`http://localhost:3000`

## Build e inicialização dos containers

Primeiramente, será necessário definir uma rede:

```
docker network create dge_microservices
```

##### Build da imagem do PostgreSQL e inicialização do container

Para instalar a imagem:

```
docker pull postgres
```

Para rodar a imagem:

```
docker run --name produto-api-db --network dge_microservices -e "POSTGRES_PASSWORD=produto-api-db" -p 5432:5432 -v C:\db -d postgres 
```

##### Build da imagem do MongoDB e inicialização do container

Para instalar a imagem:

```
docker pull tutum/mongodb
```

Para rodar a imagem:

```
docker run --network dge_microservices --name venda-api-mongo -d -p 27017:27017 -p 28017:28017 -e MONGODB_PASS=no tutum/mongodb
```

##### Inicialização do container do RabbitMQ

```
docker run  --network dge_microservices -d --hostname dge_rabbit --name dge_rabbit -p 8082:15672 -p 5672:5672 -p 25676:25676 rabbitmq:3-management
```

#### Criação da imagem da aplicação Produto-API e inicialização do container:

Para criar a imagem:

```
docker image build -t produto-api .
```

Para rodar o container:

```
docker container run --network dge_microservices --name produto-api -p 8080:8080 -d produto-api
```

Para acompanhar os logs da aplicação:

```
docker logs --follow produto-api
```

#### Criação da imagem da aplicação Venda-API e inicialização do container:

Para criar a imagem:

```
docker image build -t venda-api .
```

Para rodar o container:

```
docker container run --network dge_microservices --name venda-api -p   8081:3000 -d venda-api
```
Para acompanhar os logs da aplicação:

```
docker logs --follow venda-api
```

## Documentação das APIs

As APIs terão documentações para especificar como deve ser enviados os requests e o corpo dos JSON para inserção e atualização dos dados 
e os parâmetros para as requisições GET.

#### Produto-API

Ao inicializar as aplicações corretamente, é possível encontrar a documentação da aplicação `Produto-API` em http://localhost:8080/swagger-ui.html.

A documentação do Swagger será conforme vista abaixo:

![Documentação Produto-API](https://uploaddeimagens.com.br/images/002/720/861/original/produto-api-swagger.png?1592829134)

#### Venda-API (ainda em desenvolvimento)

Ao inicializar as aplicações corretamente, é possível encontrar a documentação da aplicação `Venda-API` em http://localhost:8081/api-docs ou em http://localhost:3000/api-docs caso esteja rodando localmente.

![Documentação Venda-API](https://uploaddeimagens.com.br/images/002/723/414/original/documentacao_venda_api.png?1592923694)

#### Erro de porta já utilizada

Utilize o comando abaixo para encontrar o PID utilizando a porta:

##### Linux

`sudo ss -lptn 'sport = :NUMERO_DA_PORTA`

E em seguida:

`sudo kill -9 PID`

##### Windows

`netstat -a -n -o | find "NUMERO_DA_PORTA"`

E em seguida:

`tskill PID`
