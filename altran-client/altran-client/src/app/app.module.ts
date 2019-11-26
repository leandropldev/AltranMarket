import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { MDBBootstrapModule } from 'angular-bootstrap-md';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { RegisterUserComponent } from './components/register-user/register-user.component';
import { EditUserComponent } from './components/edit-user/edit-user.component';
import { ListUserComponent } from './components/list-user/list-user.component';
import { WelcomeComponent } from './components/welcome/welcome.component';
import { HeaderComponent } from './shared/components/navigation/header/header.component';
import { DeleteUserModalComponent } from './shared/components/modals/delete-user-modal/delete-user-modal.component';
import { HttpClientModule } from '@angular/common/http';
import { ReactiveFormsModule, FormsModule } from '@angular/forms';
import { ApiService } from './core/api.service';
import { RegisterItensComponent } from './components/register-itens/register-itens.component';
import { RegisterCarrinhoComponent } from './components/register-carrinho/register-carrinho.component';
import { ListItemComponent } from './components/list-item/list-item.component';
import { EditItemComponent } from './components/edit-item/edit-item.component';
import { DeleteItemModalComponent } from './shared/components/modals/delete-item-modal/delete-item-modal.component';

@NgModule({
  declarations: [
    AppComponent,
    RegisterUserComponent,
    EditUserComponent,
    ListUserComponent,
    WelcomeComponent,
    HeaderComponent,
    DeleteUserModalComponent,
    RegisterItensComponent,
    RegisterCarrinhoComponent,
    ListItemComponent,
    EditItemComponent,
    DeleteItemModalComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    MDBBootstrapModule.forRoot(),
    HttpClientModule,
    ReactiveFormsModule,
    FormsModule
  ],
  providers: [ApiService],
  bootstrap: [AppComponent]
})
export class AppModule { }
