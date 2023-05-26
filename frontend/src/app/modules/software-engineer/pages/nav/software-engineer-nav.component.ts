import { Component, OnInit } from '@angular/core';
import { tap } from 'rxjs';
import { AuthService } from 'src/app/modules/pages/login/log-auth.service';
import { UserDataService } from 'src/app/modules/pages/login/log-user-data.service';

export interface NavRoute {
  path: string;
  title: string;
}

@Component({
  selector: 'app-software-engineer-nav',
  templateUrl: './software-engineer-nav.component.html',
  styleUrls: ['./software-engineer-nav.component.scss']
})
export class SoftwareEngineerNavComponent implements OnInit {

  m_Routes: NavRoute[] = [
    {
      path: 'qr-code',
      title: 'qr-code'
    },
    {
      path: 'profile',
      title: 'Profile'
    }

  ];
  constructor() { 
  }

  ngOnInit(): void {
  }
}
