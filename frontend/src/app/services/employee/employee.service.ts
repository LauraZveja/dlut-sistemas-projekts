import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Employee } from '../../models/employee.model';
@Injectable({
  providedIn: 'root',
})
export class EmployeeService {
  private baseURL = 'http://localhost:8080/dlut/employee';

  constructor(private httpClient: HttpClient) {}

  getEmployeesList(): Observable<Employee[]> {
    return this.httpClient.get<Employee[]>(`${this.baseURL}/showAll`);
  }

  createEmployee(employee: Employee): Observable<Object> {
    return this.httpClient.post(`${this.baseURL}/addNew`, employee);
  }

  updateEmployee(employee: Employee): Observable<Object> {
    return this.httpClient.put(`${this.baseURL}/update`, employee);
  }

  deleteEmployee(employee: Employee): Observable<Object> {
    const options = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json',
      }),
      body: employee,
    };
    return this.httpClient.delete(`${this.baseURL}/delete`, options);
  }
}
