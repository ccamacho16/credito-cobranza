import { Component, Inject, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';
import { RcClientDto } from 'app/core/models/rc-client-dto';
import { ClientService } from 'app/core/services/http/client.service';
import { NotiflixService } from 'app/core/services/http/notiflix.service';
import { DomainService } from '../../../../core/services/http/domain.service';
import { MuDomainValueDto } from 'app/core/models/mu-domain-value-dto';
import * as moment from 'moment';
import { ConstantsDomain, ConstantsMessage } from 'app/core/constants';
import { map, startWith } from 'rxjs/operators';
import { Observable } from 'rxjs';

@Component({
  selector: 'app-client-dlg',
  templateUrl: './client-dlg.component.html',
  styleUrls: ['./client-dlg.component.scss']
})
export class ClientDlgComponent implements OnInit {

  clientDto: RcClientDto
  formGroup: FormGroup;
  listGender: MuDomainValueDto[];
  listStateCivil: MuDomainValueDto[];
  listCategory: MuDomainValueDto[];
  
  titulo: string = '';
  accion: string = '';

  occupationFC: FormControl;
  filterdOptionOccupation: MuDomainValueDto[];
  listOccupation: MuDomainValueDto[] = [];

  constructor(private dialogRef: MatDialogRef<ClientDlgComponent>,
              private clientService: ClientService,
              private domainService: DomainService,
              private notiflixService: NotiflixService,
              @Inject(MAT_DIALOG_DATA) public data: any) {  
    this.clientDto = <RcClientDto> this.data.model;
    this.titulo = this.data.titulo;
    this.accion = this.data.accion;
    this.inicializarForm(this.clientDto);


    
  }



  ngOnInit(): void {
    this.listDomainGender();
    this.listDomainCategory();
    this.listDomainStateCivil();
    this.listDomainOccupationClient();
  }

  inicializarForm(dto: RcClientDto): void{
    this.occupationFC = new FormControl(dto ? dto.occupation : null, [Validators.required]);
    this.formGroup = new FormGroup({
      id: new FormControl(dto ? dto.id : null), 
      dni: new FormControl(dto ? dto.dni : null, [Validators.required]),
      names: new FormControl(dto ? dto.names : null, [Validators.required]),
      lastnames: new FormControl(dto ? dto.lastnames : null, [Validators.required]),

      birthdate: new FormControl(dto ? moment(dto.birthdate, 'YYYY-MM-DD').toDate() : null, [Validators.required]),
      gender: new FormControl(dto ? dto.gender : null, [Validators.required]),
      civilStatus: new FormControl(dto ? dto.civilStatus : null, [Validators.required]),
      phone: new FormControl(dto ? dto.phone : null, [Validators.required]),

      email: new FormControl(dto ? dto.email : null, [Validators.email]),
      incomeMonth: new FormControl(dto ? dto.incomeMonth : null, [Validators.required]),
      category: new FormControl(dto ? dto.category : null, [Validators.required]),
      occupation: this.occupationFC,
      //occupation: new FormControl(dto ? dto.occupation : null, [Validators.required]),
      //occupation: this.occupationFC,
      addressHome: new FormControl(dto ? dto.addressHome : null, [Validators.required]),
      addressBusiness: new FormControl(dto ? dto.addressBusiness : null),

      comment: new FormControl(dto ? dto.comment : null),
    });
  }


  saveClient(){
    if (this.formGroup.valid){
      this.clientService.save(this.formGroup.value).subscribe(
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
      this.clientService.update(this.formGroup.value).subscribe(
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

  listDomainGender(): void{
    this.domainService.listByFilter(ConstantsDomain.GENDER).subscribe(
      response => {
          this.listGender = response;
    });
  }

  listDomainCategory(): void{
    this.domainService.listByFilter(ConstantsDomain.CATEGORIA_CLIENTE).subscribe(
      response => {
          this.listCategory = response;
    });
  }

  listDomainStateCivil(): void{
    this.domainService.listByFilter(ConstantsDomain.STATE_CIVIL).subscribe(
      response => {
          this.listStateCivil = response;
          console.log(response);
    });
  }


  listDomainOccupationClient(): void{
    this.domainService.listByFilter(ConstantsDomain.OCCUPATION).subscribe(
      response => {
          this.listOccupation = response;
          this.inicioFiltroOccupation();
          console.log(response);

    });
  }

  inicioFiltroOccupation(): void{
    this.filterdOptionOccupation = [...this.listOccupation];
    this.occupationFC.valueChanges.subscribe((data) => {
      this.filterdOptionOccupation = this.filtrarOcupaciones(data);
    })
  }

  filtrarOcupaciones(value: string): MuDomainValueDto[] {
    const filterValue = value ? value.toLowerCase() : '';
    return this.listOccupation.filter(option => option.description.toLowerCase().includes(filterValue));
  }

  formatNumber(event: any) {
    let value = event.target.value;
    // Reemplazar comas por puntos
    value = value.replace(',', '.');
    // Permitir solo números y un solo punto decimal
    value = value.replace(/[^0-9.]/g, '').replace(/(\..*)\./g, '$1');
    event.target.value = value;
    this.formGroup.get('incomeMonth')?.setValue(value);
  }

}
