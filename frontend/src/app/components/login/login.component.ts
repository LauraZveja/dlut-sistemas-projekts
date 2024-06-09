import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from '../../services/auth/auth.service';
import { UserService } from '../../services/user/user.service';


@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {
  username: string = '';
  password: string = '';

  constructor(private router: Router, private authService: AuthService, private userService: UserService ) {}

  onSubmit() {
    this.authService.login(this.username, this.password).subscribe(response => {
      this.userService.setUserToken(response);
      this.router.navigate(['/home']);
      console.log ("user data!" , this.userService.getUserToken());
    }, error => {
      console.error('Login failed', error);
    });
  }
}