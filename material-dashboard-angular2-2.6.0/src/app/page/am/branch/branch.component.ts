import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { BranchService } from 'app/core/services/http/branch.service';
import { NotiflixService } from 'app/core/services/http/notiflix.service';
import { ClicComponent } from 'app/core/utils/clic-component';
import { Page } from 'app/core/utils/paginator/page';
import { AmBranchDto } from '../../../core/models/am-branch-dto';
import { ConstantsAccion, ConstantsMessage } from 'app/core/constants';
import { BranchDlgComponent } from './branch-dlg/branch-dlg.component';

@Component({
  selector: 'app-branch',
  templateUrl: './branch.component.html',
  styleUrls: ['./branch.component.scss']
})
export class BranchComponent extends ClicComponent implements OnInit {

  listBranch: any[] = [];
  textBuscar: string = '';

  constructor(private branchService: BranchService, 
              private notiflixService: NotiflixService,
              public dialog: MatDialog) { 
    super();              
  }

  ngOnInit(): void {
    this.loadBranchs();
  }

  loadBranchs(): void {
    this.branchService.listByFilter(this.textBuscar, this.pageControl.number).subscribe(
      response => {
        this.pageControl = response;
      }, error => {
        console.error('Error al obtener Sucursales', error);
      });
  }

  disable(row: AmBranchDto){
        this.notiflixService.showConfirm(
          'Anular Sucursal',
          '¿Estás seguro de anular la Sucursal?',
          () => {
            this.branchService.delete(row.id).subscribe(
              response => {
                this.notiflixService.showSuccess(ConstantsMessage.MENSAJE_ANULAR);
                this.textBuscar = '';
                this.loadBranchs();
              },
              error => {
                console.error('Error al anular la sucursal', error);
              })
          },
          () => console.log('Cancelado')
        );
    }
  
    openDialogCreate(): void{
      this.dialog.open(BranchDlgComponent, {
        maxHeight: (window.innerHeight - 50) + 'px',
        width: '70%',
        panelClass: ['zero-padding'],
        data: {
          model:null,
          accion: ConstantsAccion.CREATE,
          titulo: 'Nueva Sucursal'
        }
      })
      .afterClosed().subscribe( (response: boolean) => {
        if(response){
          this.loadBranchs();
        }
      })
    }
  
    openDialogUpdate(branchDto: AmBranchDto): void{
      this.dialog.open(BranchDlgComponent, {
        maxHeight: (window.innerHeight - 50) + 'px',
        width: '70%',
        panelClass: ['zero-padding'],
        data: {
          model: branchDto,
          accion: ConstantsAccion.UPADTE,
          titulo: 'Actualizar Sucursal'
        }
      })
      .afterClosed().subscribe( (response: boolean) => {
        if(response){
          this.loadBranchs();
        }
      }) 
    }
  
    /* openDialogView(clientDto: RcClientDto): void{
      console.log("openDialogView", clientDto);
      this.dialog.open(ClientViewDlgComponent, {
        maxHeight: (window.innerHeight - 50) + 'px',
        width: '30%',
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
          { label: "Teléfono", value: clientDto.phone },
          { label: "Email", value: clientDto.email },
          { label: "Dirección", value: clientDto.address },
          { label: "Categoria", value: clientDto.category },
        ]
      return rows;
    } */





  applyFilter(filterValue: string) {
      this.textBuscar = filterValue;
      this.pageControl.number = 0;
      this.loadBranchs();
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
         this.loadBranchs();
      }
     }
   
     nextPage() {
       if (this.pageControl.number < this.pageControl.totalPages - 1) {
           this.pageControl.number++;
           this.loadBranchs();
       }
     }
   
     setPage(pageInfo: Page) {
      this.pageControl.number = pageInfo.offset;
      this.loadBranchs();
     }

}
