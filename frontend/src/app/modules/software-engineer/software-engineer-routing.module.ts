import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { SoftwareEngineerComponent } from './software-engineer.component';

const routes: Routes = [
  {
    path: '',
    component: SoftwareEngineerComponent,
    children: [
    ]
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class SoftwareEngineerRoutingModule { }
