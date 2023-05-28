import { HttpHeaders, HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { MatSnackBar } from "@angular/material/snack-bar";
import { Observable } from "rxjs";
import { Project, SoftwareEngineer } from "../interfaces/assign-workers.interface";

@Injectable({
    providedIn: 'root'
  })
  export class AssignWorkersService {

    apiHost: string = 'http://localhost:8084/';
    headers: HttpHeaders = new HttpHeaders({ 'Content-Type': 'application/json' });
  
    constructor(
        private http: HttpClient,
        private snackbar: MatSnackBar
    ) { }

        getAllSoftwareEngineers() : Observable<SoftwareEngineer[]> {
            return this.http.get<SoftwareEngineer[]>(this.apiHost + 'softwareEngineer/all', {headers: this.headers});
        }

        getAllProjects() : Observable<Project[]>{
            return this.http.get<Project[]>(this.apiHost + 'projects/all', {headers: this.headers});
        }


  }