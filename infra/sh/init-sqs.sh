#!/bin/bash

# Criar a fila SQS
aws --profile localstack --endpoint-url=https://localhost.localstack.cloud:4566 sqs create-queue --queue-name query-cep-queue

# Criar o bucket S3 my-bucket no LocalStack
aws --profile localstack --endpoint-url=https://localhost.localstack.cloud:4566 s3api create-bucket --bucket my-bucket

# Verifica se o bucket foi criado
aws --profile localstack --endpoint-url=https://localhost.localstack.cloud:4566 s3api list-buckets

# Faça upload do ZIP para o S3
aws --profile localstack --endpoint-url=https://localhost.localstack.cloud:4566 s3 cp lambda-package.zip s3://my-bucket/

# Criar a função Lambda apontando para o S3Bucket
aws --profile localstack --endpoint-url=https://localhost.localstack.cloud:4566 lambda create-function `
    --function-name my-lambda-function `
    --runtime java17 `
    --role arn:aws:iam::000000000000:role/lambda-role `
    --handler com.empresa.querycep.handler.SqsHandler `
    --code S3Bucket=my-bucket,S3Key=lambda-package.zip

# Listar lambdas
aws --profile localstack --endpoint-url=https://localhost.localstack.cloud:4566 lambda list-functions

# Associar o Lambda com a fila SQS
aws --profile localstack --endpoint-url=https://localhost.localstack.cloud:4566 lambda create-event-source-mapping `
    --function-name my-lambda-function `
    --event-source-arn arn:aws:sqs:us-east-1:000000000000:query-cep-queue `
    --enabled `
    --batch-size 10