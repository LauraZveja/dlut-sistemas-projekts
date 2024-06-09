import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EnterAllHoursComponent } from './enter-all-hours.component';

describe('EnterAllHoursComponent', () => {
  let component: EnterAllHoursComponent;
  let fixture: ComponentFixture<EnterAllHoursComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [EnterAllHoursComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(EnterAllHoursComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
