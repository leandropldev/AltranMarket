import { Component, OnInit } from '@angular/core';
import { UserLogin } from 'src/app/core/model/userLogin';
import { ApiService } from 'src/app/core/api.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login-user',
  templateUrl: './login-user.component.html',
  styleUrls: ['./login-user.component.sass']
})
export class LoginUserComponent implements OnInit {

  user = new UserLogin();

  constructor(private apiService: ApiService, private router: Router) { }

  ngOnInit() {
  }

  public login() {
    this.apiService.login(this.user).subscribe(data => {
      this.loginSucess(data);
    }, error => {
      console.log('Erro ao realizar login');
    });
  }

  public loginSucess(data: any){
    localStorage.clear();
    localStorage.setItem('accessToken', data.acces_token);
    localStorage.setItem('refreshToken', data.refresh_token);
    this.apiService.getMainUser(localStorage.getItem('accessToken')).subscribe(user => {
      this.redirectPage(user);
    }, error => {
      console.log('Erro ao pegar user logado');
    });
  }

  public redirectPage(user: any) {
    localStorage.setItem('currentUser', JSON.stringify(user));
    this.router.navigate(['welcome']);
  }
}
