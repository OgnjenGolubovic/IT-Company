import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { CustomValidators } from './custom-validator';
import { Router } from '@angular/router';
import { tap } from 'rxjs';
import { AuthService } from './services/register.service';
import { CompanyRole } from './interfaces';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {

  registerForm = new FormGroup({
    email: new FormControl(null, [Validators.required, Validators.email]),
    name: new FormControl(null, [Validators.required]),
    surname: new FormControl(null, [Validators.required]),
    street: new FormControl(null),
    streetNumber: new FormControl(null),
    city: new FormControl(null),
    state: new FormControl(null),
    phone: new FormControl(null),
    companyRole: new FormControl(null),
    title: new FormControl(null),
    password: new FormControl(null, [Validators.required]),
    passwordConfirm: new FormControl(null, [Validators.required])
  },
    { validators: CustomValidators.passwordsMatching }
  )

  companyRoles: CompanyRole[] = [
    {value: 'humanResourceManager', viewValue: 'Human Resource Manager'},
    {value: 'softwareEngineer', viewValue: 'Software Engineer'},
    {value: 'projectManager', viewValue: 'Project Manager'},
  ];

  constructor(private router: Router,
    private authService: AuthService
    ) { }

  ngOnInit(): void {
  }

  register() {
    if (!this.registerForm.valid) {
      return;
    }
    this.authService.register(this.registerForm.value).pipe(
      // If registration was successfull, then navigate to login route
      tap(() => this.router.navigate(['../login']))
    ).subscribe();
  }

}
