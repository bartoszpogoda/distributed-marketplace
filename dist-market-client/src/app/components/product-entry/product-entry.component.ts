import { Component, OnInit, Input } from '@angular/core';
import { Product } from 'src/app/model/product';

@Component({
  selector: 'app-product-entry',
  template: `
    <div class="col-3">
      <div class="card" style="width: 100%;">
        <img class="card-img-top" src="assets/images/product.png" alt="Card image cap">
        <div class="card-body">
          <h5 class="card-title">{{product.title}} <span class="badge badge-secondary">{{product.supplierId}}</span></h5>
          <div class="card-text">
            <p>
            Producer: {{product.supplierName}} <br/>
            Left in stock: {{product.quantity}}
            </p>
          </div>
          <a href="#" class="btn btn-primary">Buy!</a>
        </div>
      </div>
    </div>
  `,
  styleUrls: ['./product-entry.component.css']
})
export class ProductEntryComponent implements OnInit {

  @Input()
  product: Product;

  constructor() { }

  ngOnInit() {
  }

}
