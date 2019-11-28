import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { ApiService } from 'src/app/core/api.service';
import { Location } from '@angular/common';
import { Item } from 'src/app/core/model/item';
import { ItemCarrinho } from 'src/app/core/model/itemCarrinho';
import { Carrinho } from 'src/app/core/model/carrinho';
import { Usuario } from 'src/app/core/model/usuario';

@Component({
  selector: 'app-register-carrinho',
  //informa qual pagina html irá se comunicar com este componet
  templateUrl: './register-carrinho.component.html',
  styleUrls: ['./register-carrinho.component.sass']
})
export class RegisterCarrinhoComponent implements OnInit {
  
  /*
  Os itens abaixo serão utilizados no html
  é uma comunicação de mão dupla, ou seja:
  Tanto o componente pode atualizar dados que serão visualizados no html, 
  como o html pode enviar dados para serem usados no componete
  */
  usuario: Usuario = new Usuario(); 

  idItem: string; //item recebido no request (botao comprar da view 'listar produtos')
  itemRecebido: Item; //item recuperado do banco de dados (back end)

  itemsCarrinhoArray: ItemCarrinho[] = []; //tabela de item comprado
  total_compra: number = 0; //total da compra

  carrinhoFinal: Carrinho = new Carrinho(); //objeto que ira para o banco de dados (back end)

  //mensagens de erros que serão visualizados no html
  msg_error: string = "";
  msg_finalizarCompra: string = "";
  msg_sucesso: string = "";

  //construtou inicializado automaticamente junto com a classe
  //tem a função de carregar itens obrigatórios para o funcionamento das funções
  constructor(private apiService: ApiService,
    private route: ActivatedRoute,
    private location: Location
    ) { }

  //este metodo sera chamado pelo html   
  finalizarCompra(): void {
    
    //valida se o usuario foi informado antes de finalizar a compra
    if(this.usuario.nome == null){
      this.msg_finalizarCompra = "É necessário informar o usuário antes de finalizar a compra!";
    //valida se tem pelo menos UM ITEM no carrinho de compras
    } else if (this.itemsCarrinhoArray.length == 0) {
      this.msg_finalizarCompra = "Carrinho de compras vazio!";
    } else {
      console.log("Tudo ok, vamos finalizar a compra");

      //preenchendo as variáveis que irão para o back end
      this.carrinhoFinal.usuario = this.usuario;
      this.carrinhoFinal.itens = this.itemsCarrinhoArray;
      this.carrinhoFinal.valor_total = this.total_compra;
      
      //chamada Http para o back end
      //a chamada é feita para uma outra classe de serviço que monta o request (url, verbo'post', objeto, headers) e envia 
      //aqui, ao dar subscribe, recebemos de fato a resposta do back end
      this.apiService.createCarrinho(this.carrinhoFinal).subscribe(carrinho => {
        console.log("Carrinho enviado com sucesso!");
        //limpar os campos do html
        this.carrinhoFinal = new Carrinho();
        this.usuario = new Usuario();
        this.itemsCarrinhoArray = [];
        this.msg_error = "";
        this.msg_finalizarCompra = "";
        this.msg_sucesso = "";
        this.total_compra = 0;
        localStorage.setItem('carrinho', JSON.stringify(this.itemsCarrinhoArray));
        this.msg_sucesso = "Compra realizada com sucesso!";
      }, error => {
        console.log("Erro ao enviar carrinho: ", error);
      });
    }
  }

  //busca usuario da base e atualiza o html
  buscarUsuario(): void {
    this.apiService.getUserById(this.usuario.id).subscribe(user => {
      this.usuario = user;
      this.msg_error = "";
    }, error => {
      this.msg_error = "usuario não encontrado!";
      this.usuario = new Usuario();
      console.log("Usuario não localizado!!");
    });
  }

