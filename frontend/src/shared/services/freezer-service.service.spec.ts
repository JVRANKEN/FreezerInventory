import { TestBed } from '@angular/core/testing';

import { FreezerServiceService } from './freezer-service.service';

describe('FreezerServiceService', () => {
  let service: FreezerServiceService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(FreezerServiceService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
