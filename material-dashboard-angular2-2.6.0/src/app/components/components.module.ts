import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';

import { FooterComponent } from './footer/footer.component';
import { NavbarComponent } from './navbar/navbar.component';
import { SidebarComponent } from './sidebar/sidebar.component';
import { MatIconModule } from "@angular/material/icon";
//import { MatMenuModule } from '@angular/material/menu';
//import {MatToolbarModule } from '@angular/material/toolbar';

@NgModule({
  imports: [
    CommonModule,
    RouterModule,
    MatIconModule,

  ],
  declarations: [
    FooterComponent,
    NavbarComponent,
    SidebarComponent,
  ],
  exports: [
    FooterComponent,
    NavbarComponent,
    SidebarComponent
  ]
})
export class ComponentsModule { }
