import { Component, OnInit } from '@angular/core'
import { Router } from '@angular/router';
import { AuthenticationService } from 'src/app/services/services';


@Component({
  selector: 'app-activate-account',
  templateUrl: './activate-account.component.html',
  styleUrls: ['./activate-account.component.scss']
})
export class ActivateAccountComponent {

  message: string = '';
  isOk: boolean = true;
  submitted: boolean = false;

  constructor(
    private authService: AuthenticationService,
    private router: Router
  ) { }

  onCodeCompleted(token: string): void {
    this.confirmAccount(token);
    this.submitted = true; // Exemple de logique supplémentaire
  }
  confirmAccount(token: string) {
    this.authService.activateAccount({token}).subscribe({
      next: () => {
        this.message = 'Votre compte a été activé avec succès';
        this.submitted = true;
        this.isOk = true;
      },
      error: () => {
        this.message = 'Une erreur est survenue lors de l\'activation de votre compte';
        this.submitted = true;
        this.isOk = false;
        
      }
    }
    )
  }
  onLogin(): void {
    this.router.navigate(['login']);
  }
}
