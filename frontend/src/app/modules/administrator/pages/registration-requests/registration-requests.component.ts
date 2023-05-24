import { Component, OnInit } from '@angular/core';
import { MatTableDataSource } from '@angular/material/table';
import { RegisteredUsersListService, RegistrationRequest } from './registration-requests.service';

@Component({
  selector: 'app-registration-requests',
  templateUrl: './registration-requests.component.html',
  styleUrls: ['./registration-requests.component.scss']
})
export class RegistrationRequestsComponent implements OnInit {

  public requests: RegistrationRequest[] = [];

  displayedColumns: string[] = ['name', 'surname', 'email', 'companyRole'];
  public dataSource = new MatTableDataSource(this.requests);

  constructor(private _registrationRequestsList: RegisteredUsersListService) { }

  ngOnInit(): void {
    this.getRegistrationRequests();
  }

  public getRegistrationRequests(){
    this._registrationRequestsList.getAllRequests().subscribe(res => {
      this.requests = res;
      this.dataSource = new MatTableDataSource(this.requests);
    });
  }

}
