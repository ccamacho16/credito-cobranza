import { Component, Inject, OnInit } from '@angular/core';
import { FormControl, FormGroup, ValidatorFn, Validators } from '@angular/forms';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';
import { ConstantsMessage } from 'app/core/constants';
import { UserStatus, getUserStatusLabel } from 'app/core/models/Mapeo/user-status';
import { MuUserDto } from 'app/core/models/mu-user-dto';
import { CodeDescriptionDto } from 'app/core/models/select/code-description-dto';
import { BranchService } from 'app/core/services/http/branch.service';
import { NotiflixService } from 'app/core/services/http/notiflix.service';
import { RoleService } from 'app/core/services/http/role.service';
import { UserService } from 'app/core/services/http/user.service';

@Component({
  selector: 'app-user-dlg',
  templateUrl: './user-dlg.component.html',
  styleUrls: ['./user-dlg.component.scss']
})
export class UserDlgComponent implements OnInit {

  clientDto: MuUserDto
  formGroup: FormGroup;
  titulo: string = '';
  accion: string = '';
  listRol: CodeDescriptionDto[];
  listBranch: CodeDescriptionDto[];
  hidePassword: boolean = true;

  userStatuses = Object.values(UserStatus); // Obtiene los valores del enum
  getUserStatusLabel = getUserStatusLabel; // Hace accesible la función en la plantilla

  constructor(private dialogRef: MatDialogRef<UserDlgComponent>,
                private userService: UserService,
                private notiflixService: NotiflixService,
                private branchService: BranchService,
                private roleService: RoleService,
                @Inject(MAT_DIALOG_DATA) public data: any) {
      this.clientDto = <MuUserDto> this.data.model;
      this.titulo = this.data.titulo;
      this.accion = this.data.accion;
      this.inicializarForm(this.clientDto);
  }

  ngOnInit(): void {
    this.loadListBranch();
    this.loadListRol();
  }

  inicializarForm(dto: MuUserDto): void{
    this.formGroup = new FormGroup({
      id: new FormControl(dto ? dto.id : null), 
      name: new FormControl(dto ? dto.name : null, [Validators.required]),
      username: new FormControl(dto ? dto.username : null, [Validators.required]),
      password: new FormControl(null, this.getPasswordValidators(this.accion)),
      email: new FormControl(dto ? dto.email : null, [Validators.required, Validators.email]),
      status: new FormControl(dto ? dto.status : UserStatus.ACTIVO, [Validators.required]),

      idRole: new FormControl(dto ? dto.idRole : null, [Validators.required]),
      nameRole: new FormControl(dto ? dto.nameRole : null),
      idBranch: new FormControl(dto ? dto.idBranch : null, [Validators.required]),
      nameBranch: new FormControl(dto ? dto.nameBranch : null),
    });
  }

  private getPasswordValidators(accion: string): ValidatorFn[] {
    if (accion === 'create') {
      return [Validators.required, Validators.minLength(7), Validators.maxLength(12)];
    } else {
      return [Validators.minLength(7), Validators.maxLength(12)]; // En update, es opcional pero debe tener mínimo 7 si se ingresa
    }
  }

  loadListBranch(): void{
    this.branchService.listForSelect().subscribe(
      response => {
          this.listBranch = response;
          console.log("loadListBranch", this.listBranch);
    });
  }

  loadListRol(): void{
    this.roleService.listForSelect().subscribe(
      response => {
          this.listRol = response;
    });
  }

  saveClient(){
      if (this.formGroup.valid){
        this.userService.save(this.formGroup.value).subscribe(
          response => {
              this.dialogRef.close(true);
              this.notiflixService.showSuccess(ConstantsMessage.MENSAJE_CREAR);
          },
          error => {
            console.log(error.error.detail);
          }
        );
      }else{
        this.notiflixService.showError(ConstantsMessage.DATOS_INCORRECTOS);
      } 
  }
    
  updateClient(){
    if (this.formGroup.valid){
      this.userService.update(this.formGroup.value).subscribe(
        response => {
            this.dialogRef.close(true);
            this.notiflixService.showSuccess(ConstantsMessage.MENSAJE_ACTUALIZAR);
        },
        error => {
          console.log(error.error.detail);
        }
      );
    }else{
      this.notiflixService.showError(ConstantsMessage.DATOS_INCORRECTOS);
    } 
  }
}
