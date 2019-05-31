import { AlertService } from './../../service/alert.service';
import { BasketEntry, Basket } from 'src/app/model/basket';
import { BasketService } from './../../service/basket.service';
import { Component, OnInit } from '@angular/core';
import { ProductService } from 'src/app/service/product.service';
import { Observable } from 'rxjs';
import { take, tap, switchMap } from 'rxjs/operators';
import { Product } from 'src/app/model/product';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { OrderService } from 'src/app/service/order.service';

@Component({
  selector: 'app-order-tab',
  template: `
    <div class="order-tab" style="padding: 5px;">
      <div style="text-align: center;">
        <h1>Order Card</h1>
        <p>Manage your basket and place your order</p>
      </div>

      <table class="table table-striped">
            <tr>
                <th>Product</th>
                <th>Quantity</th>
                <th>Actions</th>
                <th>Price</th>
            </tr>
            <tr app-basket-entry *ngFor="let entry of (basket$ | async)?.entries"
            [entry]="entry"
            (decremented)="onEntryDecremented(entry)"
            (incremented)="onEntryIncremented(entry)">
            </tr>
            <tr>
              <td></td>
              <td></td>
              <td>Total: </td>
              <td>{{(basket$ | async)?.totalMinor / 100}} zł</td>
            </tr>
        </table>

      <div style="text-align: center;">
        <h3> Client details </h3>
      </div>

      <form [formGroup]="clientForm">
        <div class="form-group" style="margin: 3px;">
          <div class="row">
            <div class="col-6">
              <label>First name</label>
            </div>
            <div class="col-6">
              <input type="text" formControlName="firstName" class="form-control"
              [ngClass]="{ 'is-invalid': submitted && controls.firstName.errors }" />
              <div *ngIf="submitted && controls.firstName.errors" class="invalid-feedback">
                  <div *ngIf="controls.firstName.errors.required">First name is required</div>
              </div>
            </div>
          </div>
        </div>

        <div class="form-group" style="margin: 3px;">
          <div class="row">
            <div class="col-6">
              <label>Family name</label>
            </div>
            <div class="col-6">
              <input type="text" formControlName="familyName" class="form-control"
              [ngClass]="{ 'is-invalid': submitted && controls.familyName.errors }" />
              <div *ngIf="submitted && controls.familyName.errors" class="invalid-feedback">
                  <div *ngIf="controls.familyName.errors.required">Family name is required</div>
              </div>
            </div>
          </div>
        </div>
        
        <div class="form-group" style="margin: 3px;">
          <div class="row">
            <div class="col-6">
              <label>Shipping address</label>
            </div>
            <div class="col-6">
              <input type="text" formControlName="address" class="form-control" autocomplete="shipping street-address"
              [ngClass]="{ 'is-invalid': submitted && controls.address.errors }" />
              <div *ngIf="submitted && controls.address.errors" class="invalid-feedback">
                  <div *ngIf="controls.address.errors.required">Address is required</div>
              </div>
            </div>
          </div>
        </div>
        
        <div class="form-group" style="margin: 3px;">
          <div class="row">
            <div class="col-6">
              <label>Email address</label>
            </div>
            <div class="col-6">
              <input type="text" formControlName="email" class="form-control" autocomplete="email"
              [ngClass]="{ 'is-invalid': submitted && controls.email.errors }" />
              <div *ngIf="submitted && controls.email.errors" class="invalid-feedback">
                  <div *ngIf="controls.email.errors.required">Email is required</div>
                  <div *ngIf="controls.email.errors.email">Email is invalid</div>
              </div>
            </div>
          </div>
        </div>

      </form>

      <button (click)="placeOrderClicked()" class="btn btn-primary btn-block" style="margin-top: 20px;"
      [ngClass]="{ 'disabled': (basket$ | async)?.entries.length === 0 }">Place order</button>

      
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

  constructor(
    private basketService: BasketService,
    private orderService: OrderService,
    private alertService: AlertService,
    private productService: ProductService) { }

  clientForm = new FormGroup({
    firstName: new FormControl('', [Validators.required]),
    familyName: new FormControl('', [Validators.required]),
    address: new FormControl('', [Validators.required]),
    email: new FormControl('', [Validators.required, Validators.email]),
  });

  get controls() { return this.clientForm.controls; }

  basket$: Observable<Basket>;
  submitted: boolean;

  ngOnInit() {
    this.basket$ = this.basketService.getBasket();
  }

  onEntryDecremented(entry: BasketEntry) {
    this.basketService.decrementQuantity(entry);
  }

  onEntryIncremented(entry: BasketEntry) {
    this.basketService.incrementQuantity(entry);
  }

  placeOrderClicked() {
    this.submitted = true;

    if (this.clientForm.invalid) {
      return;
    }

    this.basket$.pipe(
      take(1),
      switchMap(basket => this.orderService.createOrder(basket, this.clientForm))
    ).subscribe(next => {
      this.productService.refresh();
      // this.basketService.clear();

      this.alertService.change({
        type: 'success',
        title: 'Success',
        message: 'Order was created succesfully. Please check your e-mail for further details.'
      });
    }, error => {
      this.alertService.change({ type: 'danger', title: error.error.error, message: error.error.message });
    });
  }
}
