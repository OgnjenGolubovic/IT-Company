import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ProjectManagerComponent } from './project-manager.component';

const routes: Routes = [
  {
    path: '',
    component: ProjectManagerComponent,
    children: [
    ]
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class ProjectManagerRoutingModule { }
