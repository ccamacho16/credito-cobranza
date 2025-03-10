import { HttpClient, HttpResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { Authentication } from 'app/authentication/shared/authentication';
import { environment } from 'environments/environment';
import { Observable } from 'rxjs';
import { HttpHeaders } from '@angular/common/http';
import { RoleService } from './role.service';

@Injectable({
  providedIn: 'root'
})
export class AuthenticationService {

  private rolePermissions: string[] = [];

  constructor(private httpClient: HttpClient,
     private router: Router,
    private roleService: RoleService) { 

  }

  requestStorageLogin(auth: Authentication): Observable<HttpResponse<any>>{
    const url = environment.apiUrl+ 'auth/login';
    return this.httpClient.post<HttpResponse<any>>(url, JSON.stringify(auth), {observe:'response'})
  }

  loadPermissions(): void{
    console.log("loadPermissions");
    const role = this.getRole();
    if (role){
      this.roleService.listNameMenuByRole(role).subscribe(response => {
        this.rolePermissions = response;
        this.rolePermissions.push('/dashboard');

        localStorage.setItem('rolePermissions', JSON.stringify(this.rolePermissions));
      });
    }
  }

  public getRolePermissions(): string[] {
    if (this.rolePermissions.length === 0) {
      const storedPermissions = localStorage.getItem('rolePermissions');
      if (storedPermissions) {
        this.rolePermissions = JSON.parse(storedPermissions);
      }
    }
    return this.rolePermissions;
  }

  // Método para obtener el token de localStorage
  getToken(): string | null {
    return localStorage.getItem('authToken');
  }

  // Método para guardar el token en localStorage
  saveToken(token: string): void {
    localStorage.setItem('authToken', token);
  }

  // Método para eliminar el token de localStorage (logout)
  logout(): void {
    localStorage.removeItem('authToken');
    localStorage.removeItem('userName');
    localStorage.removeItem('role');
  }

  // Método para verificar si el usuario está autenticado
  isAuthenticated(): boolean {
    const token = localStorage.getItem('authToken');
    return token !== null && token.length > 0;
  }

  saveUserName(userName: string): void {
    localStorage.setItem('userName', userName);
  }

  getUserName(): string | null {
    return localStorage.getItem('userName');
  }


  saveRole(role: string): void {
    localStorage.setItem('role', role);
  }
  getRole(): string | null {
    return localStorage.getItem('role');
  }
}
