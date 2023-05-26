import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from "src/environments/environment";
import { Observable } from 'rxjs';

export interface RoleDTO{
  id : number;
  name: string;
  permissions: PermissionDTO[];
}

export interface PermissionDTO{
  id : number;
  name: string;
}

@Injectable({
  providedIn: 'root'
})
export class PermissionService {
  headers: HttpHeaders = new HttpHeaders({ 'Content-Type': 'application/json' });
  constructor(private http: HttpClient) { }

  getAllRoles(): Observable<RoleDTO[]> {
    return this.http.get<RoleDTO[]>(`${environment.hospitalApiUrl}/roles`, {headers: this.headers});
  }

  getAllPermissions(): Observable<PermissionDTO[]> {
    return this.http.get<PermissionDTO[]>(`${environment.hospitalApiUrl}/permissions`, {headers: this.headers});
  }
  updatePermissions(roleDTO : RoleDTO): Observable<any> {
    return this.http.post<RoleDTO>(`${environment.hospitalApiUrl}/roles`, roleDTO, {headers: this.headers});
  }
}
