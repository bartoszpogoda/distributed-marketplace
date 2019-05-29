import { Component, OnInit } from '@angular/core';
import { ProductService } from 'src/app/service/product.service';
import { Observable } from 'rxjs';
import { Product } from 'src/app/model/product';

@Component({
  selector: 'app-product-list',
  template: `

    <h1> Marketplace </h1>
    <p> Explore items available on marketplace </p>

    <app-product-entry *ngFor="let product of (products$ | async)" [product]="product"></app-product-entry>
  `
})
export class ExploreTabComponent implements OnInit {

  products$: Observable<Product[]>;

  constructor(private productService: ProductService) { }

  ngOnInit() {
    this.products$ = this.productService.getAll();
  }

}
