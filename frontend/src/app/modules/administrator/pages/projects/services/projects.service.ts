import { HttpHeaders, HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable } from "rxjs";
import { User, Project } from "../interfaces/projects.interface";

@Injectable({
    providedIn: 'root'
  })
  export class ProjectsService {
  
    route: string = 'https://localhost:8084/';
    headers: HttpHeaders = new HttpHeaders({ 'Content-Type': 'application/json' });
    constructor(
        private http: HttpClient) { }

    getAllProjects() : Observable<Project[]>{
        return this.http.get<Project[]>(this.route + 'projects/all', {headers: this.headers});
    }

    getAllProjectsUsers(id: number) : Observable<User[]>{
        return this.http.get<User[]>(this.route + 'softwareEngineer/get/' + id, {headers: this.headers});
    }





    
  }