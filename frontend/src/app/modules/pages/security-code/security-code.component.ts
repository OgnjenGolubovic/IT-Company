import { Component, OnInit } from '@angular/core';
import { AuthService, QrCode } from '../login/log-auth.service';
import { Router } from '@angular/router';
import { UserDataService } from '../login/log-user-data.service';
import { map } from 'rxjs';

@Component({
  selector: 'app-security-code',
  templateUrl: './security-code.component.html',
  styleUrls: ['./security-code.component.css']
})
export class SecurityCodeComponent implements OnInit {
  public code : QrCode = {
    username : "",
    securityCode : ""
  } 
  constructor(private m_AuthService : AuthService, private m_Router: Router, private m_UserDataService : UserDataService) { }

  ngOnInit(): void {
    this.m_UserDataService.m_Username$.pipe(map(username => {
      if(username)this.code.username = username;
    })).subscribe();
  }
  submitForm(): void {
    this.m_AuthService.sendCode(this.code).subscribe(data => {
        if(data){
          this.m_UserDataService.setUsername = null;
          if(data.role == 1)this.m_Router.navigate(['/software-engineer']);   
          if(data.role == 2)this.m_Router.navigate(['/human-resources']); 
          if(data.role == 3)this.m_Router.navigate(['/project-manager']);
          if(data.role == 4)this.m_Router.navigate(['/administrator']);           
        }
      }
    );
  }
}
