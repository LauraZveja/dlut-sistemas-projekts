import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { tap } from 'rxjs/operators';
import { UserService } from '../user/user.service';

@Injectable({
  providedIn: 'root',
})
export class AuthService {
  private baseURL = 'http://localhost:8080/api/auth';

  constructor(private httpClient: HttpClient, private userService: UserService) {}

  login(username: string, password: string): Observable<string> {
    const headers = new HttpHeaders({
      'Content-Type': 'application/json',
    });

    const body = { username, password };

    return this.httpClient.post<string>(`${this.baseURL}/login`, body, { headers, responseType: 'text' as 'json', withCredentials: true });
  }

  logout(): Observable<void> {
    return this.httpClient.post<void>(`${this.baseURL}/logout`, {}, { withCredentials: true }).pipe(
      tap(() => {
        this.userService.clearUserToken();
      })
    );
  }
}
