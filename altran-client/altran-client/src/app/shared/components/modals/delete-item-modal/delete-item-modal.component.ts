import { Component, OnInit, ViewChild, Input, Output, EventEmitter } from '@angular/core';


@Component({
  selector: 'app-delete-item-modal',
  templateUrl: './delete-item-modal.component.html',
  styleUrls: ['./delete-item-modal.component.sass']
})
export class DeleteItemModalComponent implements OnInit {

  @ViewChild('deleteItemModal', {static: false}) public deleteItemModal;
  @Input() recebeItem;
  @Output() resposta = new EventEmitter();
  recebeTitulo = 'Altran Warning';
  recebePergunta = 'Deseja realmente deletar este Item?';

  constructor() { }

  ngOnInit() {
  }
  onClose(event: any) {
    console.log(event);
  }
  show() {
    this.deleteItemModal.show();
  }
  delete() {
    console.log("entrou aqui");
    this.resposta.emit(this.recebeItem);
    this.deleteItemModal.hide();
  }
}
