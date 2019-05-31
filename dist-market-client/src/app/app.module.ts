import { ExploreTabComponent } from './containers/explore-tab/explore-tab.component';
import { OrderTabComponent } from './containers/order-tab/order-tab.component';
import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import {NgbModule} from '@ng-bootstrap/ng-bootstrap';
import { HttpClientModule } from '@angular/common/http';
import { ReactiveFormsModule } from '@angular/forms';

import { AppComponent } from './app.component';
import { ProductEntryComponent } from './components/product-entry/product-entry.component';
import { BasketEntryComponent } from './components/basket-entry/basket-entry.component';

@NgModule({
  declarations: [
    AppComponent,
    ExploreTabComponent,
    ProductEntryComponent,
    OrderTabComponent,
    BasketEntryComponent
  ],
  imports: [
    BrowserModule,
    NgbModule,
    HttpClientModule,
    ReactiveFormsModule,
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
