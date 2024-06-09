import { TestBed } from '@angular/core/testing';

import { HoursInMonthService } from './hours-in-month.service';

describe('HoursInMonthService', () => {
  let service: HoursInMonthService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(HoursInMonthService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
