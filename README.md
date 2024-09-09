# Desafio Técnico: Petize


## Descrição
Este projeto implementa uma loja online simplificada como desafio técnico para a Petize. A aplicação foi desenvolvida utilizando Spring Boot, Java 17, e uma arquitetura de microsserviços. Os serviços se comunicam através de mensagens utilizando RabbitMQ.


* **Cadastro de produtos**
* **Gerenciamento de pedidos**
* **Processamento de pagamentos**

- Foram criados endpoints conforme enunciado para cadastro de produtos, pedidos e mudança de status de pedido
- Foi implementada comunicação entre os microsserviços de pagamento (produtor) e pedidos(consumidor)
- O pagamento-service foi criado meramente para ativar a mensagem que ao ser recebida pelo pedido-service causa a mudança de status do pedido


## Pré-requisitos
* Docker
* Docker Compose
* Java 17
* Git

## Instalação
1. **Clone o repositório:**
   ```bash
   git clone [https://github.com/vagnersantosdasilva/desafio.git](https://github.com/vagnersantosdasilva/desafio.git)
   cd desafio

## Clonagem do repositório
1.**Clone o repositório:**
   ```bash
   git@github.com:vagnersantosdasilva/desafio.git
   ```
## Instalação
2.**Baixe e execute as imagens**
    ```bash
    docker-compose up -d
    ```
