# Demo da palestra Spring WebFlux + Spring Cloud: desenvolvendo microsserviços reativos em Java apresentada na MVPCONF Latam 2021


## Tecnologias utilizadas:

- Spring Web
- Spring Webflux
- Spring Cloud : Eureka, Sleuth e Feign
- Mongo DB
- H2 Database
- Webclient
- Reactor Test
- Junit
- Mockito


## Como testar as aplicações 


### Primeiro execute o Eureka Server

### Depois execute a Customer-API

Criando um novo cliente

  ````
curl --location --request POST 'http://localhost:8081/customers' \
--header 'Content-Type: application/json' \
--data-raw '{
    "name": "kamila",
    "address": "rua do teste"
}

'
````

Listando todos os clientes cadastrados

`````
- curl --location --request GET 'http://localhost:8081/customers' \

`````

Buscando um cliente pelo id


`````
- curl --location --request GET 'http://localhost:8081/customers/6187105b6380032a24e45711' \
`````

Atualizando um cliente

`````
- curl --location --request PUT 'http://localhost:8081/customers/6187105b6380032a24e45711' \
--header 'Content-Type: application/json' \
--data-raw '{
    "name": "cliente atualizada",
    "address": "rua do teste"
}

'

`````

Exluindo um cliente


`````
- curl --location --request DELETE 'http://localhost:8081/customers/6187105b6380032a24e45711'
`````

### Execute a Order API

Consultando as informações de um cliente usando o Spring Cloud Open Feign:


````
curl --location --request GET 'http://localhost:8082/orders/customer-data/6185a1ee4c402f62c8d4a341'
````

Criando um nova ordem

````
curl --location --request POST 'http://localhost:8082/orders' \
--header 'Content-Type: application/json' \
--data-raw '{
    "description": "ordem de teste",
    "amountValue": 1000,
    "customerId": "123"


}'

````

Listando todas as ordens criadas

````
curl --location --request GET 'http://localhost:8082/orders'
````

Buscando uma ordem pelo id

````
curl --location --request GET 'http://localhost:8082/orders/2'
````


### Agora, execute a Delivery API

Buscando as ordens para serem entregues usando o Webclient (nativo do Spring Webflux)

````
curl --location --request GET 'http://localhost:8083/delivery/orders'
````


