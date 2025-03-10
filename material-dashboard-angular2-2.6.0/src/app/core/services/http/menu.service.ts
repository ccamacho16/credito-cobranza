import { HttpClient, HttpParams, HttpResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { environment } from 'environments/environment';
import { Observable } from 'rxjs';
import { Menu } from 'app/core/models/menu';
import { ConstantsService } from 'app/core/constants';

@Injectable({
  providedIn: 'root'
})
export class MenuService {

  constructor(private httpClient: HttpClient, private router: Router) { 

  }

  requestMenusUser(username: String): Observable<Menu[]>{
    //const url = environment.apiUrl+ 'menu/list-menus-by-user/admin';
    const url = environment.apiUrl+ ConstantsService.MENU +`/list-menus-by-user/${username}`;
    return this.httpClient.get<Menu[]>(url);
  }

  getTextAccess(urlMenu: string): Observable<string>{
    const url = environment.apiUrl+ ConstantsService.MENU +`/text-access`;
    const params = new HttpParams().set('url', urlMenu);
    return this.httpClient.get<string>(url, {params, responseType: 'text' as 'json' });
  }
}