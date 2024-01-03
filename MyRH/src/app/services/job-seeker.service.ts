import { Injectable, Inject } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { JobOffer } from '../models/JobOffer';
import { JobSeeker } from '../models/JobSeeker';

@Injectable({
  providedIn: 'root'
})
export class JobSeekerService {

  constructor(private http: HttpClient) { }

  private url = "http://localhost:8080/api/seekers";

  createJobSeeker(jobSeeker: JobSeeker): Observable<JobSeeker> {
    return this.http.post<JobSeeker>(this.url, jobSeeker);
  }
}
