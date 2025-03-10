import { HttpClient, HttpParams, HttpResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { environment } from 'environments/environment';
import { Observable } from 'rxjs';
import { ConstantsService } from 'app/core/constants';
import { MuUserDto } from 'app/core/models/mu-user-dto';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor(private httpClient: HttpClient, private router: Router) { 

  }

   listByFilter(filter: string, page: number = 0): Observable<any>{

    const params = new HttpParams()
      .set('page', page.toString())
      .set('filter', filter);

    const url = environment.apiUrl+ ConstantsService.USER +`/list_by_filter`;
    return this.httpClient.get(url, {params});
  }

  delete(id: number): Observable<HttpResponse<any>> {
    const url = environment.apiUrl+ ConstantsService.USER + `/delete/${id}`;
    return this.httpClient.delete<HttpResponse<any>>(url, {observe: 'response'});
  }

  save(userDto: MuUserDto): Observable<HttpResponse<any>> {
    const url = environment.apiUrl+ ConstantsService.USER + `/save`;
    return this.httpClient.post<HttpResponse<any>>(url, JSON.stringify(userDto), {});
  }

  update(userDto: MuUserDto): Observable<HttpResponse<any>> {
    const url = environment.apiUrl+ ConstantsService.USER + `/update`;
    return this.httpClient.put<HttpResponse<any>>(url, JSON.stringify(userDto), {});
  }
 
}