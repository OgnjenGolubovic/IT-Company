import { HttpHeaders, HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable } from "rxjs";
import { User } from "src/app/modules/pages/login/log-user-data.service";

export interface RegistrationRequest{
    name: string,
    surname: string,
    email: string,
    companyRole: string,
}

@Injectable({
    providedIn: 'root'
  })
  export class RegisteredUsersListService {
  
    route: string = 'http://localhost:8084/';
    headers: HttpHeaders = new HttpHeaders({ 'Content-Type': 'application/json' });
    constructor(private http: HttpClient) { }
  
    getAllRequests() : Observable<RegistrationRequest[]> {
        return this.http.get<RegistrationRequest[]>(this.route + 'admin/regRequestAll', {headers: this.headers});
    }
    
    approveRequest(request: RegistrationRequest) : Observable<any> {
      return this.http.put<any>(this.route + 'admin/regRequestAprove', request, {headers: this.headers});
    }

    cancelRequest(request: RegistrationRequest) : Observable<any> {
      return this.http.put<any>(this.route + 'admin/regRequestCancel', request, {headers: this.headers});
    }

  }


