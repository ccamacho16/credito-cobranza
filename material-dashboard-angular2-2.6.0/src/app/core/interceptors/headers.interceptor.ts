import { HttpErrorResponse, HttpEvent, HttpHandler, HttpInterceptor, HttpRequest } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, throwError } from 'rxjs';
import { AuthenticationService } from '../services/http/authentication.service';
import { catchError } from 'rxjs/operators';
import { Router } from '@angular/router';

@Injectable()
export class HeadersInterceptor implements HttpInterceptor{

  constructor(private authenticationService: AuthenticationService,
              private router: Router
  ) {

   }


  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {

    

    req = this.authorization(req);

    if (req.responseType && req.responseType !== 'json') return next.handle(req);

    if (!(req.body instanceof FormData)) req = this.setCommonHeaders(req);

    return next.handle(req).pipe(
      catchError((_error: HttpErrorResponse) => {
          if (_error.status === 403){
            this.authenticationService.logout();
            this.router.navigate(['/login']);
          }
          return throwError(_error)
      })
    );

  }

  authorization(request: HttpRequest<any>): HttpRequest<any> {
    const token = this.authenticationService.getToken();
    if (token) {
      request = request.clone({
        setHeaders: {
          Authorization: `Bearer ${token}`,
        }
      });
    }
    return request;
  }

  private setCommonHeaders(request: HttpRequest<any>): HttpRequest<any> {
    return request.clone({
      setHeaders: {
        'Content-type': 'application/json',
        'Accept': 'application/json'
      },
      responseType: 'json'
    });
  }
}
