import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Authentication } from '../shared/authentication';
import { AuthenticationService } from 'app/core/services/http/authentication.service'; 
import { Router } from '@angular/router';
import { NotiflixService } from 'app/core/services/http/notiflix.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {

  loginForm!: FormGroup;

  constructor(private authenticationService: AuthenticationService,
              private router: Router,
              private notiflixService: NotiflixService
  ) { }

  ngOnInit(): void {
    this.loginForm = new FormGroup({
      username: new FormControl(null, [Validators.required]),
      password: new FormControl(null, [Validators.required])
    });
  }

  loginFormSubmit(){
    const value: Authentication = this.loginForm.value;
    if (this.loginForm.valid){
      this.authenticationService.requestStorageLogin(value).subscribe(
        data => {
          const token = data.body?.token;
          const userName = data.body?.username;
          const role = data.body?.role;
          if (token){
            this.authenticationService.logout();
            this.authenticationService.saveToken(token);
            this.authenticationService.saveUserName(userName);
            this.authenticationService.saveRole(role);
            this.authenticationService.loadPermissions();
            this.router.navigate(['dashboard']);
            console.log("a5")
          }
        },
        error => {
          this.notiflixService.showError("Credenciales incorrectas. Verifica tu usuario y contraseña.");
        }
      )
    } 
  }
}
