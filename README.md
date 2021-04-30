### Desafio:

Criar uma API REST em Java e MySQL que gerencie e disponibilize uma agenda de contatos de pessoas. Neste sistema, deve conter:
- Endpoint para cadastro, edição, exclusão e listagem de contatos.
- Cada contato deve conter apenas:
    - Id
    - Primeiro Nome
    - Último Nome
    - E-mail
    - Telefones (plural)
- Todos os campos acima são obrigatórios. O usuário deve ter no mínimo 1 telefone.
- Na listagem, deve ser possível realizar filtro por nomes ou email.
- Deve ser entregue o código fonte e o manual de instalação em algum repositório ou link para download - google drive ou bitbucket por exemplo
- Se possível, mas não obrigatório, a utilização de docker compose para subir o ambiente

-----------

### Documentação da API:
- http://localhost:8080/swagger-ui.html

### Como rodar o projeto:
* #### Docker
  * Execute o comando
    ```
      docker-compose up -d
    ```
* #### Local
  * Execute os comandos
    ```
      mvn install
    
      mvn spring-boot:run
    ```
