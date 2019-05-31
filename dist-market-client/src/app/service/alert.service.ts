import { Injectable } from '@angular/core';
import { BehaviorSubject, Observable, ReplaySubject } from 'rxjs';
import { Alert } from '../model/alert';

@Injectable({
  providedIn: 'root'
})
export class AlertService {

  constructor() { }

  alerts$ = new ReplaySubject<Alert>(1);


  change(alert: Alert) {
    this.alerts$.next(alert);
  }

  get(): Observable<Alert> {
    return this.alerts$;
  }

  dismiss() {
    this.alerts$.next(null);
  }

}
