import { Component, OnInit } from '@angular/core';
import { AuthenticationRequest } from 'src/app/services/models';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent {
login() {
throw new Error('Method not implemented.');
}
register() {
throw new Error('Method not implemented.');
}

  constructor() { }

  authRequest: AuthenticationRequest = {email:"", password:""}
  errorMsg:Array<string> = [];

}
