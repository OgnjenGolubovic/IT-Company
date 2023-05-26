import { Component, OnInit } from '@angular/core';
import { UserDataService } from '../login/log-user-data.service';
import { Observable, switchMap, tap } from 'rxjs';
import { MatSnackBar } from '@angular/material/snack-bar';
import { AuthService } from '../login/log-auth.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-qr-code',
  templateUrl: './qr-code.component.html',
  styleUrls: ['./qr-code.component.css']
})
export class QrCodeComponent implements OnInit {
  public qrCodeData: string = "";
  constructor(private m_UserDataService : UserDataService, private snackBar: MatSnackBar,
    private m_AuthService : AuthService, private m_Router : Router){}
  ngOnInit(): void {
    this.m_AuthService.qrCode().subscribe(res => {
      this.qrCodeData = res['QrCode'];
    });
  }
  setUp(): void {
    this.m_AuthService.set2FA().subscribe(_ => {
      this.m_Router.navigate(['/software-engineer']);
    });
  }
}
