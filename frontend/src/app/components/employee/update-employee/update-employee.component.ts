import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Location } from '@angular/common';
import { EmployeeService } from '../../../services/employee/employee.service';
import { Employee } from '../../../models/employee.model';

@Component({
  selector: 'app-update-employee',
  templateUrl: './update-employee.component.html',
  styleUrls: ['./update-employee.component.css']
})
export class UpdateEmployeeComponent implements OnInit {
  employee: Employee | undefined;
  originalEmployee: Employee | undefined;

  constructor(
    private employeeService: EmployeeService,
    private router: Router,
    private location: Location
  ) {
    const navigation = this.router.getCurrentNavigation();
    if (navigation?.extras.state) {
      this.employee = navigation.extras.state['employee'] as Employee;
      if (this.employee) {
        this.originalEmployee = {...this.employee};
      }
    }
  }

  ngOnInit(): void {
    if (!this.employee) {
      console.error('Netika saņemti dati par darbinieku.');
      this.goBack();
    }
  }

  onSubmit(): void {
    if (this.employee && this.hasChanges()) {
      this.employeeService.updateEmployee(this.employee).subscribe({
        next: () => {
          this.router.navigate(['/employees']);
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
    return JSON.stringify(this.originalEmployee) !== JSON.stringify(this.employee);
  }
}
