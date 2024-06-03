import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Employee } from '../../models/employee.model';
import { UserService } from '../../services/user/user.service';


@Injectable({
  providedIn: 'root',
})
export class EmployeeService {
  private baseURL = 'http://localhost:8080/dlut/employee';

  constructor(private httpClient: HttpClient, private userService: UserService) {}


  getHeader(){
    return new HttpHeaders({
      'token': this.userService.getUserToken(),
    });
  }

  getEmployeesList(): Observable<Employee[]> {
    const headers = this.getHeader();
    console.log("Headeri:", headers);
    return this.httpClient.get<Employee[]>(`${this.baseURL}/showAll`, { headers});
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
