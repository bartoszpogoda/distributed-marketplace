import { Product } from "./product";

export interface Order {
    entries: OrderEntry[];
}

export interface OrderEntry {
    productId: number; 
    quantity: number;
}
