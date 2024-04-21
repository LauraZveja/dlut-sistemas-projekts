import { Component, OnInit } from '@angular/core';
import { Department } from '../../../models/department.model';
import { DepartmentService } from '../../../services/department/department.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-view-all-departments',
  templateUrl: './view-all-departments.component.html',
  styleUrl: './view-all-departments.component.css'
})
export class ViewAllDepartmentsComponent implements OnInit{
  departments: Department[] = [];

  constructor(private departmentService: DepartmentService, private router: Router) { }

  ngOnInit(): void {
    this.getDepartments();
  }

  private getDepartments() {
    this.departmentService.getDepartmentsList().subscribe(data => {
      this.departments = data;
    });
  }

  navigateToUpdate(department: Department): void {
    this.router.navigate(['/update-department'], { state: { department } });
  }

  navigateToCreate(): void {
    this.router.navigate(['/create-department']);
  }

  deleteDepartment(department: Department): void {
    this.departmentService.deleteDepartment(department).subscribe({
      next: () => {
        this.getDepartments();
      },
      error: (err) => {
        console.error(err);
      }
    });
      
  }

  confirmDelete(department: Department): void {
    if (confirm('Vai tiešām vēlaties dzēst šo nodaļu?')) {
      this.deleteDepartment(department);
    }
  }

}
