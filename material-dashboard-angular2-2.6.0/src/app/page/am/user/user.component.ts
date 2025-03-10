import { Component, OnInit } from '@angular/core';
import { UserService } from '../../../core/services/http/user.service';
import { NotiflixService } from 'app/core/services/http/notiflix.service';
import { MatDialog } from '@angular/material/dialog';
import { Page } from 'app/core/utils/paginator/page';
import { MuUserDto } from 'app/core/models/mu-user-dto';
import { ClicComponent } from 'app/core/utils/clic-component';
import { UserDlgComponent } from './user-dlg/user-dlg.component';
import { ConstantsAccion } from 'app/core/constants';
import { getUserStatusLabel } from 'app/core/models/Mapeo/user-status';
import { UserViewDlgComponent } from './user-view-dlg/user-view-dlg.component';
import { RowView } from 'app/core/models/row-view';

@Component({
  selector: 'app-user',
  templateUrl: './user.component.html',
  styleUrls: ['./user.component.scss']
})
export class UserComponent extends ClicComponent implements OnInit {

  listUser: any[] = [];
  textBuscar: string = '';
  pageControl: any;
  getUserStatusLabel = getUserStatusLabel;

  constructor(private userService: UserService,
              private notiflixService: NotiflixService,
              private dialog: MatDialog
  ) {
    super();
  }

  ngOnInit(): void {
    this.loadUsers();
  }

  loadUsers(): void {
    this.userService.listByFilter(this.textBuscar, this.pageControl.number).subscribe(
      response => {
        this.pageControl = response;
        
      }, error => {
        console.error('Error al obtener Usuarios', error);
      });
  }


  
  openDialogCreate(): void{
      this.dialog.open(UserDlgComponent, {
      maxHeight: (window.innerHeight - 50) + 'px',
      width: '70%',
      panelClass: ['zero-padding'],
      data: {
        model:null,
        accion: ConstantsAccion.CREATE,
        titulo: 'Nuevo Usuario'
      }
    })
    .afterClosed().subscribe( (response: boolean) => {
      if(response){
        this.loadUsers();
      }
    }) 
  }
  
  openDialogUpdate(userDto: MuUserDto): void{
      this.dialog.open(UserDlgComponent, {
      maxHeight: (window.innerHeight - 50) + 'px',
      width: '70%',
      panelClass: ['zero-padding'],
      data: {
        model: userDto,
        accion: ConstantsAccion.UPADTE,
        titulo: 'Actualizar Usuario'
      }
    })
    .afterClosed().subscribe( (response: boolean) => {
      if(response){
        this.loadUsers();
      }
    }) 
  }
  
  openDialogView(userDto: MuUserDto): void{
    this.dialog.open(UserViewDlgComponent, {
      maxHeight: (window.innerHeight - 50) + 'px',
      width: '30%',
      panelClass: ['zero-padding'],
      data: {
        rows: this.buildShowInfoData(userDto),
        accion: ConstantsAccion.VIEW,
        titulo: 'Visualizar Usuario'
      }
    }) 
  }
  
  protected buildShowInfoData = (userDto: MuUserDto): RowView[] => {
      const rows: RowView[] =
        [
          { label: "Id", value: userDto.id.toString() },
          { label: "Nombre", value: userDto.name },
          { label: "Usuario", value: userDto.username },
          { label: "Email", value: userDto.email },
          { label: "Estado", value: getUserStatusLabel(userDto.status) },

          { label: "Role", value: userDto.nameRole },
          { label: "Sucursal", value: userDto.nameBranch },
        ]
      return rows;
  }
 






  applyFilter(filterValue: string) {
      this.textBuscar = filterValue;
      this.pageControl.number = 0;
      this.loadUsers();
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
         this.loadUsers();
      }
     }
   
     nextPage() {
       if (this.pageControl.number < this.pageControl.totalPages - 1) {
           this.pageControl.number++;
           this.loadUsers();
       }
     }
   
     setPage(pageInfo: Page) {
      this.pageControl.number = pageInfo.offset;
      this.loadUsers();
     }

}
