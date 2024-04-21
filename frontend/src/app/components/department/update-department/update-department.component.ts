import { Component, OnInit } from '@angular/core';
import { Location } from '@angular/common';
import { Department } from '../../../models/department.model';
import { DepartmentService } from '../../../services/department/department.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-update-department',
  templateUrl: './update-department.component.html',
  styleUrl: './update-department.component.css'
})
export class UpdateDepartmentComponent implements OnInit{
  department: Department | undefined;
  originalDepartment: Department | undefined;

  constructor(
    private departmentService: DepartmentService,
    private router: Router,
    private location: Location
  ) {
    const navigation = this.router.getCurrentNavigation();
    if (navigation?.extras.state) {
      this.department = navigation.extras.state['department'] as Department;
      if (this.department) {
        this.originalDepartment = {...this.department};
      }
    }
  }

  ngOnInit(): void {
    if (!this.department) {
      console.error('Netika saņemti dati par nodaļu.');
      this.goBack();
    }
  }

  onSubmit(): void {
    if (this.department && this.hasChanges()) {
      this.departmentService.updateDepartment(this.department).subscribe({
        next: () => {
          this.router.navigate(['/departments']);
        },
        error: (error) => {
          console.error('Dati netika atjaunoti:', error.message);
          alert('Dati netika atjaunoti, lūdzu, mēģiniet vēlreiz.');
        }
      });
    } else {
      alert('Izmaiņas netika veiktas.');
    }
  }

  goBack(): void {
    this.location.back();
  }

  hasChanges(): boolean {
    return JSON.stringify(this.originalDepartment) !== JSON.stringify(this.department);
  }

}
