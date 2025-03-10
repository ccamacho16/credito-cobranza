import { Component, HostListener} from '@angular/core';
import { AuthenticationService } from './core/services/http/authentication.service';


@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  constructor() {}

  ngOnInit() {

  }

  // TODO: BORRA TOKEN CUANDO SE CIERRA LA PESTAÑA DE LA APLICACION
/*   @HostListener('window:beforeunload', ['$event'])
  unloadHandler(event: Event) {
    localStorage.removeItem('authToken');
  } */
}
