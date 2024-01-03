import { Component, assertPlatform } from '@angular/core';
import { JobSeeker } from 'src/app/models/JobSeeker';
import { ApplyingServiceService } from 'src/app/services/applying-service.service';
import { JobSeekerService } from 'src/app/services/job-seeker.service';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { JobOffer } from 'src/app/models/JobOffer';
import { ActivatedRoute } from '@angular/router';
import { JsonPipe } from '@angular/common';
import { Applying } from 'src/app/models/Applying';
import { FileService } from 'src/app/services/file.service';

@Component({
  selector: 'app-applying',
  templateUrl: './applying.component.html',
  styleUrls: ['./applying.component.css']
})
export class ApplyingComponent {
  jobSeekerForm: FormGroup;
  jobOfferUUid: string = "";
  resumeUUid: string = "";

  constructor(
    private applyingService: ApplyingServiceService,
    private jobSeekerService: JobSeekerService,
    private fileService: FileService,
    private formBuilder: FormBuilder,
    private router: Router,
    private activatedRoute: ActivatedRoute
  ) {
    this.jobSeekerForm = this.formBuilder.group({
      email: ['', [Validators.required, Validators.minLength(10), Validators.maxLength(50)]],
      fullName: ['', [Validators.required, Validators.minLength(10), Validators.maxLength(10)]],
      phone: ['', [Validators.required]],
      resume: ['', [Validators.required]],
      letter: ['', [Validators.required]]
    });    
    this.activatedRoute.paramMap.subscribe(params => {
      this.jobOfferUUid = params.get("uuid") || ""
    })
  }

  createJobSeeker() {
    const jobSeekerData = this.jobSeekerForm.value;
    jobSeekerData.resume = this.resumeUUid;
    this.jobSeekerService.createJobSeeker(jobSeekerData).subscribe({
      next: jobSeeker => {
        this.apply(jobSeeker.uuid)
      },
      error: err => {
        console.log(err.error)
        alert(JSON.stringify(err.error)) 
      }
    });
  }

  apply(jobSeeker: string) {
    alert("job offer uuid " + this.jobOfferUUid)
    const applying: Applying = {
      jobOffer: this.jobOfferUUid,
      jobSeeker: jobSeeker,
      letter: this.jobSeekerForm.value.letter
    }
    this.applyingService.apply(applying).subscribe({
      next: data => {
        alert("Applyed successfully")
        this.router.navigate(["/home"])
      },
      error: err => alert(JSON.stringify(err.error))
    })
  }

  uploadResume() {
    const formDate = new FormData;
    formDate.append("file", this.jobSeekerForm.get("resume")?.value)
    this.fileService.uploadFile(formDate).subscribe({
      next: resume => {
        console.log("resume: " + JSON.stringify(resume))
        alert("file uploaded successfully? UUID " + resume.fileUuid)
        this.resumeUUid = resume.fileUuid;
        this.createJobSeeker()
      },
      error: err => {
        console.log(err)
        alert(JSON.stringify(err.error))
      }
    })
  }

  onFileSelected(event: any) {
    const file = event.target.files[0]
    if (file) {
      console.log(file)
      this.jobSeekerForm.patchValue({
        resume: file
      });
    }
  }
}
