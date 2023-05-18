import { Injectable } from "@angular/core";
import { ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot, UrlTree } from "@angular/router";
import { Observable, map, take, switchMap, filter } from "rxjs";
import { UserDataService } from "./log-user-data.service";

@Injectable({
  providedIn: 'root'
})
export class AntiAuthGuard implements CanActivate {
  constructor(private m_UserDataService: UserDataService, private m_Router: Router) { }

  canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): boolean | UrlTree | Observable<boolean | UrlTree> | Promise<boolean | UrlTree> {
    const token = this.m_UserDataService.getToken();
    return this.m_UserDataService.m_UserData$.pipe(filter(user_data => user_data !== null),map(user_data => {
        return !token ? true : this.m_Router.createUrlTree([this.getRole(user_data?.role)]);
    }));
  }
  getRole(role: number | undefined) : string{
    if(role==1)return '/software-engineer';
    if(role==2)return '/human-resources';
    if(role==3)return '/project-manager';
    if(role==4)return '/administrator';
    
    return '';
  }
}