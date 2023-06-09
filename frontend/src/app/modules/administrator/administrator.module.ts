import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { AdministratorRoutingModule } from './administrator-routing.module';
import { AdministratorComponent, ChangePasswordDialog } from './administrator.component';
import { AdministratorNavComponent } from './pages/nav/administrator-nav.component';
import { FormsModule, ReactiveFormsModule } from "@angular/forms";
import { MatListModule } from '@angular/material/list';
import { MatToolbarModule } from '@angular/material/toolbar';
import { MatSidenavModule } from '@angular/material/sidenav';
import { MatTableModule } from '@angular/material/table';
import { MatOptionModule } from '@angular/material/core';
import { MatSelectModule } from '@angular/material/select';
import { MatDialogModule } from '@angular/material/dialog';
import { MatPaginatorModule } from '@angular/material/paginator';
import { AddPermissionDialog, DeletePermissionDialog, PermissionsComponent } from './pages/permissions/permissions.component';
import { MatCardModule } from '@angular/material/card';
import { RegistrationRequestsComponent } from './pages/registration-requests/registration-requests.component';
import { AdministrationComponent } from './pages/administration/administration.component';
import { CreateProjectComponent } from './pages/create-project/create-project.component';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { AssignWorkersComponent } from './pages/assign-workers/assign-workers.component';
import { ProfileComponent } from './pages/profile/profile.component';
import { ProjectsComponent } from './pages/projects/projects.component';
import { AdminRegisterComponent } from './pages/admin-register/admin-register.component';

@NgModule({
  declarations: [
    AdministratorComponent,
    AdministratorNavComponent,
    PermissionsComponent,
    AddPermissionDialog,
    DeletePermissionDialog,
    ChangePasswordDialog,
    RegistrationRequestsComponent,
    AdministrationComponent,
    CreateProjectComponent,
    AssignWorkersComponent,
    ProfileComponent,
    ProjectsComponent,
    AdminRegisterComponent,
  ],
  imports: [
    CommonModule,
    AdministratorRoutingModule,
    MatCardModule,
    ReactiveFormsModule,
    MatPaginatorModule,
    MatListModule,
    MatDialogModule,
    MatOptionModule,
    MatSelectModule,
    MatToolbarModule,
    MatSidenavModule,
    MatTableModule,
    FormsModule,
    MatFormFieldModule,
    MatInputModule
  ]
})
export class AdministratorModule { }
