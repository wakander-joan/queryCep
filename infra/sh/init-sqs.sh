# Criar a fila SQS
aws --profile localstack --endpoint-url=https://localhost.localstack.cloud:4566 sqs create-queue --queue-name query-cep-queue

# Consome a fila SQS e ver 1 mensagem
aws --endpoint-url=http://localhost.localstack.cloud:4566 sqs receive-message --queue-url=http://localhost.localstack.cloud:4566/000000000000/query-cep-queue --max-number-of-messages 1 --wait-time-seconds 20