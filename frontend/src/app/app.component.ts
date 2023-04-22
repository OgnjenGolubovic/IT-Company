import { Component } from '@angular/core';
import { EMPTY, switchMap, take } from 'rxjs';
import { AuthService } from './modules/pages/login/log-auth.service';
import { UserDataService } from './modules/pages/login/log-user-data.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  constructor(private authService : AuthService, private userDataService : UserDataService){}
  ngOnInit(): void {
    this.userDataService.m_Token$.pipe(take(1), switchMap(token => {
      return !!token ? this.authService.getUserData() : EMPTY;
    })).subscribe();
  }
  title = 'IT Company';
}
