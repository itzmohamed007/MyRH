import { TestBed } from '@angular/core/testing';

import { ApplyingServiceService } from './applying-service.service';

describe('ApplyingServiceService', () => {
  let service: ApplyingServiceService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ApplyingServiceService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
