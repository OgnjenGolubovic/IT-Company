import { HttpHeaders, HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable, tap } from "rxjs";
import { Project, User } from "../interfaces/administration.interface";
import { MatSnackBar } from "@angular/material/snack-bar";

@Injectable({
    providedIn: 'root'
  })
  export class AdministrationService {
  
    route: string = 'http://localhost:8084/';
    headers: HttpHeaders = new HttpHeaders({ 'Content-Type': 'application/json' });
    constructor(
        private http: HttpClient) { }
  
    getAllUsers() : Observable<User[]> {
        return this.http.get<User[]>(this.route + 'users/all', {headers: this.headers});
    }

    getAllProjects() : Observable<Project[]>{
        return this.http.get<Project[]>(this.route + 'projects/all', {headers: this.headers});
    }

    
    
  }