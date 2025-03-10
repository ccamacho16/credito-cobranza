import { NgModule } from '@angular/core';
import { CommonModule, } from '@angular/common';
import { BrowserModule  } from '@angular/platform-browser';
import { Routes, RouterModule } from '@angular/router';

import { AdminLayoutComponent } from './layouts/admin-layout/admin-layout.component';
import { AuthGuard } from './core/services/util/auth-guard.guard';


const routes: Routes = [
  {  // Despliega el contenido del login
    path: '',
    redirectTo: 'login',
    pathMatch: 'full',
  },
  {  // Ruta para el login
    path: 'login',
    canActivate: [AuthGuard],
    loadChildren: () => import('./authentication/authentication.module').then(m => m.AuthenticationModule),
  },
  {  // Despliega el menú, la cabecera y otros.
    path: '',
    component: AdminLayoutComponent,
    canActivate: [AuthGuard],
    children: [
      {
        path: '',
        loadChildren: () => import('./layouts/admin-layout/admin-layout.module').then(m => m.AdminLayoutModule),
      }
    ],
    
  },
  { path: '**', redirectTo: '404' }
];


@NgModule({
  imports: [
    CommonModule,
    BrowserModule,
    RouterModule.forRoot(routes,{
       useHash: true
    })
  ],
  exports: [
  ],
})
export class AppRoutingModule { }
