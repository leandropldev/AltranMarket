import { Component, OnInit } from '@angular/core';
import { Usuario } from 'src/app/core/model/usuario';
import { ApiService } from 'src/app/core/api.service';
import { ActivatedRoute } from '@angular/router';
import { Location } from '@angular/common';
import { THIS_EXPR } from '@angular/compiler/src/output/output_ast';

@Component({
  selector: 'app-edit-user',
  templateUrl: './edit-user.component.html',
  styleUrls: ['./edit-user.component.sass']
})
export class EditUserComponent implements OnInit {
  user = new  Usuario();
  idUser: string;
  userRecebido = new Usuario();

  constructor(private apiService: ApiService,
              private route: ActivatedRoute,
              private location: Location) { }

  ngOnInit() {
    this.idUser = this.route.snapshot.paramMap.get('id');
    this.apiService.getUserById(this.idUser).subscribe(user => {
      console.log('Retornou usuário com sucesso! ' + user.nome);
      this.userRecebido = user;
    }, error => {
      console.log('Error ao pegar usuário por ID! ', error);
    });
  }

  update(): void {
    this.user.id = this.idUser;
    this.apiService.updateUser(this.userRecebido).subscribe( user => {
      console.log("User atualizado: " + JSON.stringify(user));
      this.goBack();
    }, error => {
      console.log('Error ao atualizar usuário! ', error);
    });
  }
  goBack() {
    this.location.back();
  }
}
