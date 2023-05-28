import { Component, OnInit } from '@angular/core';
import { User } from './interfaces/administration.interface';
import { AdministrationService } from './services/administration.service';
import { MatTableDataSource } from '@angular/material/table';

@Component({
  selector: 'app-administration',
  templateUrl: './administration.component.html',
  styleUrls: ['./administration.component.css']
})
export class AdministrationComponent implements OnInit {

  public users: User[] = [];
  displayedColumns: string[] = ['name', 'surname', 'company_role', 'phone_number'];
  public dataSource = new MatTableDataSource(this.users);

  constructor(private _administrationService: AdministrationService) { }

  ngOnInit(): void {
    this._administrationService.getAllUsers().subscribe(res => {
      this.users = res;
      this.dataSource = new MatTableDataSource(this.users);
    })
  }

}
