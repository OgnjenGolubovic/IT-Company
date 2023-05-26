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
      path: 'registration-requests',
      title: 'Registration requests'
    }


  ];
  constructor() { }

  ngOnInit(): void {
  }

}
