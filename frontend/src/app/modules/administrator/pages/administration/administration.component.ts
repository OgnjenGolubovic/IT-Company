import { Component, OnInit } from '@angular/core';
import { Project, User } from './interfaces/administration.interface';
import { AdministrationService } from './services/administration.service';
import { MatTableDataSource } from '@angular/material/table';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { tap } from 'rxjs';

@Component({
  selector: 'app-administration',
  templateUrl: './administration.component.html',
  styleUrls: ['./administration.component.css']
})
export class AdministrationComponent implements OnInit {

  public users: User[] = [];
  displayedColumns: string[] = ['name', 'surname', 'company_role', 'phone_number'];
  public dataSource = new MatTableDataSource(this.users);

  public projects: Project[] = [];
  displayedColumns2: string[] = ['name', 'duration'];
  public dataSource2 = new MatTableDataSource(this.projects);

  constructor(private _administrationService: AdministrationService) { }

  ngOnInit(): void {
    this._administrationService.getAllUsers().subscribe(res => {
      this.users = res;
      this.dataSource = new MatTableDataSource(this.users);
    })

    this._administrationService.getAllProjects().subscribe(res => {
      this.projects = res;
      this.dataSource2 = new MatTableDataSource(this.projects);
    })

  }

}
