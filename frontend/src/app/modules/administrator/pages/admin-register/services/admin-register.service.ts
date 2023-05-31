import { HttpHeaders, HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { MatSnackBar } from "@angular/material/snack-bar";
import { Observable, tap } from "rxjs";
import { RegisterResponse } from "../interfaces/admin-register.interface";

@Injectable({
    providedIn: 'root'
  })
  export class AdminRegisterService {
  
    apiHost: string = 'https://localhost:8084/';
    headers: HttpHeaders = new HttpHeaders({ 'Content-Type': 'application/json' });
  
  
    constructor(
      private http: HttpClient,
      private snackbar: MatSnackBar
    ) { }
    
    register(registerRequest: any): Observable<RegisterResponse> {
      
      return this.http.post<RegisterResponse>(this.apiHost + 'auth/adminRegistration', registerRequest, {headers: this.headers}).pipe(
        tap((res: RegisterResponse) => this.snackbar.open(`Admin created successfully`, 'Close', {
          duration: 2000, horizontalPosition: 'right', verticalPosition: 'top'
        }))
      )
    }
  
    
  }