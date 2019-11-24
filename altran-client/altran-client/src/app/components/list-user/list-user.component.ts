import { Component, OnInit } from '@angular/core';
import { Usuario } from 'src/app/core/model/usuario';
import { Router } from '@angular/router';
import { ApiService } from 'src/app/core/api.service';

@Component({
  selector: 'app-list-user',
  templateUrl: './list-user.component.html',
  styleUrls: ['./list-user.component.sass']
})
export class ListUserComponent implements OnInit {
  users: Usuario[];
  constructor(private router: Router, private apiService: ApiService) { }

  ngOnInit() {
    this.apiService.getUsers().subscribe(users => {
      this.users = users;
    }, error => {
      console.log('Error ao pegar a lista de usuários! ', error);
    });
  }

}
