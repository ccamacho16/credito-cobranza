import { HttpClient, HttpParams, HttpResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { environment } from 'environments/environment';
import { Observable } from 'rxjs';
import { ConstantsService } from 'app/core/constants';
import { RcClientDto } from 'app/core/models/rc-client-dto';
import { MuDomainValueDto } from 'app/core/models/mu-domain-value-dto';

@Injectable({
  providedIn: 'root'
})
export class DomainService {

  constructor(private httpClient: HttpClient, private router: Router) { 

  }

   listByFilter(domain: string): Observable<MuDomainValueDto[]>{
    const url = environment.apiUrl+ ConstantsService.DOMAIN +`/list-value-by-domain?idDomain=${domain}`;
    return this.httpClient.get<MuDomainValueDto[]>(url, {});
  }


 
}