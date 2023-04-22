import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { AppRoutingModule } from 'src/app/app-routing.module';
import { MatTableModule } from '@angular/material/table';
import { FormsModule } from '@angular/forms';
import { ReactiveFormsModule } from '@angular/forms';
import { LoginComponent } from './login/login.component';
import { MatInputModule } from '@angular/material/input';
import { MatButtonModule } from '@angular/material/button';
import { MatCardModule } from '@angular/material/card';
import { MatIconModule } from '@angular/material/icon';
import { MatTooltipModule } from '@angular/material/tooltip';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatRadioModule } from '@angular/material/radio';
import { MatSortModule } from '@angular/material/sort';
import { MatSnackBarModule } from '@angular/material/snack-bar';

@NgModule({
  declarations: [
    LoginComponent
  ],
  imports: [
    FormsModule,
    CommonModule,
    AppRoutingModule,
    MatTableModule,
    ReactiveFormsModule,
    MatInputModule,
    MatButtonModule,
    MatTableModule,
    MatCardModule,
    MatIconModule,
    MatTooltipModule,
    MatFormFieldModule,
    MatRadioModule,
    MatSortModule,
    MatSnackBarModule
  ]
})
export class PagesModule { }
