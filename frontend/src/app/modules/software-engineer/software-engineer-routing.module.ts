import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { SoftwareEngineerComponent } from './software-engineer.component';
import { QrCodeComponent } from '../pages/qr-code/qr-code.component';
import { ProfileComponent } from './pages/profile/profile.component';
import { ProjectsComponent } from './pages/projects/projects.component';

const routes: Routes = [
  {
    path: '',
    component: SoftwareEngineerComponent,
    children: [
      { path: 'qr-code', component: QrCodeComponent },
      { path: 'profile', component: ProfileComponent},
      { path: 'projects', component: ProjectsComponent}
    ]
  }
];
@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class SoftwareEngineerRoutingModule { }
