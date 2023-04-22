import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HumanResourcesComponent } from './human-resources.component';

const routes: Routes = [
  {
    path: '',
    component: HumanResourcesComponent,
    children: [
    ]
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class HumanResourcesRoutingModule { }
