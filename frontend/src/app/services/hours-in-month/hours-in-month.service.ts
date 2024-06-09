import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { HoursInMonth } from '../../models/hoursInMonth.model';
import { UserService } from '../../services/user/user.service';

@Injectable({
  providedIn: 'root',
})
export class HoursInMonthService {
  private baseURL = 'http://localhost:8080/dlut/table';

  constructor(private httpClient: HttpClient, private userService: UserService) {}

  getHeader(){
    return new HttpHeaders({
      'token': this.userService.getUserToken(),
    });
  }

  getAllHoursInYear(year: number): Observable<HoursInMonth[]> {
    const headers = this.getHeader();
    return this.httpClient.get<HoursInMonth[]>(`${this.baseURL}/showHoursInYear/${year}`, { headers});
  }

  getAllHoursInThisYear(): Observable<HoursInMonth[]> {
    const headers = this.getHeader();
    const currentYear = new Date().getFullYear();
    return this.httpClient.get<HoursInMonth[]>(`${this.baseURL}/showHoursInYear/${currentYear}`, { headers});
  }

  insertHoursInMonth(hoursInMonthDTO: HoursInMonth): Observable<Object> {
    const headers = this.getHeader();
    return this.httpClient.post(`${this.baseURL}/insertHoursInMonth`, hoursInMonthDTO, { headers});
  }

  updateHoursInMonth(hoursInMonthDTO: HoursInMonth): Observable<Object> {
    const headers = this.getHeader();
    return this.httpClient.put(`${this.baseURL}/updateHoursInMonth`, hoursInMonthDTO, { headers});
  }

  deleteHoursInMonth(hoursInMonthDTO: HoursInMonth): Observable<Object> {
    const headers = this.getHeader();
    const options = {
      headers: headers,
      body: hoursInMonthDTO,
    };
    return this.httpClient.delete(`${this.baseURL}/delete`, options);
  }

  getHoursInMonth(year: number, month: number): Observable<HoursInMonth> {
    const headers = this.getHeader();
    return this.httpClient.get<HoursInMonth>(`${this.baseURL}/showHoursInMonth/${year}/${month}`, { headers});
  }

  insertHoursInYear(hoursInYearDTO: HoursInMonth[]): Observable<Object> {
    const headers = this.getHeader();
    return this.httpClient.post(`${this.baseURL}/insertHoursInYear`, hoursInYearDTO, { headers});
}


}
