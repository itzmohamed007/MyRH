import { Injectable, Inject } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { JobOffer } from '../models/JobOffer';

@Injectable({
  providedIn: 'root'
})
export class JobOfferService {

  constructor(private http: HttpClient) { }

  private url = "http://localhost:8080/api/offers";

  getJobOffers(): Observable<Array<JobOffer>> {
    return this.http.get<Array<JobOffer>>(this.url + "/all");
  }
}
