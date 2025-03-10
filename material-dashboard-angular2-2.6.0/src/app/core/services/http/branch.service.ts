import { HttpClient, HttpParams, HttpResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { environment } from 'environments/environment';
import { Observable } from 'rxjs';
import { Menu } from 'app/core/models/menu';
import { ConstantsService } from 'app/core/constants';
import { RcClientDto } from 'app/core/models/rc-client-dto';
import { AmBranchDto } from 'app/core/models/am-branch-dto';
import { CodeDescriptionDto } from 'app/core/models/select/code-description-dto';

@Injectable({
  providedIn: 'root'
})
export class BranchService {

  constructor(private httpClient: HttpClient, private router: Router) { 

  }

   listByFilter(filter: string, page: number = 0): Observable<any>{

    const params = new HttpParams()
      .set('page', page.toString())
      .set('filter', filter);

    const url = environment.apiUrl+ ConstantsService.BRANCH +`/list_by_filter`;
    return this.httpClient.get(url, {params});
  }

  listForSelect(): Observable<CodeDescriptionDto[]>{
    const url = environment.apiUrl+ ConstantsService.BRANCH +`/list_for_selection`;
    return this.httpClient.get<CodeDescriptionDto[]>(url, {});
  }

  delete(id: number): Observable<HttpResponse<any>> {
    const url = environment.apiUrl+ ConstantsService.BRANCH + `/delete/${id}`;
    return this.httpClient.delete<HttpResponse<any>>(url, {observe: 'response'});
  }

  save(branchDto: AmBranchDto): Observable<HttpResponse<any>> {
    const url = environment.apiUrl+ ConstantsService.BRANCH + `/save`;
    return this.httpClient.post<HttpResponse<any>>(url, JSON.stringify(branchDto), {});
  }

  update(branchDto: AmBranchDto): Observable<HttpResponse<any>> {
    const url = environment.apiUrl+ ConstantsService.BRANCH + `/update`;
    return this.httpClient.put<HttpResponse<any>>(url, JSON.stringify(branchDto), {});
  }
 
}