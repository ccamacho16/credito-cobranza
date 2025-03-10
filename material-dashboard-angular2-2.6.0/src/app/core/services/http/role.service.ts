import { HttpClient, HttpParams, HttpResponse } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Router } from "@angular/router";
import { ConstantsService } from "app/core/constants";
import { CodeDescriptionDto } from "app/core/models/select/code-description-dto";
import { MenuSelectDto } from "app/core/models/select/menu-select-dto";
import { environment } from "environments/environment";
import { Observable } from "rxjs";

@Injectable({
    providedIn: 'root'
})
export class RoleService {

    constructor(private httpClient: HttpClient, private router: Router) { 
    }


    listForSelect(): Observable<CodeDescriptionDto[]>{
        const url = environment.apiUrl+ ConstantsService.ROL +`/list_for_selection`;
        return this.httpClient.get<CodeDescriptionDto[]>(url, {});
    }

    listMenuBasic(idRol: number): Observable<MenuSelectDto[]>{
        const url = environment.apiUrl+ ConstantsService.ROL +`/list_menu_basic/${idRol}`;
        return this.httpClient.get<MenuSelectDto[]>(url, {});
    }

    listNameMenuByRole(nameRol: string): Observable<string[]>{
        const url = environment.apiUrl+ ConstantsService.ROL +`/list_name_menu_by_role/${nameRol}`;
        return this.httpClient.get<string[]>(url, {});
    }

    saveListMenusRol(idListMenus: number[], idRole: number): Observable<HttpResponse<any>> {
        const url = environment.apiUrl+ ConstantsService.ROL +`/save-list-menus-rol`;
        const params = new HttpParams().set('idRole', idRole.toString());
        return this.httpClient.post<HttpResponse<any>>(url, idListMenus,{params, observe:'response'})
    }
}