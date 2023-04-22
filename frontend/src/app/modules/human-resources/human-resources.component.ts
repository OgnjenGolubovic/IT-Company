import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from '../pages/login/log-auth.service';

@Component({
  selector: 'app-human-resources',
  templateUrl: './human-resources.component.html',
  styleUrls: ['./human-resources.component.scss']
})
export class HumanResourcesComponent implements OnInit {

  constructor(private m_Router : Router, private m_AuthService : AuthService) { }

  ngOnInit(): void {
  }

  logout(): void {
    this.m_AuthService.logout();
    this.m_Router.navigate(['/login']);
  }

}
