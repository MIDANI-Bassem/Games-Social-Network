import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AuthenticationRequest } from 'src/app/services/models';
import { AuthenticationService } from 'src/app/services/services';
import { TokenService } from 'src/app/services/token/token.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent {

  constructor(
    private router: Router,
    private authService: AuthenticationService,
    private tokenService: TokenService
  ) { }

  authRequest: AuthenticationRequest = {email:"", password:""}
  errorMsg:Array<string> = [];
  /*** 
  login() {
    this.errorMsg = [];
    this.authService.authenticate({
      body: this.authRequest
    }).subscribe({
      next: (response) => {
        console.log(response);
        this.tokenService.setToken(response.token as string);
        this.router.navigate(['dashboard']);
      },
      error: (err) => {
        if (err.error instanceof Blob) {
          const reader = new FileReader();
          reader.onload = () => {
            try {
              const errorJson = JSON.parse(reader.result as string);
              console.error(errorJson);
              this.errorMsg = errorJson.validationErrors || [];
            } catch (e) {
              console.error('Erreur lors du parsing du Blob:', e);
            }
          };
          reader.readAsText(err.error); // Convertir le Blob en texte
        } else {
          console.error(err.error);
        }
      }
    });
  }
    */
  login() {
    this.errorMsg = [];
    this.authService.authenticate({
      body: this.authRequest
    }).subscribe({
      next: (res) => {
        this.tokenService.setToken(res.token as string);
        this.router.navigate(['home']);
      },
      error: (err) => {
        console.log(err.error);
        if (err.error.validationErrors) {
          this.errorMsg = err.error.validationErrors;
        } else {
          this.errorMsg.push(err.error.errorMsg);
        }
      }
    });
  }
  register() {
    this.router.navigate(['register']);
    }
    
}
