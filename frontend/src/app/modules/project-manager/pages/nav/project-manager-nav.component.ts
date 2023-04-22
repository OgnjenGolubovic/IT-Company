import { Component, OnInit } from '@angular/core';

export interface NavRoute {
  path: string;
  title: string;
}

@Component({
  selector: 'app-project-manager-nav',
  templateUrl: './project-manager-nav.component.html',
  styleUrls: ['./project-manager-nav.component.scss']
})
export class ProjectManagerNavComponent implements OnInit {
  m_Routes: NavRoute[] = [
  ];
  constructor() { }

  ngOnInit(): void {
  }

}
