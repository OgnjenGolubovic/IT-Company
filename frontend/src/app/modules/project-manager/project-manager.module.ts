import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ProjectManagerRoutingModule } from './project-manager-routing.module';
import { ProjectManagerComponent } from './project-manager.component';
import { ProjectManagerNavComponent } from './pages/nav/project-manager-nav.component';
import { FormsModule, ReactiveFormsModule } from "@angular/forms";
import { MatListModule } from '@angular/material/list';
import { MatToolbarModule } from '@angular/material/toolbar';
import { MatSidenavModule } from '@angular/material/sidenav';
import { MatTableModule } from '@angular/material/table';


@NgModule({
  declarations: [
    ProjectManagerComponent,
    ProjectManagerNavComponent,
  ],
  imports: [
    CommonModule,
    ProjectManagerRoutingModule,
    ReactiveFormsModule,
    MatListModule,
    MatToolbarModule,
    MatSidenavModule,
    MatTableModule,
    FormsModule
  ]
})
export class ProjectManagerModule { }
