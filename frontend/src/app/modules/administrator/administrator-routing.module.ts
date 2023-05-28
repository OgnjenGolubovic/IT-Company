import { Component, NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AdministratorComponent } from './administrator.component';
import { PermissionsComponent } from './pages/permissions/permissions.component';
import { RegistrationRequestsComponent } from './pages/registration-requests/registration-requests.component';
import { AdministrationComponent } from './pages/administration/administration.component';
import { CreateProjectComponent } from './pages/create-project/create-project.component';
import { AssignWorkersComponent } from './pages/assign-workers/assign-workers.component';

const routes: Routes = [
  {
    path: '',
    component: AdministratorComponent,
    children: [
      { 
        path: 'permissions', 
        component: PermissionsComponent 
      },
      {
        path: 'registration-requests',
        component: RegistrationRequestsComponent,
      },
      {
        path: 'administration',
        component: AdministrationComponent
      },
      {
        path: 'create-project',
        component: CreateProjectComponent
      },
      {
        path: 'assign-workers',
        component: AssignWorkersComponent
      }
    ]
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class AdministratorRoutingModule { }
