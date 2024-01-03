import { Injectable, Inject } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { JobSeeker } from '../models/JobSeeker';
import { JobOffer } from '../models/JobOffer';
import { Applying } from '../models/Applying';

@Injectable({
  providedIn: 'root'
})
export class ApplyingServiceService {

  constructor(private http: HttpClient) { }

  private url = "http://localhost:8080/api/applying";

  getJobOffers(): Observable<Array<JobOffer>> {
    return this.http.get<Array<JobOffer>>(this.url + "/all");
  }

  apply(applying: Applying): Observable<Applying> {
    return this.http.post<Applying>(this.url, applying);
  }
  
}
