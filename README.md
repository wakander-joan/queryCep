# Instruções para Testar o Projeto

## Requisitos

### Certifique-se de que sua máquina tenha os seguintes softwares instalados:

- Java 17 ou superior.
- Maven para gerenciamento de dependências.
- Docker.
- Um cliente HTTP como o Postman.

## Passos para Rodar o Projeto


### Configure o Banco de Dados:
Execute o "docker-compose up" no terminal, para subir no docker o banco de dados, o localstack e Wiremock.

Crie um banco de dados "schema" no PostgreSQL com o nome especificado no application.yml.

Atualize as configurações de conexão no arquivo mencionado caso necessário (usuário, senha, URL do banco).


### Build e Execute o Projeto:

- mvn clean install
- mvn spring-boot:run

### Atributos de qualidade
- Disponibilidade > Escalabilidade 

### Importe a Collection do Postman:
- Disponibilizarei uma Collection no projeto
- /colections/postman/ Querycep.postman_collection.json
- Importe a collection no Postman

## Teste a Aplicação
- Ultilize as seguintes rotas para interagir com o sistema:
- GetCep GET

### Acesso a API:
- A API estará disponível no endereço padrão http://localhost:8089.

### Clone o Repositório:
git clone https://github.com/wakander-joan/queryCep.git

### Caso queira testar o envio do log a fila do SQS do localstack
Execute os comandos que estão "infra\sh" no arquivo (init-sqs) no terminal para:
- Execute o 1º comando do arquivo "init-sqs" paraCriar a fila SQS
- Na aplicação - Retire /* ' */ na classe "CepApplicationService" no metodo "buscaCep" para descomentar, E veja a execuxão.
- Execute o 2º comando do arquivo "init-sqs" para, Consomir a fila SQS e ver 1 mensagem no console.

### Observações
Para feedbacks ou problemas, entre em contato por meio das issues no GitHub.

### Boa sorte ao testar a aplicação!
