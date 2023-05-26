import { Component, NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AdministratorComponent } from './administrator.component';
import { PermissionsComponent } from './pages/permissions/permissions.component';
import { RegistrationRequestsComponent } from './pages/registration-requests/registration-requests.component';

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
      }
    ]
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class AdministratorRoutingModule { }
