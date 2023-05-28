import { HttpErrorResponse, HttpEvent, HttpHandler, HttpInterceptor, HttpRequest } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { BehaviorSubject, catchError, exhaustMap, filter, Observable, switchMap, take, throwError } from "rxjs";
import { environment } from "src/environments/environment";
import { UserDataService } from "./log-user-data.service";
import { AuthService } from "./log-auth.service";
import { Router } from "@angular/router";

@Injectable()
export class AuthInterceptor implements HttpInterceptor {
  private isRefreshing = false;
  private refreshTokenSubject: BehaviorSubject<any> = new BehaviorSubject<any>(null);

  
  constructor(private m_UserDataService: UserDataService, private m_AuthService: AuthService, 
    private m_Router: Router){}
  
  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    return this.m_UserDataService.m_Token$.pipe(
      take(1),
      exhaustMap(token => {
        let authReq = req;
        if (
          token != null &&
          req.url.indexOf(environment.hospitalApiUrl) !== -1 &&
          req.url.indexOf('login') === -1
        ) {
          authReq = this.addTokenHeader(req, token);
        }
        return next.handle(authReq).pipe(
          catchError(error => {
            if (
              error instanceof HttpErrorResponse &&
              !authReq.url.includes('auth/register') &&
              error.status === 401
            ) {
              return this.handle401Error(authReq, next);
            }

            return throwError(error);
          })
        );
      })
    );
  }
  private handle401Error(request: HttpRequest<any>, next: HttpHandler) {
    if (!this.isRefreshing) {
      this.isRefreshing = true;
      this.refreshTokenSubject.next(null);

      const token = this.m_UserDataService.getToken();

      if (token)
        return this.m_AuthService.refreshToken(token).pipe(
          switchMap((token: any) => {
            this.isRefreshing = false;

            this.m_UserDataService.setToken = token;
            
            return next.handle(this.addTokenHeader(request, token));
          }),
          catchError((err) => {
            this.isRefreshing = false;
            
            this.m_AuthService.logout();
            this.m_Router.navigate(['/login']);
            return throwError(err);
          })
        );
    }

    return this.refreshTokenSubject.pipe(
      filter(token => token !== null),
      take(1),
      switchMap((token) => next.handle(this.addTokenHeader(request, token)))
    );
  }

  private addTokenHeader(request: HttpRequest<any>, token: string) {
    return request.clone({
      setHeaders: { Authorization: `Bearer ${token}` }
    })
  }
}