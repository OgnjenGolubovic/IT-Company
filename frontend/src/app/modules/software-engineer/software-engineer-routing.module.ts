import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { SoftwareEngineerComponent } from './software-engineer.component';
import { QrCodeComponent } from '../pages/qr-code/qr-code.component';

const routes: Routes = [
  {
    path: '',
    component: SoftwareEngineerComponent,
    children: [
      { path: 'qr-code', component: QrCodeComponent },
    ]
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class SoftwareEngineerRoutingModule { }
