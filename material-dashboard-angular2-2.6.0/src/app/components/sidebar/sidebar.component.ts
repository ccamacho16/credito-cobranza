import { Component, OnInit } from '@angular/core';
import { Menu } from 'app/core/models/menu';
import { AuthenticationService } from 'app/core/services/http/authentication.service';

import { MenuService } from 'app/core/services/http/menu.service';

declare const $: any;
declare interface RouteInfo {
    path: string;
    title: string;
    icon: string;
    class: string;
}



@Component({
  selector: 'app-sidebar',
  templateUrl: './sidebar.component.html',
  styleUrls: ['./sidebar.component.css']
})
export class SidebarComponent implements OnInit {
  menuItems: any[];
  listMenus: Menu[];
  constructor(private menuService: MenuService,
               private authenticationService: AuthenticationService
  ) { }

  ngOnInit() {
    console.log("ingresoooo");
    this.menuService.requestMenusUser(this.authenticationService.getUserName()).subscribe(
      data => {
          this.listMenus = data.map(menu => ({
            ...menu,
            isOpen: false // Inicializa isOpen en false
          }));
          //this.listMenus = data;
      }
    );
  }
  isMobileMenu() {
      if ($(window).width() > 991) {
          return false;
      }
      return true;
  };

  toggleSubMenu(menuItem: any) {
    menuItem.isOpen = !menuItem.isOpen;
  }
}
