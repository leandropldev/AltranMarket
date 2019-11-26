import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { ApiService } from 'src/app/core/api.service';
import { Item } from 'src/app/core/model/item';

@Component({
  selector: 'app-list-item',
  templateUrl: './list-item.component.html',
  styleUrls: ['./list-item.component.sass']
})
export class ListItemComponent implements OnInit {
  items: Item[];
  constructor(private router: Router, private apiService: ApiService) { }

  ngOnInit() {
    this.apiService.getItens().subscribe(item => {
      console.log(JSON.stringify(item));
      this.items = item;
    }, error => {
      console.log('Error ao pegar a lista de Itens! ', error);
    });
  }
  
  deleteItem(item: Item): void {
    this.apiService.deleteItem(item.id).subscribe(() => {
      this.items = this.items.filter(u => u.id !== item.id);

    }, error => {
      console.log('Error ao deletar usuário! ', error);
    });
  }
}
