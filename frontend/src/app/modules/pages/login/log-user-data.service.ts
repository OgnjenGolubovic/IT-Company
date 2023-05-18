import { Injectable } from "@angular/core";
import { BehaviorSubject } from "rxjs";
import { CookieService } from 'ngx-cookie-service';

export interface User {
  id: number;
  role: number;
}

@Injectable({
  providedIn: 'root'
})
export class UserDataService{
  private m_TokenSubject: BehaviorSubject<null | string> = new BehaviorSubject<null | string>(null);
  private m_UserDataSubject: BehaviorSubject<null | User> = new BehaviorSubject<null | User>(null);

  public m_Token$ = this.m_TokenSubject.asObservable();
  public m_UserData$ = this.m_UserDataSubject.asObservable();

  constructor(private cookieService: CookieService){

  }
  set setToken(token: null | string){
    if(token){
      this.m_TokenSubject.next(token);
    }else{
      this.m_TokenSubject.next(null);
    }
  }
  setRefreshToken(token: null | string, expires: number){
    if(token){
      this.cookieService.set('refreshToken', token, expires, '/', 'localhost', true, 'Strict');
    }else{
      this.cookieService.delete('refreshToken', '/', 'localhost', true);
    }
  }
  getToken(): string{
    return this.cookieService.get('refreshToken');
  }
  set setUserData(userData: null | User){
    this.m_UserDataSubject.next(userData);
  }
}