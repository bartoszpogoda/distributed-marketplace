import { Component, OnInit, Input, Output, EventEmitter } from '@angular/core';
import { BasketEntry } from 'src/app/model/basket';

@Component({
  // tslint:disable-next-line:component-selector
  selector: '[app-basket-entry]',
  template: `
    <td>{{entry.product.title}}</td>
    <td>{{entry.quantity}}</td>
    <td>
      <button (click)="decremented.emit()" type="button" class="btn" style="margin: 3px; font-size: 0.5rem;">-</button>
      <button (click)="incremented.emit()"  type="button" class="btn btn-primary" style="margin: 3px; font-size: 0.5rem;">+</button>
    </td>
    <td>{{entry.subTotalMinor / 100}} zł</td>
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
