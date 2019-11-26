import { Component, OnInit } from '@angular/core';
import { ApiService } from 'src/app/core/api.service';
import { ActivatedRoute } from '@angular/router';
import { Location } from '@angular/common';
import { Item } from 'src/app/core/model/item';

@Component({
  selector: 'app-edit-item',
  templateUrl: './edit-item.component.html',
  styleUrls: ['./edit-item.component.sass']
})
export class EditItemComponent implements OnInit {
  item = new  Item();
  idItem: string;
  itemRecebido = new Item();

  constructor(private apiService: ApiService,
              private route: ActivatedRoute,
              private location: Location) { }

  ngOnInit() {
    this.idItem = this.route.snapshot.paramMap.get('id');
    this.apiService.getItemById(this.idItem).subscribe(item => {
      console.log('Retornou item com sucesso! ');
      this.itemRecebido = item;
    }, error => {
      console.log('Error ao pegar item por ID! ', error);
    });
  }

  update(): void {
    this.item.id = this.idItem;
    this.apiService.updateItem(this.itemRecebido).subscribe( item => {
      console.log("Item atualizado com sucesso!");
      this.goBack();
    }, error => {
      console.log('Error ao atualizar item! ', error);
    });
  }
  goBack() {
    this.location.back();
  }
}
