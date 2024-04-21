import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ViewAllDepartmentsComponent } from './view-all-departments.component';

describe('ViewAllDepartmentsComponent', () => {
  let component: ViewAllDepartmentsComponent;
  let fixture: ComponentFixture<ViewAllDepartmentsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ViewAllDepartmentsComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(ViewAllDepartmentsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
