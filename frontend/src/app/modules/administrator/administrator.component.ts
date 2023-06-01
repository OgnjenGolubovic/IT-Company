import { Component, Inject, OnInit } from '@angular/core';
import { MatDialog, MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';
import { MatFormField } from '@angular/material/form-field';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Router } from '@angular/router';
import { map, Observable, switchMap, tap } from 'rxjs';
import { AuthService } from '../pages/login/log-auth.service';
import { UserDataService } from '../pages/login/log-user-data.service';
import { AdminService, PasswordDTO } from './admin.service';


export interface Password {
  new: string;
  confirm: string;
}

@Component({
  selector: 'app-administrator',
  templateUrl: './administrator.component.html',
  styleUrls: ['./administrator.component.scss']
})
export class AdministratorComponent implements OnInit {

  new: string = '';
  confirm: string = '';
  visible: boolean = true;
  password: PasswordDTO = {
    password : '',
    confirmPassword : ''
  }

  constructor(public dialog: MatDialog, private snackBar: MatSnackBar,private m_UserDataService : UserDataService, 
    private m_AuthService: AuthService, private m_Router: Router, private adminService : AdminService) { }

  ngOnInit(): void {
    this.adminService.getIfPasswordChanged().pipe(tap(res =>{
      this.visible = !res;
    }),switchMap(_ => this.initate())).subscribe();    
  }
  initate(): Observable<any>{
    if(this.visible){
      const dialogRef = this.dialog.open(ChangePasswordDialog, {
        data: {new: this.new, confirm: this.confirm},
      });
      dialogRef.disableClose = true;
      return dialogRef.afterClosed().pipe(map(result  => {
        this.new = result.new;
        this.confirm = result.confirm;
        this.password.password = result.new;
        this.password.confirmPassword = result.confirm;
      }),switchMap(_ => this.changePassword()));
    }
    return new Observable<any>;
  }
  changePassword(): Observable<any>{
    return this.adminService.changePassword(this.password).pipe(tap(_ => {
      this.snackBar.open('Password Successfully Changed','Ok', {
        duration: 3000
      })
      this.logout();
    }));
  }
  logout(): void {
    this.m_AuthService.logout();
    this.m_Router.navigate(['/login']);
  }
}

@Component({
  selector: 'change-password',
  templateUrl: 'change-password.html',
})
export class ChangePasswordDialog {
  flag: boolean = true;
  constructor(
    public dialogRef: MatDialogRef<ChangePasswordDialog>,
    @Inject(MAT_DIALOG_DATA) public data: Password,
  ) {}
  check(): void{
    if(!this.data.new){
      this.flag = true;
      return;
    }
    if(!this.data.confirm){
      this.flag = true;
      return;
    }
    if(this.data.new != this.data.confirm){
      this.flag = true;
      return;
    }
    this.flag = false;
  }
}