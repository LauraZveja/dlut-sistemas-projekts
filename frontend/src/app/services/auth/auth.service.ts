import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class AuthService {
  private baseURL = 'http://localhost:8080/api/auth';

  constructor(private httpClient: HttpClient) {}

  login(username: string, password: string): Observable<string> {
    const headers = new HttpHeaders({
      'Content-Type': 'application/json',
    });

    const body = { username, password };

    return this.httpClient.post<string>(`${this.baseURL}/login`, body, { headers, responseType: 'text' as 'json', withCredentials: true });
  }

  logout(): Observable<void> {
    return this.httpClient.post<void>(`${this.baseURL}/login`, {}, { withCredentials: true });
  }
}
