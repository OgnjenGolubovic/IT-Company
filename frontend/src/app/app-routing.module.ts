import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AuthGuard } from "./modules/pages/login/log-auth.guard";
import { AntiAuthGuard } from "./modules/pages/login/log-anti-auth.guard";
import { LoginComponent } from "./modules/pages/login/login.component";
import { PageNotFoundComponent } from "./modules/pages/page-not-found/page-not-found.component";
import { SecurityCodeComponent } from './modules/pages/security-code/security-code.component';
import { CodeGuard } from './modules/pages/login/log-code-guard';

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
  { path: 'not-found', component: PageNotFoundComponent },
  { path: 'code', component: SecurityCodeComponent, canActivate : [CodeGuard] },
  { path: 'login', component: LoginComponent },
  { path: '**', redirectTo: 'not-found', pathMatch: 'full' }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
