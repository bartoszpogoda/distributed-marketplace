
export interface Order {
    entries: OrderEntry[];

    totalMinor: number;
    firstName: string;
    familyName: string;
    email: string;
    address: string;
}

export interface OrderEntry {
    productId: number;
    quantity: number;
}
