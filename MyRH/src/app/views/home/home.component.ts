import { Component, OnInit } from '@angular/core';
import { JobOffer } from 'src/app/models/JobOffer';
import { JobOfferService } from 'src/app/services/job-offer.service';
import { DomSanitizer, SafeUrl } from '@angular/platform-browser';
import { DatePipe } from '@angular/common';
import { FileService } from 'src/app/services/file.service';

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

  constructor(private jobOfferService: JobOfferService, private sanitizer: DomSanitizer, private datePipe: DatePipe, private fileService: FileService) {}

  loadJobOffers() {
    this.jobOfferService.getJobOffers().subscribe({
      next: jobOffers => {
        jobOffers.forEach((jobOffer) => {
          jobOffer.recruiter.image.path = this.getImage(jobOffer.recruiter.image.uuid)
        })
        this.jobOffers = jobOffers;
      },
      error: err => console.log(err)
    });
  }  

  getImage(uuid: string): string {
    let path: string = "";
    this.fileService.getFile(uuid).subscribe({
      next: image => {
        console.log(image);
        path =  image.path
      },
      error: err => console.log(err)
    })
    return path;
  }

  getCurrentDate() {
    return this.datePipe.transform(new Date(), 'yyyy-MM-dd');
  }
}
