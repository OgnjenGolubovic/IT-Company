import { Injectable } from "@angular/core";
import { ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot, UrlTree } from "@angular/router";
import { Observable, map, take, switchMap } from "rxjs";
import { UserDataService } from "./log-user-data.service";

@Injectable({
  providedIn: 'root'
})
export class CodeGuard implements CanActivate {
  constructor(private m_UserDataService: UserDataService, private m_Router: Router) { }

  canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): boolean | UrlTree | Observable<boolean | UrlTree> | Promise<boolean | UrlTree> {
    const token = this.m_UserDataService.getToken();
    return this.m_UserDataService.m_Username$.pipe(map(username => {
      return !token ? !!username : this.m_Router.createUrlTree(['/login']);
    }));
  }
}