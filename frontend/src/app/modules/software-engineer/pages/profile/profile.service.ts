import { HttpHeaders, HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable, tap } from "rxjs";
import { UserDataService } from "src/app/modules/pages/login/log-user-data.service";
import { User } from "./profile.interface";

@Injectable({
    providedIn: 'root'
  })
  export class ProfileService {
  
    route: string = 'http://localhost:8084/';
    headers: HttpHeaders = new HttpHeaders({ 'Content-Type': 'application/json' });
    public id: number = 0;

    constructor(private http: HttpClient, private userDataService : UserDataService) { 
        this.userDataService.m_UserData$.pipe(tap(user_data => {
            if(user_data != null)this.id = user_data.id;
          })).subscribe();
    }
  
    submit(user: any): Observable<any> {
        return this.http.put<any>(this.route + 'softwareEngineer/profileUpdate', user, {headers: this.headers});
    }

    getById() : Observable<any> {
        console.log(this.id);
          return this.http.get<any>(this.route + 'api/users/' + this.id, {headers: this.headers});
      }

  }