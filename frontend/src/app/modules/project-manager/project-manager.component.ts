import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from '../pages/login/log-auth.service';

@Component({
  selector: 'app-project-manager',
  templateUrl: './project-manager.component.html',
  styleUrls: ['./project-manager.component.scss']
})
export class ProjectManagerComponent implements OnInit {

  constructor(private m_Router : Router, private m_AuthService : AuthService) { }

  ngOnInit(): void {
  }

  logout(): void {
    this.m_AuthService.logout();
    this.m_Router.navigate(['/login']);
  }

}
