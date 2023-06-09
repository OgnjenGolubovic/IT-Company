import { Component, OnInit } from '@angular/core';

export interface NavRoute {
  path: string;
  title: string;
}

@Component({
  selector: 'app-administrator-nav',
  templateUrl: './administrator-nav.component.html',
  styleUrls: ['./administrator-nav.component.scss']
})
export class AdministratorNavComponent implements OnInit {
  m_Routes: NavRoute[] = [
    {
      path: 'permissions',
      title: 'Permissions'
    },
    {
      path: 'registration-requests',
      title: 'Registration requests'
    },
    {
      path: 'administration',
      title: 'Administration'
    },
    {
      path: 'create-project',
      title: 'Create project'
    },
    {
      path: 'assign-workers',
      title: 'Assign workers'
    },
    {
      path: 'profile',
      title: 'Profile'
    },
    {
      path: 'projects',
      title: 'Projects'
    },
    {
      path: 'admin-register',
      title: 'Admin register'
    }
  ];
  constructor() { }

  ngOnInit(): void {
  }

}
