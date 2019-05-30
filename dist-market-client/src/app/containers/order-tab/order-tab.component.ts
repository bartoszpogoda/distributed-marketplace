import { BasketEntry } from 'src/app/model/basket';
import { BasketService } from './../../service/basket.service';
import { Component, OnInit } from '@angular/core';
import { ProductService } from 'src/app/service/product.service';
import { Observable } from 'rxjs';
import { Product } from 'src/app/model/product';

@Component({
  selector: 'app-order-tab',
  template: `
    <div class="order-tab">
      <div style="text-align: center;">
        <h1>Order</h1>
        <p>Manage your basket and place your order</p>
      </div>

      <app-basket-entry *ngFor="let entry of (basket$ | async)?.entries"
            [entry]="entry"
            (decremented)="onEntryDecremented(entry)"
            (incremented)="onEntryIncremented(entry)">
      </app-basket-entry>

      <p> Total: {{(basket$ | async)?.totalMinor / 100}} zł</p>
      <p> Order details (client details form) </p>

      <a (click)="placeOrderClicked()" class="btn btn-primary">Place order</a>
    </div>
  `,
  styles: [`
    .order-tab {
      background-color: #f5f5f5;
      margin: 15px;
    }
  `]
})
export class OrderTabComponent implements OnInit {

  constructor(private basketService: BasketService) {}

  basket$;

  ngOnInit() {
    this.basket$ = this.basketService.getBasket();
  }

  onEntryDecremented(entry: BasketEntry) {
    this.basketService.decrementQuantity(entry);
  }

  onEntryIncremented(entry: BasketEntry) {
    this.basketService.incrementQuantity(entry);
  }
}
