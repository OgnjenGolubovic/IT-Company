import { Component, OnInit } from '@angular/core';
import { User } from './profile.interface';
import { ProfileService } from './profile.service';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit {

  public user: User = {
    email: '',
    name: '',
    surname: '',
    state: '',
    city: '',
    street: '',
    streetNumber: '',
    phone: '',
    skills: '',
    cv: new File([], 'dummy.txt')
  }


  constructor(
    private profileService: ProfileService
  ) { }

  ngOnInit(): void {
    this.getUserInfo();
  }

  submit() : void {
    this.profileService.submit(this.user).pipe().subscribe();
  }

  public getUserInfo(){
    this.profileService.getById().subscribe(res =>{
      this.user = res;
      this.user.email = res.username;
    })
  }


}
