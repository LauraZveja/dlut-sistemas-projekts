import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Employee } from '../../../models/employee.model';
import { EmployeeService } from '../../../services/employee.service';

@Component({
  selector: 'app-view-all-employees',
  templateUrl: './view-all-employees.component.html',
  styleUrls: ['./view-all-employees.component.css']
})
export class ViewAllEmployeesComponent implements OnInit {
  employees: Employee[] = [];

  constructor(private employeeService: EmployeeService, private router: Router) { }

  ngOnInit(): void {
    this.getEmployees();
  }

  private getEmployees() {
    this.employeeService.getEmployeesList().subscribe(data => {
      this.employees = data;
    });
  }

  navigateToUpdate(employee: Employee): void {
    this.router.navigate(['/update-employee'], { state: { employee } });
  }

  navigateToCreate(): void {
    this.router.navigate(['/create-employee']);
  }

  deleteEmployee(employee: Employee): void {
    this.employeeService.deleteEmployee(employee).subscribe({
      next: () => {
        this.getEmployees();
      },
      error: (err) => {
        console.error(err);
      }
    });
      
  }

  confirmDelete(employee: Employee): void {
    if (confirm('Vai tiešām vēlaties dzēst šo darbinieku?')) {
      this.deleteEmployee(employee);
    }
  }
  
  
  
}
