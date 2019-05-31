import { Injectable } from '@angular/core';
import { Product } from '../model/product';
import { Observable, ReplaySubject } from 'rxjs';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class ProductService {

  constructor(private http: HttpClient) { }

  products$ = new ReplaySubject<Product[]>(1);

  getAll(): Observable<Product[]> {
    this.http.get<Product[]>('/marketplace/api/v1/products').subscribe(products => this.products$.next(products));

    return this.products$;
  }

  refresh() {
    this.getAll();
  }

}
