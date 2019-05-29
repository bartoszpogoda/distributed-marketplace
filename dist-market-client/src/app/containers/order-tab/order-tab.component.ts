import { Component, OnInit } from '@angular/core';
import { ProductService } from 'src/app/service/product.service';
import { Observable } from 'rxjs';
import { Product } from 'src/app/model/product';

@Component({
  selector: 'app-order-tab',
  template: `
    <div class="order-tab">
      <h1>Order</h1>
      <p>Manage your basket and place your order</p>

      <p> Basket </p>
      <p> Order details </p>
      <button>Place order!</button>
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

  ngOnInit() {
  }

}
