import { Component, OnInit } from '@angular/core';
import { Department } from '../../../models/department.model';
import { Router } from '@angular/router';
import { DepartmentService } from '../../../services/department/department.service';
import { Location } from '@angular/common';

@Component({
  selector: 'app-create-department',
  templateUrl: './create-department.component.html',
  styleUrl: './create-department.component.css'
})
export class CreateDepartmentComponent implements OnInit{
  department: Department = new Department();

  constructor(
    private departmentService: DepartmentService,
    private router: Router,
    private location: Location
  ) {}

  ngOnInit(): void {}

  onSubmit(): void {
    if (this.department) {
      this.departmentService.createDepartment(this.department).subscribe({
        next: () => {
          this.router.navigate(['/departments']);
        },
        error: (error) => {
          console.error('Neizdevās izveidot.', error);
          alert('Neizdevās izveidot, lūdzu, mēģiniet vēlreiz.');
        },
      });
    }
  }
  goBack(): void {
    this.location.back();
  }

}
