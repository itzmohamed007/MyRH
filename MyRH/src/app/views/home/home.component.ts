import { Component, OnInit } from '@angular/core';
import { JobOffer } from 'src/app/models/JobOffer';
import { JobOfferService } from 'src/app/services/job-offer.service';
import { DomSanitizer, SafeUrl } from '@angular/platform-browser';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {
  jobOffers: JobOffer[] = [];

  ngOnInit() {
    this.loadJobOffers();
  }

  constructor(private jobOfferService: JobOfferService, private sanitizer: DomSanitizer) {}

  loadJobOffers() {
    this.jobOfferService.getJobOffers().subscribe({
      next: jobOffers => {
        this.jobOffers = jobOffers;
        jobOffers.forEach((jobOffer) => console.log(atob(jobOffer.recruiter.image.content)));
        console.log(jobOffers);
      },
      error: err => console.log(err)
    });
  }  

  sanitizeImage(content: string): SafeUrl {
    const decodedImage = atob(content);
    const blob = new Blob([decodedImage], { type: 'image/*' });
    const url = URL.createObjectURL(blob);
    return this.sanitizer.bypassSecurityTrustUrl(url);
  }
}
