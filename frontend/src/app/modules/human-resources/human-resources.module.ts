import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { HumanResourcesRoutingModule } from './human-resources-routing.module';
import { HumanResourcesComponent } from './human-resources.component';
import { HumanResourcesNavComponent } from './pages/nav/human-resources-nav.component';
import { FormsModule, ReactiveFormsModule } from "@angular/forms";
import { MatListModule } from '@angular/material/list';
import { MatToolbarModule } from '@angular/material/toolbar';
import { MatSidenavModule } from '@angular/material/sidenav';
import { MatTableModule } from '@angular/material/table';
import { MatPaginatorModule } from '@angular/material/paginator';


@NgModule({
  declarations: [
    HumanResourcesComponent,
    HumanResourcesNavComponent
  ],
  imports: [
    CommonModule,
    HumanResourcesRoutingModule,
    ReactiveFormsModule,
    MatPaginatorModule,
    MatListModule,
    MatToolbarModule,
    MatSidenavModule,
    MatTableModule,
    FormsModule
  ]
})
export class HumanResourcesModule { }
