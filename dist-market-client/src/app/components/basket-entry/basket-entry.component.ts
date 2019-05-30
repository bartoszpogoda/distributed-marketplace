import { Component, OnInit, Input, Output, EventEmitter } from '@angular/core';
import { BasketEntry } from 'src/app/model/basket';

@Component({
  selector: 'app-basket-entry',
  template: `
    <div style="display: flex; justify-content: space-between;">
      <div> {{entry.product.title}} </div>
      <div style="display: flex; width: 100px; justify-content: space-between; margin: 5px;
      ">
        <button (click)="decremented.emit()" type="button" class="btn">-</button>
        <div>{{entry.quantity}}</div>
        <button (click)="incremented.emit()"  type="button" class="btn btn-primary">+</button>
      </div>
      <div>{{entry.subTotalMinor / 100}} zł</div>
    </div>
  `
})
export class BasketEntryComponent implements OnInit {

  @Input()
  entry: BasketEntry;

  @Output()
  incremented = new EventEmitter();

  @Output()
  decremented = new EventEmitter();

  constructor() { }

  ngOnInit() {
  }

}
