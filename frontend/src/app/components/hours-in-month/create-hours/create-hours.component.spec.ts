import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CreateHoursComponent } from './create-hours.component';

describe('CreateHoursComponent', () => {
  let component: CreateHoursComponent;
  let fixture: ComponentFixture<CreateHoursComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [CreateHoursComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(CreateHoursComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
