import { Component, Inject, OnInit } from '@angular/core';
import { MatDialog, MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { MatSnackBar } from '@angular/material/snack-bar';
import { PermissionService, RoleDTO, PermissionDTO } from './permission.service';
import { Observable, map, switchMap, tap } from 'rxjs';

@Component({
  selector: 'app-permissions',
  templateUrl: './permissions.component.html',
  styleUrls: ['./permissions.component.css']
})
export class PermissionsComponent implements OnInit {

  public roles : RoleDTO[] = [];
  public role : RoleDTO = {
    id: 0,
    name: '',
    permissions: []
  };
  public addVisible: boolean = false;
  public deleteVisible: boolean = false;

  constructor(public dialog: MatDialog, private snackBar: MatSnackBar, private permissionService : PermissionService) { }

  ngOnInit(): void {
    this.permissionService.getAllRoles().subscribe(res => {
      this.roles = res;
    });
  }
  deletePermission(role : RoleDTO): void{
    this.role = role;
    this.deleteVisible = true;
    this.initiateDelete(role.permissions).subscribe();
  }
  addPermission(role : RoleDTO): void{
    this.role = role;
    this.permissionService.getAllPermissions(role).pipe(map((res : PermissionDTO[]) =>{
      this.addVisible = true;
      return res;
    }),switchMap((permissions : PermissionDTO[]) => this.initiateAdd(permissions))).subscribe();
  }
  initiateAdd(permissions : PermissionDTO[]): Observable<any> {
    if(this.addVisible){
      const dialogRef = this.dialog.open(AddPermissionDialog, {
        data: permissions,
      });
      dialogRef.disableClose = true;
      return dialogRef.afterClosed().pipe(tap((result : PermissionDTO[])  => {
        result.forEach(element => {
          this.role.permissions.push(element);
        });
      }),switchMap(_ => this.addPermissions()));
    }
    return new Observable<any>;
  }
  addPermissions(): Observable<any>{
    return this.permissionService.updatePermissions(this.role).pipe(tap(_ => {
      this.addVisible = false;
      this.snackBar.open('Permissions Successfully Added','Ok', {
        duration: 2000
      })
    }))
  }

  initiateDelete(permissions : PermissionDTO[]): Observable<any> {
    if(this.deleteVisible){
      const dialogRef = this.dialog.open(DeletePermissionDialog, {
        data: permissions,
      });
      dialogRef.disableClose = true;
      return dialogRef.afterClosed().pipe(tap((result : PermissionDTO[])  => {
        result.forEach(element => {
          const index = this.role.permissions.indexOf(element);
          this.role.permissions.splice(index, 1);          
        });
      }),switchMap(_ => this.deletePermissions()));
    }
    return new Observable<any>;
  }
  deletePermissions(): Observable<any>{
    return this.permissionService.updatePermissions(this.role).pipe(tap(_ => {
      this.deleteVisible = false;
      this.snackBar.open('Permissions Successfully Deleted','Ok', {
        duration: 2000
      })
    }))
  }
}

@Component({
  selector: 'add-permission',
  templateUrl: 'add-permission.html',
})

export class AddPermissionDialog {
  public selectedPermissions : PermissionDTO[] = [];
  constructor(
    public dialogRef: MatDialogRef<AddPermissionDialog>,
    @Inject(MAT_DIALOG_DATA) public data: PermissionDTO[],
  ) {}
    isSelected(item: any): boolean {
      return this.selectedPermissions.includes(item);
    }
    toggleSelection(item: any): void {
      if (this.isSelected(item)) {
        const index = this.selectedPermissions.indexOf(item);
        this.selectedPermissions.splice(index, 1);
      } else {
        this.selectedPermissions.push(item);
      }
    }
}

@Component({
  selector: 'delete-permission',
  templateUrl: 'delete-permission.html',
})

export class DeletePermissionDialog {
  public selectedPermissions : PermissionDTO[] = [];
  constructor(
    public dialogRef: MatDialogRef<DeletePermissionDialog>,
    @Inject(MAT_DIALOG_DATA) public data: PermissionDTO[],
  ) {}
    isSelected(item: any): boolean {
      return this.selectedPermissions.includes(item);
    }
    toggleSelection(item: any): void {
      if (this.isSelected(item)) {
        const index = this.selectedPermissions.indexOf(item);
        this.selectedPermissions.splice(index, 1);
      } else {
        this.selectedPermissions.push(item);
      }
    }

}