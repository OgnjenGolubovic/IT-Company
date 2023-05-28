import { HttpHeaders, HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { MatSnackBar } from "@angular/material/snack-bar";
import { Observable, tap } from "rxjs";
import { RegisterResponse } from "./create-project.interface";

@Injectable({
    providedIn: 'root'
  })
  export class CreateProjectService {

    route: string = 'https://localhost:8084/';
    headers: HttpHeaders = new HttpHeaders({ 'Content-Type': 'application/json' });
    constructor(
        private http: HttpClient,
        private snackbar: MatSnackBar) { }
    

    createProject(registerRequest: any): Observable<RegisterResponse> {
        
        return this.http.post<RegisterResponse>(this.route + 'projects/create', registerRequest, {headers: this.headers}).pipe(
        tap((res: RegisterResponse) => this.snackbar.open(`Project created successfully`, 'Close', {
            duration: 2000, horizontalPosition: 'right', verticalPosition: 'top'
        }))
        )
    }



  }



