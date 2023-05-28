import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { CreateProjectService } from './create-project.service';

@Component({
  selector: 'app-create-project',
  templateUrl: './create-project.component.html',
  styleUrls: ['./create-project.component.css']
})
export class CreateProjectComponent implements OnInit {

  createProjectForm = new FormGroup({
    name: new FormControl(null, [Validators.required]),
    duration: new FormControl(null, [Validators.required])
  })

  constructor(private _createProjectService: CreateProjectService) { }

  ngOnInit(): void {
  }

  createProject() {
    if (!this.createProjectForm.valid) {
      return;
    }
    this._createProjectService.createProject(this.createProjectForm.value).pipe().subscribe();
  }


}
