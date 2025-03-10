import { NgModule } from '@angular/core';
import { Routes } from '@angular/router';
import { LoginComponent } from './login/login.component';
import { ErrorComponent } from './error/error.component';

export const AuthenticationRoutes: Routes = [
  { path: '', component: LoginComponent }, // Login estará en /login
  { path: '404', component: ErrorComponent } // Página de error
];
/*export const AuthenticationRoutes: Routes = [
  {
    path: '',
    children: [
      {
        path: 'login',
        component: LoginComponent
      },
      {
        path: '404',
        component: ErrorComponent
      }
    ]
  }
];*/