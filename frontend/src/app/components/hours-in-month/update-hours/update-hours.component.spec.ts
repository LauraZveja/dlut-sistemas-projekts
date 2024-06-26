import { ComponentFixture, TestBed } from '@angular/core/testing';

import { UpdateHoursComponent } from './update-hours.component';

describe('UpdateHoursComponent', () => {
  let component: UpdateHoursComponent;
  let fixture: ComponentFixture<UpdateHoursComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [UpdateHoursComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(UpdateHoursComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
