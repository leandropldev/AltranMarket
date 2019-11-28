# Altran
Projeto Spring - Angular para carrinho de compras
##### Candidato: Leandro Reis
**Importante:**
- A url heroku pode ser bloqueada em alguns firewalls corporativos, favor analisar caso a aplicação não abra!
- A base mongo atlas as vezes entra em "modo soneca" de alguns reloads para acordar o banco caso necessário

- ## Database:
Acesse pelo mongo compass
> mongodb+srv://admin:admin123@altrandb-rv3ci.mongodb.net/test

- ## Back end:
**Instrução para acessar da nuvem (heroku):**
1) Acesse o link: https://altran-api.herokuapp.com/api/swagger-ui.html

Documentação com todos os recursos disponibilizados pela aplicação

**Instrução para rodar pelo docker:**
Url da imagem: https://hub.docker.com/repository/docker/leandropldev/altran-api
1) no prompt, execute: 
	> docker pull leandropldev/altran-api
	> docker run -p 8080:8080 leandropldev/altran-api
	
2) Acesse o link: http://localhost:8080/api/swagger-ui.html

Documentação com todos os recursos disponibilizados pela aplicação

**Instrução para rodar localmente:**
1) Faça o download do repositório:
	> git clone https://github.com/leandropldev/AltranMarket.git
2) Navegue até a pasta do projeto (pasta com o pom.xml) e gere um jar com o comando:
	> mvn clean install
3) Acesse a pasta target e execute o comando:
	> java -jar altranSpringBoot-api-0.0.1-SNAPSHOT.jar
4) Acesse o link: http://localhost:8080/api/swagger-ui.html

Documentação com todos os recursos disponibilizados pela aplicação

- ## Front end:
**Instrução para acessar da nuvem (heroku):**
1) Acesse o link: https://altran-client.herokuapp.com/

Documentação com todos os recursos disponibilizados pela aplicação

**Instrução para rodar pelo docker:**
Url da imagem: https://hub.docker.com/repository/docker/leandropldev/altran-client
1) no prompt, execute: 
	> docker pull leandropldev/altran-client
	> docker run -it -p 4200:4200 --rm leandropldev/altran-client
	
2) Acesse o link: http://localhost:4200/

**Instrução para rodar localmente:**
1) Faça o download do repositório:
	> git clone https://github.com/leandropldev/AltranMarket.git
2) Navegue até a pasta do projeto e execute o comando:
	> npm install
	
	> ng serve
3) Acesse o link: http://localhost:4200/

- ## Metodologia de trabalho**
1) Mapear operações sistêmicas
2) Modelar collections usuario, item, item_carrinho e carrinho
3) implementar e expor recursos rest
4) implementar testes unitários (cobertura em 87%)
5) implementar client para consumir recursos (angular 8)
6) adicionar suporte a Docker
7) subir na nuvem
