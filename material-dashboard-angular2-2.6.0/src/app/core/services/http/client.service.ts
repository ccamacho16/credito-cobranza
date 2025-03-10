import { HttpClient, HttpParams, HttpResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { environment } from 'environments/environment';
import { Observable } from 'rxjs';
import { ConstantsService } from 'app/core/constants';
import { RcClientDto } from 'app/core/models/rc-client-dto';

@Injectable({
  providedIn: 'root'
})
export class ClientService {

  constructor(private httpClient: HttpClient, private router: Router) { 

  }

   listByFilter(filter: string, page: number = 0): Observable<any>{

    const params = new HttpParams()
      .set('page', page.toString())
      .set('filter', filter);

    const url = environment.apiUrl+ ConstantsService.CLIENT +`/list_by_filter`;
    return this.httpClient.get(url, {params});
  }

  delete(id: number): Observable<HttpResponse<any>> {
    const url = environment.apiUrl+ ConstantsService.CLIENT + `/delete/${id}`;
    return this.httpClient.delete<HttpResponse<any>>(url, {observe: 'response'});
  }

  save(clientDto: RcClientDto): Observable<HttpResponse<any>> {
    const url = environment.apiUrl+ ConstantsService.CLIENT + `/save`;
    return this.httpClient.post<HttpResponse<any>>(url, JSON.stringify(clientDto), {});
  }

  update(clientDto: RcClientDto): Observable<HttpResponse<any>> {
    const url = environment.apiUrl+ ConstantsService.CLIENT + `/update`;
    return this.httpClient.put<HttpResponse<any>>(url, JSON.stringify(clientDto), {});
  }
 
}