import { Injectable } from '@angular/core';
import { BehaviorSubject, Observable } from 'rxjs';
import { Basket, BasketEntry } from '../model/basket';
import { Product } from '../model/product';
import { isDefined } from '@angular/compiler/src/util';

@Injectable({
  providedIn: 'root'
})
export class BasketService {

  constructor() { }

  emptyBasket: Basket = {
    entries: [],
    totalMinor: 0
  };

  $basket = new BehaviorSubject<Basket>(this.emptyBasket);

  getBasket(): Observable<Basket> {
    return this.$basket;
  }

  addToBasket(product: Product): void {
    const basket = { ...this.$basket.getValue() };
    const foundEntry = basket.entries.find(ent => ent.product.id === product.id);

    if (isDefined(foundEntry)) {
      this.$basket.next(this.changeQuantity(this.$basket.getValue(), foundEntry, 1));
    } else {
      basket.entries.push({
        product: product,
        quantity: 1,
        subTotalMinor: 0
      });

      this.recalculateTotals(basket);

      this.$basket.next(basket);
    }
  }

  clear() {
    this.$basket.next(this.emptyBasket);
  }

  decrementQuantity(entry: BasketEntry) {
    this.$basket.next(this.changeQuantity(this.$basket.getValue(), entry, -1));
  }

  incrementQuantity(entry: BasketEntry) {
    this.$basket.next(this.changeQuantity(this.$basket.getValue(), entry, 1));
  }

  private changeQuantity(basket: Basket, entry: BasketEntry, change: number): Basket {
    let entries = [...basket.entries];
    const entryToChange = entries.find(en => en.product.id === entry.product.id);

    const newQuantity = entryToChange.quantity + change;
    if (newQuantity <= 0) {
      entries = entries.filter(ent => ent.product.id !== entry.product.id);
    } else if (newQuantity <= entry.product.quantity) {
      entryToChange.quantity = entryToChange.quantity + change;
    }

    const newBasket = {
      entries: entries,
      totalMinor: 0
    };

    this.recalculateTotals(newBasket);
    return newBasket;
  }

  private recalculateTotals(basket: Basket) {
    basket.entries.forEach(entry => {
      entry.subTotalMinor = entry.product.priceMinor * entry.quantity;
    });

    if (basket.entries.length === 0) {
      basket.totalMinor = 0;
    } else {
      basket.totalMinor = basket.entries.map(ent => ent.subTotalMinor).reduce((a, b) => a + b);
    }
  }

}
