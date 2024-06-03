import { Injectable } from '@angular/core';
import { Department } from '../../models/department.model';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { UserService } from '../../services/user/user.service';

@Injectable({
  providedIn: 'root'
})
export class DepartmentService {

  private baseURL = 'http://localhost:8080/dlut/department';

  constructor(private httpClient: HttpClient, private userService: UserService) {}

  getHeader(){
    return new HttpHeaders({
      'token': this.userService.getUserToken(),
    });
  }

  getDepartmentsList(): Observable<Department[]> {
    const headers = this.getHeader();
    return this.httpClient.get<Department[]>(`${this.baseURL}/showAll`, { headers});
  }

  createDepartment(department: Department): Observable<Object> {
    const headers = this.getHeader();
    return this.httpClient.post(`${this.baseURL}/addNew`, department, { headers});
  }

  updateDepartment(department: Department): Observable<Object> {
    const headers = this.getHeader();
    return this.httpClient.put(`${this.baseURL}/update`, department, { headers});
  }

  deleteDepartment(department: Department): Observable<Object> {
    const headers = this.getHeader();
    const options = {
      headers: headers,
      body: department,
    };
    return this.httpClient.delete(`${this.baseURL}/delete`, options);
  }
}
