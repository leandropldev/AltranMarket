import { Item } from './item';

export class ItemCarrinho {
    id?: string;
    item: Item;
    quantidade: number;
    valor_somado: Number;
}