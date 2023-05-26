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
import { PageNotFoundComponent } from './page-not-found/page-not-found.component';
import { QrCodeComponent } from './qr-code/qr-code.component';
import { QRCodeModule  } from 'angularx-qrcode';
import { SecurityCodeComponent } from './security-code/security-code.component';
import { RegisterComponent } from './register/register.component';
import { MatSelectModule } from '@angular/material/select';

@NgModule({
  declarations: [
    LoginComponent,
    PageNotFoundComponent,
    QrCodeComponent,
    SecurityCodeComponent,
    RegisterComponent
  ],
  imports: [
    QRCodeModule,
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
    MatSelectModule,
    MatSnackBarModule
  ]
})
export class PagesModule { }
