import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AuthGuard } from "./modules/pages/login/log-auth.guard";
import { LoginComponent } from "./modules/pages/login/login.component";


const routes: Routes = [
  {
    path: 'administrator',
    loadChildren: () => import('./modules/administrator/administrator.module').then(a => a.AdministratorModule),
    canActivate : [AuthGuard]
  },
  {
    path: 'software-engineer',
    loadChildren: () => import('./modules/software-engineer/software-engineer.module').then(ac => ac.SoftwareEngineerModule),
    canActivate : [AuthGuard]
  },
  {
    path: 'project-manager',
    loadChildren: () => import('./modules/project-manager/project-manager.module').then(ac => ac.ProjectManagerModule),
    canActivate : [AuthGuard]
  },
  {
    path: 'human-resources',
    loadChildren: () => import('./modules/human-resources/human-resources.module').then(ac => ac.HumanResourcesModule),
    canActivate : [AuthGuard]
  },
  { path: '', redirectTo: 'login', pathMatch: 'full' },
  { path: 'login', component: LoginComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
