import { Product } from "./product";

export interface Basket {
    entries: BasketEntry[];
    totalMinor: number;
}

export interface BasketEntry {
    product: Product;
    quantity: number;
    subTotalMinor: number;
}
