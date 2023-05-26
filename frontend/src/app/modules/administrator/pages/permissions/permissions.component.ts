import { Component, OnInit } from '@angular/core';
import { MatDialog, MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { MatSnackBar } from '@angular/material/snack-bar';
import { PermissionService, RoleDTO, PermissionDTO } from './permission.service';

@Component({
  selector: 'app-permissions',
  templateUrl: './permissions.component.html',
  styleUrls: ['./permissions.component.css']
})
export class PermissionsComponent implements OnInit {

  public roles : RoleDTO[] = [];

  constructor(public dialog: MatDialog, private snackBar: MatSnackBar, private permissionService : PermissionService) { }

  ngOnInit(): void {
    this.permissionService.getAllRoles().subscribe(res => {
      this.roles = res;
    });
  }
  deletePermission(): void{

  }
  addPermission(): void{

  }
}

