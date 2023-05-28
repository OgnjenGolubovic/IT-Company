import { HttpClient, HttpHeaders } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable, tap } from "rxjs";
import { UserDataService } from "src/app/modules/pages/login/log-user-data.service";

@Injectable({
    providedIn: 'root'
  })
  export class ProjectsService {
       
    route: string = 'https://localhost:8084/';
    headers: HttpHeaders = new HttpHeaders({ 'Content-Type': 'application/json' });
    public id: number = 0;

    constructor(private http: HttpClient, private userDataService : UserDataService) { 
        this.userDataService.m_UserData$.pipe(tap(user_data => {
            if(user_data != null)this.id = user_data.id;
          })).subscribe();
    }

    getProjects() : Observable<any> {
        return this.http.get<any>(this.route + 'softwareEngineer/projects/' + this.id, {headers: this.headers});        
      }

    submit(project: any) : Observable<any> {
        return this.http.put<any>(this.route + 'softwareEngineer/projectUpdate', project, {headers: this.headers});
    }



  }