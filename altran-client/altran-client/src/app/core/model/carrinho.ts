import { Usuario } from './usuario';
import { ItemCarrinho } from './itemCarrinho';

export class Carrinho {
    id?: string;
    usuario: Usuario;
    itens: Array<ItemCarrinho>;
    valor_total: number; 
}