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
  error_msg: string = "";
  ngOnInit() {
  }

  save(): void {
    this.apiService.registerUser(this.user).subscribe(data => {
      console.log('Usuários registrado com sucesso!');
      this.router.navigate(['list-user']);
    }, errors => {
      this.error_msg = errors.error.message;
      console.log('Error ao criar usuário ', errors.error.message);
    });
  }

  goBack(){
    this.location.back();
  }
}
