import { HttpClient, HttpHeaders} from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable } from "rxjs";

export class PasswordDTO{
  password : string = ''
  confirmPassword : string = ''
}
@Injectable({
  providedIn: 'root'
})
export class AdminService {

  route: string = 'https://localhost:8084/';
  headers: HttpHeaders = new HttpHeaders({ 'Content-Type': 'application/json' });
  constructor(private http: HttpClient) { }

  getIfPasswordChanged() : Observable<boolean> {
      return this.http.get<boolean>(this.route + 'admin/changedPassword', {headers: this.headers});
  }

  changePassword(password : PasswordDTO) : Observable<boolean> {
    return this.http.post<boolean>(this.route + 'admin/changePassword', password, {headers: this.headers});
  }
}
