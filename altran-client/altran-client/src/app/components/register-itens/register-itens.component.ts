import { Component, OnInit } from '@angular/core';
import { ApiService } from 'src/app/core/api.service';
import { Location } from '@angular/common';
import { Item } from 'src/app/core/model/item';
import { Router } from '@angular/router';

@Component({
  selector: 'app-register-itens',
  templateUrl: './register-itens.component.html',
  styleUrls: ['./register-itens.component.sass']
})
export class RegisterItensComponent implements OnInit {
  
  public item = new Item();
  constructor(private apiService: ApiService, private location: Location, private router: Router) { }
  error_msg:string = "";
  ngOnInit() {
  }

  save(): void {
 
    this.apiService.registerItem(this.item).subscribe(item => {
      console.log('Item cadastrado com sucesso!');
      this.router.navigate(['list-itens']);
    }, errors => {
      this.error_msg = errors.error.message;
      console.log('Error ao cadastrar item ', errors);
    });
  }
}
