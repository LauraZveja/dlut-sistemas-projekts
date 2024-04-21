import { Injectable } from '@angular/core';
import { Department } from '../../models/department.model';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class DepartmentService {

  private baseURL = 'http://localhost:8080/dlut/department';

  constructor(private httpClient: HttpClient) {}

  getDepartmentsList(): Observable<Department[]> {
    return this.httpClient.get<Department[]>(`${this.baseURL}/showAll`);
  }

  createDepartment(department: Department): Observable<Object> {
    return this.httpClient.post(`${this.baseURL}/addNew`, department);
  }

  updateDepartment(department: Department): Observable<Object> {
    return this.httpClient.put(`${this.baseURL}/update`, department);
  }

  deleteDepartment(department: Department): Observable<Object> {
    const options = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json',
      }),
      body: department,
    };
    return this.httpClient.delete(`${this.baseURL}/delete`, options);
  }
}
