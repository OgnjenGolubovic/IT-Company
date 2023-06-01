import { Component } from '@angular/core';
import { EMPTY, switchMap, take, tap } from 'rxjs';
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
    const token = this.userDataService.getToken();
    if(token){
      this.authService.refreshToken(token).subscribe();
    }
  }
  title = 'IT Company';
}
