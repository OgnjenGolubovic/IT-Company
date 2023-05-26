import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { AdministratorRoutingModule } from './administrator-routing.module';
import { AdministratorComponent } from './administrator.component';
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
import { RegistrationRequestsComponent } from './pages/registration-requests/registration-requests.component';

@NgModule({
  declarations: [
    AdministratorComponent,
    AdministratorNavComponent,
    RegistrationRequestsComponent,
  ],
  imports: [
    CommonModule,
    AdministratorRoutingModule,
    ReactiveFormsModule,
    MatPaginatorModule,
    MatListModule,
    MatDialogModule,
    MatOptionModule,
    MatSelectModule,
    MatToolbarModule,
    MatSidenavModule,
    MatTableModule,
    FormsModule
  ]
})
export class AdministratorModule { }
