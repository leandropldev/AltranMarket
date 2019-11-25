import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { WelcomeComponent } from './components/welcome/welcome.component';
import { EditUserComponent } from './components/edit-user/edit-user.component';
import { ListUserComponent } from './components/list-user/list-user.component';
import { RegisterUserComponent } from './components/register-user/register-user.component';
import { RegisterItensComponent } from './components/register-itens/register-itens.component';
import { RegisterCarrinhoComponent } from './components/register-carrinho/register-carrinho.component';

const routes: Routes = [ 
  {path : '', component : WelcomeComponent}, 
  { path: 'register-user', component: RegisterUserComponent },
  { path: 'edit-user/:id', component: EditUserComponent }, 
  { path: 'list-user', component: ListUserComponent }, 
  { path: 'register-itens', component: RegisterItensComponent }, 
  { path: 'register-carrinho', component: RegisterCarrinhoComponent }, 
  { path: 'welcome', component: WelcomeComponent }  
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { 

  
}

