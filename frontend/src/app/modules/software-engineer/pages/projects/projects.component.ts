import { Component, OnInit } from '@angular/core';
import { Project } from './projects.interface';
import { ProjectsService } from './projects.service';
import { pipe } from 'rxjs';

@Component({
  selector: 'app-projects',
  templateUrl: './projects.component.html',
  styleUrls: ['./projects.component.css']
})
export class ProjectsComponent implements OnInit {

  projects: Project[] = [];

  constructor(
    private projectsService: ProjectsService
  ) { }

  ngOnInit(): void {
    this.getProjects();
  }

  submit(project: any){
    this.projectsService.submit(project).pipe().subscribe();
  }

  public getProjects(){
    this.projectsService.getProjects().subscribe(res =>{
      this.projects = res;
      for (const project of this.projects) {
        project.user_id = this.projectsService.id;
      }
    })
  }

}
