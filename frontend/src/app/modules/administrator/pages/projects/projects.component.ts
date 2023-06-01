import { Component, OnInit } from '@angular/core';
import { MatTableDataSource } from '@angular/material/table';
import { Project } from '../create-project/create-project.interface';
import { ProjectsService } from './services/projects.service';
import { User } from './interfaces/projects.interface';

@Component({
  selector: 'app-projects',
  templateUrl: './projects.component.html',
  styleUrls: ['./projects.component.css']
})
export class ProjectsComponent implements OnInit {

  public projects: Project[] = [];
  displayedColumns2: string[] = ['name', 'duration'];
  public dataSource2 = new MatTableDataSource(this.projects);

  public users: User[] = [];
  displayedColumns: string[] = ['name', 'surname', 'phone'];
  public dataSource = new MatTableDataSource(this.users);

  constructor(private projectsService: ProjectsService) { }

  ngOnInit(): void {
    this.projectsService.getAllProjects().subscribe(res => {
      this.projects = res;
      this.dataSource2 = new MatTableDataSource(this.projects);
    })
  }

  onRowClick(id: number): void{

    this.projectsService.getAllProjectsUsers(id).subscribe(ret =>{
      this.users = ret;
      this.dataSource = new MatTableDataSource(this.users);
    });
  }


}
