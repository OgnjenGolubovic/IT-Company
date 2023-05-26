import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AdministratorComponent } from './administrator.component';
import { PermissionsComponent } from './pages/permissions/permissions.component';

const routes: Routes = [
  {
    path: '',
    component: AdministratorComponent,
    children: [
      { path: 'permissions', component: PermissionsComponent },
    ]
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class AdministratorRoutingModule { }
