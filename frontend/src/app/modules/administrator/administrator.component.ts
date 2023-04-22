import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from '../pages/login/log-auth.service';

@Component({
  selector: 'app-administrator',
  templateUrl: './administrator.component.html',
  styleUrls: ['./administrator.component.scss']
})
export class AdministratorComponent implements OnInit {

  constructor(private m_AuthService: AuthService, private m_Router: Router) { }

  ngOnInit(): void {

  }

  logout(): void {
    this.m_AuthService.logout();
    this.m_Router.navigate(['/login']);
  }
}