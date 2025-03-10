import { Component, OnInit } from '@angular/core';
import { MatSelectionListChange } from '@angular/material/list';
import { id } from '@swimlane/ngx-datatable';
import { ConstantsMessage } from 'app/core/constants';
import { CodeDescriptionDto } from 'app/core/models/select/code-description-dto';
import { MenuSelectDto } from 'app/core/models/select/menu-select-dto';
import { NotiflixService } from 'app/core/services/http/notiflix.service';
import { RoleService } from 'app/core/services/http/role.service';

@Component({
  selector: 'app-menu-rol',
  templateUrl: './menu-rol.component.html',
  styleUrls: ['./menu-rol.component.scss']
})
export class MenuRolComponent implements OnInit {

  listRol: CodeDescriptionDto[];
  listMenu: MenuSelectDto[];

  selectedRol: number = 0;

  selectedMenus: number[] = [];

  constructor(private roleService: RoleService,
              private notiflixService: NotiflixService,
  ) { }

  ngOnInit(): void {
    this.loadListRol();
  }

  loadListRol(): void{
    this.roleService.listForSelect().subscribe(
      response => {
          this.listRol = response;
    });
  }

  loadListMenuRol(idRole: number): void{
    this.listMenu = null;
    this.roleService.listMenuBasic(idRole).subscribe(
      response => {
        this.listMenu = response;
        this.selectedMenus = this.listMenu
        .filter(menu => menu.select)
        .map(menu => menu.id);
      }
    );
  }

  getIndentation(menu: MenuSelectDto): number {
    return menu.idFather ? 20 : 0;
  }

  onSelectionChange(event: any): void {
    this.loadListMenuRol(event.value);
  }

  onMenuSelectionChange(event: MatSelectionListChange) {
    if (event.options.length > 0) {
      const selectedOption = event.options[0]; // Tomamos el primer cambio detectado
      const selectedMenu = this.listMenu.find(m => m.id === selectedOption.value);
      if (selectedMenu) {
        if (!selectedMenu.idFather) {
          this.toggleChildren(selectedMenu.id, selectedOption.selected); //altera los hijos
        } else {
          this.updateParentSelection(selectedMenu.idFather); // actualiza la seleccion del Padre. 
        }
      }
    }
    this.updateListMenuBasedOnSelection();
  }

  toggleChildren(parentId: number, isSelected: boolean) {
    this.listMenu.forEach(menu => {
          if (menu.idFather === parentId) {
                if (isSelected) {
                    if (!this.selectedMenus.includes(menu.id)) {
                      this.selectedMenus.push(menu.id);
                    }
                } else {
                    this.selectedMenus = this.selectedMenus.filter(id => id !== menu.id);
                }
          }
    });
    this.updateParentSelection(parentId);
  }

  updateParentSelection(parentId: number) {
    const parentMenu = this.listMenu.find(menu => menu.id === parentId);
    if (parentMenu) {
      const hasSelectedChildren = this.listMenu.some(menu => menu.idFather === parentId && this.selectedMenus.includes(menu.id));
      if (hasSelectedChildren) {
        if (!this.selectedMenus.includes(parentMenu.id)) {
          this.selectedMenus.push(parentMenu.id);
        }
      } else {
        this.selectedMenus = this.selectedMenus.filter(id => id !== parentMenu.id);
      }
    }
  }

  updateListMenuBasedOnSelection(): void {
    this.listMenu.forEach(menu => {
      menu.select = this.selectedMenus.includes(menu.id);
    });
  }

  save(): void{
    this.notiflixService.showConfirm(
      'Guardar Cambios',
      '¿Estás seguro de realizar los Cambios?',
      () => {
        this.roleService.saveListMenusRol(this.selectedMenus, this.selectedRol).subscribe(
          response => {
              this.notiflixService.showSuccess(ConstantsMessage.MENSAJE_GUARDAR);
          }
        );
      },
      () => console.log('Cancelado')
    );
    
  }
}
