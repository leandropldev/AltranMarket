import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { WelcomeComponent } from './components/welcome/welcome.component';
import { EditUserComponent } from './components/edit-user/edit-user.component';
import { ListUserComponent } from './components/list-user/list-user.component';
import { RegisterUserComponent } from './components/register-user/register-user.component';
import { LoginUserComponent } from './components/login-user/login-user.component';
import { RegisterItensComponent } from './components/register-itens/register-itens.component';
import { RegisterCarrinhoComponent } from './components/register-carrinho/register-carrinho.component';

const routes: Routes = [ 
  {path : '', component : LoginUserComponent}, 
  { path: 'login', component: LoginUserComponent }, 
  { path: 'register-user', component: RegisterUserComponent }, 
  { path: 'register-itens', component: RegisterItensComponent }, 
  { path: 'register-carrinho', component: RegisterCarrinhoComponent }, 
//  { path: 'resend-register-token', component: ResendRegistrationTokenComponent }, 
  { path: 'welcome', component: WelcomeComponent }, 
  { path: 'list-user', component: ListUserComponent }, 
  { path: 'edit-user/:id', component: EditUserComponent } 
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { 

  
}

