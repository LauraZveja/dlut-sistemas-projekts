import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class UserService {
  private userToken: any = null;

  constructor() { }

  setUserToken(token: any) {
    this.userToken = token;
  }

  getUserToken() {
    return this.userToken;
  }

  clearUserToken() {
    this.userToken = null;
  }
}