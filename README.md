# AltranMarket
Projeto SpringAngular para carrinho de compras
<i>Candidato: Leandro Reis</i>

- ## Back end:
**Instrução para acessa da nuvem (heroku):
1) Acesse o link: https://altran-api.herokuapp.com/api/swagger-ui.html#/

**Instrução para rodar pelo docker:
1) no prompt, execute: 
	> docker pull leandropldev/altran-api
	> docker ps -a
	> docker start <CONTAINER_ID> id da imagem baixada
	
2) Acesse o link: http://localhost:8080/api/swagger-ui.html

**Instrução para rodar localmente:
1) Em uma pasta do seu pc abra o promp ou o git bash e digite:
	> git clone https://github.com/leandropldev/AltranMarket.git
2) Navegue até a pasta do projeto e gere um jar com o comando:
	> mvn clean install
3) Acesse a pasta target e execute o comando:
	> java -jar altranSpringBoot-api-0.0.1-SNAPSHOT.jar
4) Acesse o link: http://localhost:8080/api/swagger-ui.html