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
  templateUrl: './register-carrinho.component.html',
  styleUrls: ['./register-carrinho.component.sass']
})
export class RegisterCarrinhoComponent implements OnInit {
  
  usuario: Usuario = new Usuario();

  idItem: string; //item recebido no request
  itemRecebido: Item; //item recuperado da base

  itemsCarrinhoArray: ItemCarrinho[] = []; //array que ira pra view
  total_compra: number = 0; //total que ira pra view

  carrinhoFinal: Carrinho = new Carrinho();

  msg_error: string = "";
  msg_finalizarCompra: string = "";
  msg_sucesso: string = "";

  constructor(private apiService: ApiService,
    private route: ActivatedRoute,
    private location: Location
    ) { }

  finalizarCompra(): void {
    
    if(this.usuario.nome == null){
      this.msg_finalizarCompra = "É necessário informar o usuário antes de finalizar a compra!";
    } else if (this.itemsCarrinhoArray.length == 0) {
      this.msg_finalizarCompra = "Carrinho de compras vazio!";
    } else {
      console.log("Tudo ok, vamos finalizar a compra");
      this.carrinhoFinal.usuario = this.usuario;
      this.carrinhoFinal.itens = this.itemsCarrinhoArray;
      this.carrinhoFinal.valor_total = this.total_compra;
      
      this.apiService.createCarrinho(this.carrinhoFinal).subscribe(carrinho => {
        console.log("Carrinho enviado com sucesso!");
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

  ngOnInit() {
    this.idItem = this.route.snapshot.paramMap.get('id');
    if(this.idItem){
      this.apiService.getItemById(this.idItem).subscribe(itemRecebido => {

        this.itemRecebido = itemRecebido;

        var itemCarrinho: ItemCarrinho = {
          item: this.itemRecebido,
          quantidade: 1,
          valor_somado: this.itemRecebido.valor
        };

        if(localStorage.getItem('carrinho') == null) {

          let carrinho: any = [];
          carrinho.push(JSON.stringify(itemCarrinho));
          localStorage.setItem('carrinho', JSON.stringify(carrinho));

        } else {

          let carrinho: any = JSON.parse(localStorage.getItem('carrinho'));
          let index: number = -1;

          for(var i = 0; i < carrinho.length; i++){
            let itemCarrinho: ItemCarrinho = JSON.parse(carrinho[i]);
            if (itemCarrinho.item.id == this.idItem) {
              index = i;
							break;
						}
          }
          //pra saber se precisa somar a quantidade !!
					if (index == -1) {
						//item recebido não esta no array da variavel ambiente
						//envia o item pra variavel ambiente
						carrinho.push(JSON.stringify(itemCarrinho));
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
		//pega o array da variavel ambiente
		let cart = JSON.parse(localStorage.getItem('carrinho'));
		
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

  remove(id: string): void {
    let cart: any = JSON.parse(localStorage.getItem('carrinho'));
    let index: number = -1;
    for (var i = 0; i < cart.length; i++) {
      let itemCarrinho: ItemCarrinho = JSON.parse(cart[i]);
      if (itemCarrinho.item.id == id) {
        cart.splice(i, 1);
        break;
      }
    }
    localStorage.setItem("carrinho", JSON.stringify(cart));
    this.loadCart();
  }

  
}
