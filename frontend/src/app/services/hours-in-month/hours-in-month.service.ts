import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { HoursInMonth } from '../../models/hoursInMonth.model';

@Injectable({
  providedIn: 'root',
})
export class HoursInMonthService {
  private baseURL = 'http://localhost:8080/dlut/table';

  constructor(private httpClient: HttpClient) {}

  getAllHoursInYear(year: number): Observable<HoursInMonth[]> {
    return this.httpClient.get<HoursInMonth[]>(`${this.baseURL}/showHoursInYear/${year}`);
  }

  getAllHoursInThisYear(): Observable<HoursInMonth[]> {
    const currentYear = new Date().getFullYear();
    return this.httpClient.get<HoursInMonth[]>(`${this.baseURL}/showHoursInYear/${currentYear}`);
  }

  insertHoursInMonth(hoursInMonthDTO: HoursInMonth): Observable<Object> {
    return this.httpClient.post(`${this.baseURL}/insertHoursInMonth`, hoursInMonthDTO);
  }

  updateHoursInMonth(hoursInMonthDTO: HoursInMonth): Observable<Object> {
    return this.httpClient.put(`${this.baseURL}/updateHoursInMonth`, hoursInMonthDTO);
  }

  deleteHoursInMonth(hoursInMonthDTO: HoursInMonth): Observable<Object> {
    const options = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json',
      }),
      body: hoursInMonthDTO,
    };
    return this.httpClient.delete(`${this.baseURL}/delete`, options);
  }

  getHoursInMonth(year: number, month: number): Observable<HoursInMonth> {
    return this.httpClient.get<HoursInMonth>(`${this.baseURL}/showHoursInMonth/${year}/${month}`);
  }
}
