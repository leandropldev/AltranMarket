import { Component, OnInit, ViewChild, Input, Output, EventEmitter } from '@angular/core';


@Component({
  selector: 'app-delete-user-modal',
  templateUrl: './delete-user-modal.component.html',
  styleUrls: ['./delete-user-modal.component.sass']
})
export class DeleteUserModalComponent implements OnInit {

  @ViewChild('deleteUserModal', {static: false}) public deleteUserModal;
  @Input() recebeItem;
  @Output() resposta = new EventEmitter();
  recebeTitulo = 'Altran Warning';
  recebePergunta = 'Deseja realmente deletar este usuário?';

  constructor() { }

  ngOnInit() {
  }
  onClose(event: any) {
    console.log(event);
  }
  show() {
    this.deleteUserModal.show();
  }
  delete() {
    this.resposta.emit(this.recebeItem);
    this.deleteUserModal.hide();
  }
}
