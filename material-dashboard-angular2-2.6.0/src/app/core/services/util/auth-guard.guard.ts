import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot, UrlTree } from '@angular/router';
import { Observable } from 'rxjs';
import { AuthenticationService } from '../http/authentication.service';
import { RoleService } from '../http/role.service';

@Injectable({
  providedIn: 'root'
})
export class AuthGuard implements CanActivate {

  constructor(private authenticationService: AuthenticationService,
              private roleService: RoleService,
                private router: Router) {}

  canActivate(  // Este el Guardian que protege las rutas.
      route: ActivatedRouteSnapshot,
      state: RouterStateSnapshot): Observable<boolean | UrlTree> | Promise<boolean | UrlTree> | boolean | UrlTree {

      if (this.authenticationService.isAuthenticated()){
            if (state.url === '/login') {
              this.router.navigate(['/dashboard']); 
              return false;  
            }
            const allowedRoles = this.authenticationService.getRolePermissions();
            console.log("allowedRoles",allowedRoles);
            const currentRoute = state.url;
            console.log("currentRoute",currentRoute);
            if (allowedRoles && !allowedRoles.includes(currentRoute)) {
              return false;
            }
            return true;
      } else{
            if (state.url !== '/login') {
              this.router.navigate(['/login']);
              return false;
            }
            return true;
      }
  }

  
}

/*         if (!this.authenticationService.isAuthenticated()) {
          if (state.url !== '/login') {
            this.router.navigate(['/login']);
          }
          return false;
        }
    
        if (state.url === '/login') {
          this.router.navigate(['/dashboard']);
          return false;
        } */
    
        /*const userRole = this.authenticationService.getRole(); 
        const allowedRoles = route.data['roles'] as string[];

        console.log("roleeee", userRole);*/
    
        /*if (allowedRoles && !allowedRoles.includes(userRole)) {
          this.router.navigate(['/**']);
          //return this.router.createUrlTree(['/unauthorized']);
        }*/
    
        
        //return true;
