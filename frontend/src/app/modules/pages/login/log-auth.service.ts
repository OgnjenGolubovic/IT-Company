import { HttpClient } from "@angular/common/http"
import { Injectable } from "@angular/core"
import { Observable, tap, switchMap, EMPTY, take, map, catchError, of } from "rxjs"
import { environment } from "src/environments/environment"
import { UserDataService } from "src/app/modules/pages/login/log-user-data.service"

export interface LoginDTO {
  username: string;
  password: string;
}

export interface QrCode {
  username: string;
  securityCode: string;
}

@Injectable({
  providedIn: "root"
})
export class AuthService {
  constructor(private m_UserDataService: UserDataService, private m_Http: HttpClient) {
    this.m_UserDataService.m_Token$.pipe(
      take(1),
      switchMap(token => {
        if (token) return this.getUserData();
        return EMPTY;
      })
    ).subscribe();
  }

  login(loginDTO: LoginDTO): Observable<any> {
    return this.m_Http.post(`${environment.hospitalApiUrl}/auth/login`, loginDTO).pipe(
      map((res: any) => {
        if(res['accessToken']){
          this.m_UserDataService.setToken = res['accessToken'];
          this.m_UserDataService.setRefreshToken(res['refreshToken'], res['expiresInRefresh']);
          return true;
        }else{
          return false;
        }
      }),
      switchMap((shouldContinue: boolean) => {
        if (shouldContinue) {
          return this.getUserData();
        } else {
          return of(null);
        }
      })
    );
  }
  qrCode(): Observable<any> {
    return this.m_Http.get(`${environment.hospitalApiUrl}/users/qrcode`);
  }
  set2FA(): Observable<any> {
    return this.m_Http.get(`${environment.hospitalApiUrl}/users/set2FA`);
  }
  sendCode(code: QrCode): Observable<any> {
    return this.m_Http.post(`${environment.hospitalApiUrl}/auth/qrcode`, code).pipe(      
      tap((res: any) => {
        if(res['accessToken'])this.m_UserDataService.setToken = res['accessToken'];
        if(res['refreshToken'])this.m_UserDataService.setRefreshToken(res['refreshToken'], res['expiresInRefresh'])
      }),switchMap(_ => this.getUserData()));
  }
  refreshToken(token: string) {
    return this.m_Http.post(`${environment.hospitalApiUrl}/auth/refresh`, token).pipe(
      tap((res: any) => {
        this.m_UserDataService.setToken = res['accessToken'];
      }),switchMap(_ => this.getUserData())
    );
  }
  refreshTokenInterceptor(token: string) {
    return this.m_Http.post(`${environment.hospitalApiUrl}/auth/refresh`, token).pipe(
      tap((res: any) => {
        this.m_UserDataService.setToken = res['accessToken'];
      })
    );
  }
  logout(): void {
    this.m_UserDataService.setToken = null;
    this.m_UserDataService.setUserData = null;
    this.m_UserDataService.setRefreshToken(null, 0);
  }

  getUserData(): Observable<any> {
    if(this.m_UserDataService.getToken() != ""){
      return this.m_Http.get(`${environment.hospitalApiUrl}/users/data`).pipe(
        tap((res: any) => {
          this.m_UserDataService.setUserData = res;
        })
      )
    }
    return new Observable<any>;
  }
}
