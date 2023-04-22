import { Component, OnInit } from '@angular/core';

export interface NavRoute {
  path: string;
  title: string;
}

@Component({
  selector: 'app-human-resources-nav',
  templateUrl: './human-resources-nav.component.html',
  styleUrls: ['./human-resources-nav.component.scss']
})
export class HumanResourcesNavComponent implements OnInit {

  m_Routes: NavRoute[] = [

    ];
  constructor() { 
  }

  ngOnInit(): void {
    
  }
}
