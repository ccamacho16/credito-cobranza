import { Component, OnInit } from '@angular/core';
import { FormControl } from '@angular/forms';
import { MatDialog } from '@angular/material/dialog';
import { RcClientDto } from 'app/core/models/rc-client-dto';
import { ClientService } from 'app/core/services/http/client.service';
import { NotiflixService } from 'app/core/services/http/notiflix.service';
import { ClicComponent } from 'app/core/utils/clic-component';
import { Page } from 'app/core/utils/paginator/page';
import { ClientDlgComponent } from './client-dlg/client-dlg.component';
import { ClientViewDlgComponent } from './client-view-dlg/client-view-dlg.component';
import { RowView } from 'app/core/models/row-view';
import { ConstantsAccion, ConstantsMessage } from 'app/core/constants';

@Component({
  selector: 'app-client',
  templateUrl: './client.component.html',
  styleUrls: ['./client.component.scss']
})
export class ClientComponent extends ClicComponent implements OnInit {

  listClient: any[] = [];
  textBuscar: string = '';

  constructor(private clientService: ClientService, 
              private notiflixService: NotiflixService,
              public dialog: MatDialog) { 
    super();
  }

  ngOnInit(): void {
    this.loadClients();
  }

  loadClients(): void {
    this.clientService.listByFilter(this.textBuscar, this.pageControl.number).subscribe(
      response => {
        this.pageControl = response;
      }, error => {
        console.error('Error al obtener clientes', error);
      });
  }

  disable(row: RcClientDto){
      this.notiflixService.showConfirm(
        'Anular Cliente',
        '¿Estás seguro de anular el Cliente?',
        () => {
          this.clientService.delete(row.id).subscribe(
            response => {
              this.notiflixService.showSuccess(ConstantsMessage.MENSAJE_ANULAR);
              this.textBuscar = '';
              this.loadClients();
            },
            error => {
              console.error('Error al anular el cliente', error);
            })
        },
        () => console.log('Cancelado')
      );
  }

  openDialogCreate(): void{
    this.dialog.open(ClientDlgComponent, {
      maxHeight: (window.innerHeight - 50) + 'px',
      width: '70%',
      panelClass: ['zero-padding'],
      data: {
        model:null,
        accion: ConstantsAccion.CREATE,
        titulo: 'Nuevo Cliente'
      }
    })
    .afterClosed().subscribe( (response: boolean) => {
      if(response){
        this.loadClients();
      }
    })
  }

  openDialogUpdate(clientDto: RcClientDto): void{
    this.dialog.open(ClientDlgComponent, {
      maxHeight: (window.innerHeight - 50) + 'px',
      width: '70%',
      panelClass: ['zero-padding'],
      data: {
        model: clientDto,
        accion: ConstantsAccion.UPADTE,
        titulo: 'Actualizar Cliente'
      }
    })
    .afterClosed().subscribe( (response: boolean) => {
      if(response){
        this.loadClients();
      }
    })
  }

  openDialogView(clientDto: RcClientDto): void{
    console.log("openDialogView", clientDto);
    this.dialog.open(ClientViewDlgComponent, {
      maxHeight: (window.innerHeight - 50) + 'px',
      width: '35%',
      panelClass: ['zero-padding'],
      data: {
        rows: this.buildShowInfoData(clientDto),
        accion: ConstantsAccion.VIEW,
        titulo: 'Visualizar Cliente'
      }
    })
  }

  protected buildShowInfoData = (clientDto: RcClientDto): RowView[] => {
    const rows: RowView[] =
      [
        { label: "Id", value: clientDto.id.toString() },
        { label: "DNI", value: clientDto.dni },
        { label: "Nombre", value: clientDto.names },
        { label: "Apellido", value: clientDto.lastnames },
        { label: "Fecha de Nacimiento", value: clientDto.birthdate?.toString() },
        { label: "Género", value: clientDto.gender },
        { label: "Estado Civil", value: clientDto.civilStatus },
        { label: "Teléfono", value: clientDto.phone },
        { label: "Email", value: clientDto.email },
        { label: "Ingreso Mensual", value: clientDto.incomeMonth.toString() },
        { label: "Dirección Domicilio", value: clientDto.addressHome },
        { label: "Dirección Negocio", value: clientDto.addressBusiness },
        { label: "Categoría", value: clientDto.category },
        { label: "Ocupación", value: clientDto.occupation },
        { label: "Comentario", value: clientDto.comment },
      ]
    return rows;
  }

  applyFilter(filterValue: string) {
    this.textBuscar = filterValue;
    this.pageControl.number = 0;
    this.loadClients();
  }

  public flex: number;
  public flexInput: number;
  onXsScreen() {
    this.flex = 100;
    this.flexInput = 100;
  }

  onSmScreen() {
    this.flex = 25;
    this.flexInput = 75;
  }

  onMdScreen() {
    this.flex = 15;
    this.flexInput = 33;
  }

  onLgScreen() {
    this.flex = 15;
    this.flexInput = 33;
  }

  onGtLgScreen() {
    this.flex = 10;
    this.flexInput = 33;
  }

  previousPage() {
   if (this.pageControl.number > 0) {
      this.pageControl.number--;
      this.loadClients();
   }
  }

  nextPage() {
    if (this.pageControl.number < this.pageControl.totalPages - 1) {
        this.pageControl.number++;
        this.loadClients();
    }
  }

  setPage(pageInfo: Page) {
   this.pageControl.number = pageInfo.offset;
   this.loadClients();
  }
}
