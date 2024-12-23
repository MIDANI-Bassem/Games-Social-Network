import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { RegistrationRequest } from 'src/app/services/models';
import { AuthenticationService } from 'src/app/services/services';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.scss']
})
export class RegisterComponent  {

  registerRequest: RegistrationRequest = {
    email: '',
    firstName: '',
    lastName: '',
    password: ''
  }
  errorMsg: Array<string> = [];

  constructor(
    private router:Router,
    private authService: AuthenticationService

  ) { }


  login() {
    this.router.navigate(['login']);
  }
  register() {
    this.errorMsg = [];
    this.authService.register({
      body: this.registerRequest
    }).subscribe({
      next: (response) => {
        this.router.navigate(['activate-account']);
      },
      error: (error) => {
        this.errorMsg = error.error.validationErrors;
      }
    });
  }
}
