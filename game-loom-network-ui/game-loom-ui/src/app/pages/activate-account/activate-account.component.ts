import { Component, OnInit } from '@angular/core'
import { Router } from '@angular/router';
import { AuthenticationService } from 'src/app/services/services';

@Component({
  selector: 'app-activate-account',
  templateUrl: './activate-account.component.html',
  styleUrls: ['./activate-account.component.scss']
})
export class ActivateAccountComponent {
message: string = 'Your account has been activated successfully. You can now login to your account.'
isOk: boolean = true;
submitted: boolean = false;

constructor(
  private authService: AuthenticationService,
  private router: Router
) { }

}
