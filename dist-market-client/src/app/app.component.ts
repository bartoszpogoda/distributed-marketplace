import { AlertService } from './service/alert.service';
import { Component, OnInit } from '@angular/core';
import { Observable } from 'rxjs';
import { Alert } from './model/alert';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {

  title = 'dist-market-client';
  alert$: Observable<Alert>;

  constructor(private alertService: AlertService) { }

  ngOnInit(): void {
    this.alert$ = this.alertService.get();
  }

  dismissAlert() {
    this.alertService.dismiss();
  }

}
