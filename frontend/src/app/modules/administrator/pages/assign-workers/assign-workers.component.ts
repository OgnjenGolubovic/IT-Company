import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Project, SoftwareEngineer } from './interfaces/assign-workers.interface';
import { AssignWorkersService } from './services/assign-workers.service';

@Component({
  selector: 'app-assign-workers',
  templateUrl: './assign-workers.component.html',
  styleUrls: ['./assign-workers.component.css']
})
export class AssignWorkersComponent implements OnInit {

  assignForm = new FormGroup({
    softwareEngineer: new FormControl(null, [Validators.required]),
    project: new FormControl(null, [Validators.required])
  })

  softwareEngineers: SoftwareEngineer[] = [];
  projects: Project[] = [];

  constructor(private _assignWorkersService: AssignWorkersService) { }

  ngOnInit(): void {
    this._assignWorkersService.getAllSoftwareEngineers().subscribe(res =>{
      this.softwareEngineers = res;
    })

    this._assignWorkersService.getAllProjects().subscribe(res =>{
      this.projects = res;
    })

  }

  assign(){
    if (!this.assignForm.valid) {
      return;
    }
    this._assignWorkersService.assign(this.assignForm.value).pipe().subscribe();
  }



}
