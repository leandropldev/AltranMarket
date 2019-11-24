import { Usuario } from './usuario';
import { Item } from './item';

export class Carrinho {
    id?: string;
    usuario: Usuario;
    itens: Array<Item>; 
}