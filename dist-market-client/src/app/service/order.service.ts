import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Order } from '../model/order';
import { Basket } from '../model/basket';
import { FormGroup } from '@angular/forms';

@Injectable({
  providedIn: 'root'
})
export class OrderService {

  constructor(private http: HttpClient) { }


  createOrder(basket: Basket, clientForm: FormGroup): Observable<any> {

    const entries = basket.entries.map(entry => {
      return {
        productId: entry.product.id,
        quantity: entry.quantity
      };
    });

    const order: Order = {
      entries: entries,
      address: clientForm.controls.address.value,
      email: clientForm.controls.email.value,
      familyName: clientForm.controls.familyName.value,
      firstName: clientForm.controls.firstName.value,
      totalMinor: basket.totalMinor
    };

    return this.http.post('/marketplace/api/v1/orders', order);
  }

}
