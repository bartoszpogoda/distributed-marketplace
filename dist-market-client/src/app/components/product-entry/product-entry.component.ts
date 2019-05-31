import { Component, OnInit, Input, EventEmitter, Output } from '@angular/core';
import { Product } from 'src/app/model/product';

@Component({
  selector: 'app-product-entry',
  template: `
    <div class="card" style="width: 100%;">
      <img class="card-img-top" src="assets/images/product.png" alt="Card image cap">
      <div class="card-body">
        <h5 class="card-title">{{product.title}} <span class="badge badge-secondary">{{product.supplierId}}</span></h5>
        <div class="card-text">
          <p>{{product.description}}</p>
          <p>
          Producer: {{product.supplierName}} <br/>
          Left in stock: {{product.quantity}} <br/>
          Price: {{product.priceMinor / 100}} zł
          </p>
        </div>
        <button (click)="addToOrderClicked()" class="btn btn-primary btn-block">Add to order</button>
      </div>
    </div>
  `,
  styleUrls: ['./product-entry.component.css']
})
export class ProductEntryComponent implements OnInit {

  @Input()
  product: Product;

  @Output()
  added = new EventEmitter<Product>();

  constructor() { }

  ngOnInit() {
  }

  addToOrderClicked() {
    this.added.emit(this.product);
  }

}
