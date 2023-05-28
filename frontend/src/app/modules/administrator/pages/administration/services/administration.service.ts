import { HttpHeaders, HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable } from "rxjs";
import { User } from "../interfaces/administration.interface";

@Injectable({
    providedIn: 'root'
  })
  export class AdministrationService {
  
    route: string = 'http://localhost:8084/';
    headers: HttpHeaders = new HttpHeaders({ 'Content-Type': 'application/json' });
    constructor(private http: HttpClient) { }
  
    getAllUsers() : Observable<User[]> {
        return this.http.get<User[]>(this.route + 'users/all', {headers: this.headers});
    }
    
  }