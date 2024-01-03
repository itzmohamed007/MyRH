import { Component } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-offer',
  templateUrl: './offer.component.html',
  styleUrls: ['./offer.component.css']
})
export class OfferComponent {
  jobOfferUuid: string = "";

  constructor(private router: ActivatedRoute) {
    this.router.paramMap.subscribe(params => {
      this.jobOfferUuid = params.get("uuid") || ""
    })
  }

}
