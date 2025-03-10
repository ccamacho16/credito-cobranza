import { Component, Inject, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';
import { ConstantsMessage } from 'app/core/constants';
import { AmBranchDto } from 'app/core/models/am-branch-dto';
import { BranchService } from 'app/core/services/http/branch.service';
import { NotiflixService } from 'app/core/services/http/notiflix.service';

@Component({
  selector: 'app-branch-dlg',
  templateUrl: './branch-dlg.component.html',
  styleUrls: ['./branch-dlg.component.scss']
})
export class BranchDlgComponent implements OnInit {
  
  branchDto: AmBranchDto
  formGroup: FormGroup;
  titulo: string = '';
  accion: string = '';

  constructor(private dialogRef: MatDialogRef<BranchDlgComponent>,
                private branchService: BranchService,
                private notiflixService: NotiflixService,
                @Inject(MAT_DIALOG_DATA) public data: any) { 
        this.branchDto = <AmBranchDto> this.data.model;
        this.titulo = this.data.titulo;
        this.accion = this.data.accion;
        this.inicializarForm(this.branchDto);
  }

  ngOnInit(): void {
  }

  inicializarForm(dto: AmBranchDto): void{
      this.formGroup = new FormGroup({
        id: new FormControl(dto ? dto.id : null), 
        name: new FormControl(dto ? dto.name : null, [Validators.required]),
        description: new FormControl(dto ? dto.description : null, [Validators.required]),
        address: new FormControl(dto ? dto.address : null, [Validators.required]),
        phone: new FormControl(dto ? dto.phone : null, [Validators.required]),
      });
  }

    saveClient(){
       if (this.formGroup.valid){
        this.branchService.save(this.formGroup.value).subscribe(
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
        this.branchService.update(this.formGroup.value).subscribe(
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
