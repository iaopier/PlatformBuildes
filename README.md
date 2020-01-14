API de Clientes

O Banco de Dados MySQL está em um container docker, para sua execução basta:
docker-compose.yml up mysqldb

A aplicação está dentro de PlatformBuilders

Para executar a aplicação basta executar o maven com mvn install e mvn spring-boot:run

Decisões de projeto

Para a busca foi utilizado o CPF como único pois não podem existir CPFS duplicados. Existe a busca por CPF e por NOME com paginação para quando a busca
é feita por nome. Pois assim é possível existir mais de um registro com o mesmo nome, o que não acontece com o CPF que é único.

Para a Idade, foi criada uma classe DTO que extende a classe cliente e cria o campo idade para cálculo da idade e retorno dela. No entanto, para criação
de registro de clientes não é requerido o campo idade no body request.

O Postman com os testes está na raiz do projeto. Existe o arquivo Testing PlatformBuilders.postman_collection.json que é a coleção de testes executados e 
Testing PlatformBuilders.postman_test_run.json que é o resultado da execução.

Foram realizados TDD com JUnit dentro do projeto.

