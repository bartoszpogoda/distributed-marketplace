import { BasketService } from './../../service/basket.service';
import { Component, OnInit } from '@angular/core';
import { ProductService } from 'src/app/service/product.service';
import { Observable } from 'rxjs';
import { Product } from 'src/app/model/product';

@Component({
  selector: 'app-product-list',
  template: `
    <div style="text-align: center; display: flex; justify-content: space-between;">
      <div style="width: 90px;"></div>
      <div><h1> Marketplace </h1>
      <p> Explore items available on marketplace  </p></div>
      <div style="display: flex; flex-direction: column; justify-content: center;">
        <button style="height: 50px; width: 90px;" class="btn btn-secondary" (click)="onRefresh()">Refresh</button>
      </div>
    </div>
    <div class="row">
      <div class="col-6 col-lg-3" *ngFor="let product of (products$ | async)" style="margin-bottom: 5px;">
        <app-product-entry
          [product]="product"
          (added)="addToBasket($event)">
        </app-product-entry>
      </div>
    </div>
  `
})
export class ExploreTabComponent implements OnInit {

  products$: Observable<Product[]>;

  constructor(
    private productService: ProductService,
    private basketService: BasketService
  ) { }

  ngOnInit() {
    this.products$ = this.productService.getAll();
  }

  addToBasket(product: Product) {
    this.basketService.addToBasket(product);
  }

  onRefresh() {
    this.productService.refresh();
    this.basketService.clear();
  }

}
