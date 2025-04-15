# API de Gerenciamento de Produtos com GraphQL e Redis

<div>
  <img src="https://img.shields.io/badge/Java-17-%23ED8B00?logo=java" alt="Java 17">
  <img src="https://img.shields.io/badge/Spring_Boot-3.1.5-%236DB33F?logo=spring" alt="Spring Boot">
  <img src="https://img.shields.io/badge/GraphQL-E10098?logo=graphql" alt="GraphQL">
  <img src="https://img.shields.io/badge/Redis-DC382D?logo=redis" alt="Redis">
</div>

## 📋 Visão Geral
API GraphQL para gerenciamento de produtos com cache Redis, permitindo operações CRUD completas e consultas otimizadas.

## 🛠 Pré-requisitos
- Docker
- Java 17 (JDK)
- Maven

## Configuração

### Iniciar Container Redis
```bash
docker run --name redis-produtos -p 6379:6379 -d redis
```
## Exemplos de Requisições GraphQL
### Criar produto
``curl -X POST http://localhost:8080/graphql \
  -H "Content-Type: application/json" \
  -d '{"query":"mutation { createProduct(name: \"Notebook\", price: 5000) { id } }"}'
  ``
### Consultar Todos os Produtos
``curl -X POST http://localhost:8080/graphql \
  -H "Content-Type: application/json" \
  -d '{"query":"query { products { id name price stock } }"}'
  ``

### Consultar Produto por ID
``curl -X POST http://localhost:8080/graphql \
  -H "Content-Type: application/json" \
  -d '{"query":"query { productById(id: \"prod1\") { id name price } }"}'``

### Consultar por Categoria
``curl -X POST http://localhost:8080/graphql \
  -H "Content-Type: application/json" \
  -d '{"query":"query { productsByCategory(category: \"Eletrônicos\") { id name } }"}'``

### Atualizar Produto
``
curl -X POST http://localhost:8080/graphql \
-H "Content-Type: application/json" \
-d '{
"query": "mutation {
updateProduct(
id: \"prod1\",
price: 4599.99
) { id name price }
}"
}'
``
### Deletar Produto
``curl -X POST http://localhost:8080/graphql \
  -H "Content-Type: application/json" \
  -d '{"query":"mutation { deleteProduct(id: \"prod1\") }"}'``
