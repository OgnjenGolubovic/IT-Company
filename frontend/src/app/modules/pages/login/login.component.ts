import { Component, OnInit } from '@angular/core';
import { UntypedFormControl, UntypedFormGroup, Validators } from '@angular/forms'
import { AuthService, LoginDTO } from 'src/app/modules/pages/login/log-auth.service'
import { MatSnackBar } from '@angular/material/snack-bar'
import { Router } from '@angular/router';
import { UserDataService } from './log-user-data.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  m_Form: UntypedFormGroup = this.formInstance;
  m_Errors: string[] = [];

  constructor(private m_AuthService: AuthService, private m_SnackBar: MatSnackBar, private m_Router: Router,
    private m_UserDataService : UserDataService) { }

  ngOnInit(): void {
  }

  onSubmit() {
    this.m_Errors.length = 0;
    const dto: LoginDTO = this.m_Form.getRawValue();
    if (!this.m_Form.valid) return;

    this.m_AuthService.login(dto)
      .subscribe({ next: data => {
        if(data){
          if(data.role == 1)this.m_Router.navigate(['/software-engineer']);   
          if(data.role == 2)this.m_Router.navigate(['/human-resources']); 
          if(data.role == 3)this.m_Router.navigate(['/project-manager']);
          if(data.role == 4)this.m_Router.navigate(['/administrator']);           
        }else{
          this.m_UserDataService.setUsername = dto.username;
          this.m_Router.navigate(['/code']);
        }
      }, error : error => {
        this.m_SnackBar.open('Username or Password is wrong','Ok', {
          duration: 2000
        })
        //if(this.m_Errors.length != 0) this.m_Errors.pop();
        //this.m_Errors.push(error.error.message);
      }});
  }

  get formInstance(): UntypedFormGroup {
    return new UntypedFormGroup({
      'username': new UntypedFormControl(null, [Validators.required]),
      'password': new UntypedFormControl(null, [Validators.required])
    });
  }
}