  //Inicializa junto com o componet, mesma lógica do construtor, porém aqui a inicialização é mais performática
  ngOnInit() {

    //pega o id do produto que venho da requisição (botão comprar 'lista-itens')
    //e guarda na variavel 
    this.idItem = this.route.snapshot.paramMap.get('id');
    if(this.idItem){

      //faz uma chamada no back end para ver se existe um item com o id informado
      //caso exista, retorna o item equivalente
      this.apiService.getItemById(this.idItem).subscribe(itemRecebido => {

        //salva na memória o iten recebido
        this.itemRecebido = itemRecebido;

        //cria o primeiro registro de item a ser vendido
        //primeiro item da tabela do html
        var itemCarrinho: ItemCarrinho = {
          item: this.itemRecebido,
          quantidade: 1,
          valor_somado: this.itemRecebido.valor
        };

        //verifica se  NÃO existe um item em memória chamdo carrinho
        if(localStorage.getItem('carrinho') == null) {

          //localStorage é um objeto excluvivo do navegador em que a aplicação esta rodando
          //o código apenas cria e/ou recupera os dados deste objeto
          //os dados são compostos por chave e valor
          //no caso iremos criar um dado dentro deste objeto com a chave de nome 'carrinho'
          //o valor desta chave será nossa lista de itens a ser vendido
          //a função setItem cria ou 'recupera' a chave especificada
          let carrinho: any = [];
          carrinho.push(JSON.stringify(itemCarrinho));
          localStorage.setItem('carrinho', JSON.stringify(carrinho));

        } else {
          //neste caso já temos um objeto com a chave 'carrinho' guardado na memória do navegador
          let carrinho: any = JSON.parse(localStorage.getItem('carrinho'));
          let index: number = -1;

          //vamos percorrer esse dado e procuparar se dentro da lista, existe um item igual ao item que estamos tentando adicionar no carrinho
          for(var i = 0; i < carrinho.length; i++){
            let itemCarrinho: ItemCarrinho = JSON.parse(carrinho[i]);
            if (itemCarrinho.item.id == this.idItem) {
              index = i;
							break;
						}
          }
          //pra saber se precisa somar a quantidade !!
          //se o index estiver -1, significa que o novo item não é repetido na lista
					if (index == -1) {
						//item recebido não esta no array da variavel ambiente
						//envia o item pra variavel ambiente
            carrinho.push(JSON.stringify(itemCarrinho));
            //atualizamos a lista em memória com o novo item
						localStorage.setItem('carrinho', JSON.stringify(carrinho));
					} else {
						//item esta no array
						//cria um item baseado no item que já estava no array
						let itemCarrinho: ItemCarrinho = JSON.parse(carrinho[index]);
						//atualiza a quantidade
						itemCarrinho.quantidade += 1;
						carrinho[index] = JSON.stringify(itemCarrinho);
						//atualiza a variavel ambiente
						localStorage.setItem("carrinho", JSON.stringify(carrinho));
					}  
        }
        //uma vez que a lógica foi aplicada, é hora de enviar os dados para a tela html
        this.loadCart();
      }, error => {
        console.log("Erro ao recuperar o item!");
      });
    } else {
      this.loadCart();
    }
  }

  loadCart(): void {
		this.total_compra = 0;
		this.itemsCarrinhoArray = [];
    //recupera o dado da chave "carrinho" que esta em memória
    //no caso, este dado é um array
		let cart = JSON.parse(localStorage.getItem('carrinho'));
    
    //pega cada item do array que estava em memória
    //atualiza o sub total dele
    //atualiza o valor total da compra
    //envia o array para a variável array que é exibida no html
    for (var i = 0; i < cart.length; i++) {
      //atualiza o item da <<view>> com o array que pegou da variavel ambiente
      let item = JSON.parse(cart[i]);
      this.itemsCarrinhoArray.push({
        item: item.item,
        quantidade: item.quantidade,
        valor_somado: item.item.valor * item.quantidade
      });
      //atualiza o total da compra da view
      this.total_compra += item.item.valor * item.quantidade;
      this.carrinhoFinal.valor_total = this.total_compra;
    }
  }

  //o id é enviado da página html
  remove(id: string): void {
    //recupera o dado em memório, no caso um array
    //percorre o array procurando o item com o id que foi enviado
    //remove através do splice o item do array
    let cart: any = JSON.parse(localStorage.getItem('carrinho'));
    let index: number = -1;
    for (var i = 0; i < cart.length; i++) {
      let itemCarrinho: ItemCarrinho = JSON.parse(cart[i]);
      if (itemCarrinho.item.id == id) {
        cart.splice(i, 1);
        break;
      }
    }

    //atualiza o dado em memória com o novo array
    localStorage.setItem("carrinho", JSON.stringify(cart));
    this.loadCart();
  }

  
}
