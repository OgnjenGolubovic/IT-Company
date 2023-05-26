import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { SoftwareEngineerRoutingModule } from './software-engineer-routing.module';
import { SoftwareEngineerComponent } from './software-engineer.component';
import { SoftwareEngineerNavComponent } from './pages/nav/software-engineer-nav.component';
import { FormsModule, ReactiveFormsModule } from "@angular/forms";
import { MatListModule } from '@angular/material/list';
import { MatToolbarModule } from '@angular/material/toolbar';
import { MatSidenavModule } from '@angular/material/sidenav';
import { MatTableModule } from '@angular/material/table';
import { MatPaginatorModule } from '@angular/material/paginator';
import { ProfileComponent } from './pages/profile/profile.component';


@NgModule({
  declarations: [
    SoftwareEngineerNavComponent,
    SoftwareEngineerComponent,
    ProfileComponent
  ],
  imports: [
    CommonModule,
    SoftwareEngineerRoutingModule,
    ReactiveFormsModule,
    MatPaginatorModule,
    MatListModule,
    MatToolbarModule,
    MatSidenavModule,
    MatTableModule,
    FormsModule
  ]
})
export class SoftwareEngineerModule { }
