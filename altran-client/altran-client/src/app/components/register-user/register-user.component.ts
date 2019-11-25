import { Component, OnInit } from '@angular/core';
import { ApiService } from 'src/app/core/api.service';
import { Location } from '@angular/common';
import { Usuario } from 'src/app/core/model/usuario';
import { Router } from '@angular/router';

@Component({
  selector: 'app-register-user',
  templateUrl: './register-user.component.html',
  styleUrls: ['./register-user.component.sass']
})
export class RegisterUserComponent implements OnInit {

  public user = new Usuario();
  constructor(private apiService: ApiService, private location: Location, private router: Router) { }

  ngOnInit() {
  }

  save(): void {
    this.apiService.registerUser(this.user).subscribe(data => {
      console.log('Usuários registrado com sucesso!');
      //this.goBack();
      this.router.navigate(['list-user']);
    }, error => {
      console.log('Error ao criar usuário ', error);
    });
  }

  goBack(){
    this.location.back();
  }
}
