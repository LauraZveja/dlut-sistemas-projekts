import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { EmployeeService } from '../../../services/employee/employee.service';
import { Employee } from '../../../models/employee.model';
import { Location } from '@angular/common';

@Component({
  selector: 'app-create-employee',
  templateUrl: './create-employee.component.html',
  styleUrls: ['./create-employee.component.css'],
})
export class CreateEmployeeComponent implements OnInit {
  employee: Employee = new Employee();

  constructor(
    private employeeService: EmployeeService,
    private router: Router,
    private location: Location
  ) {}

  ngOnInit(): void {}

  onSubmit(): void {
    if (this.employee) {
      this.employeeService.createEmployee(this.employee).subscribe({
        next: () => {
          this.router.navigate(['/employees']);
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
