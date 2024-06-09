import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ViewAllHoursComponent } from './view-all-hours.component';

describe('ViewAllHoursComponent', () => {
  let component: ViewAllHoursComponent;
  let fixture: ComponentFixture<ViewAllHoursComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ViewAllHoursComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(ViewAllHoursComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
