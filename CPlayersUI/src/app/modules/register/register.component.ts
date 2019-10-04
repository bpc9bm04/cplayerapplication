import { Component } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';

import { AuthenticationService } from '../authentication.service';
import { RouterService } from '../router.service';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent {

  submitMessage: string;
  username = new FormControl('');
  password = new FormControl('');
  firstName = new FormControl('');
  lastName = new FormControl('');

  registerSubmit() {
    if(this.username.value == '' || this.password.value == '' || this.firstName.value == '' 
      || this.lastName.value == '') {
      this.submitMessage = 'Please enter mandatory fields';
    } else {
      const loginData = `{"userId": "${this.username.value}", "password": "${this.password.value}", "firstName": "${this.firstName.value}"
        , "lastName": "${this.lastName.value}"}`;
      this.authService.registerUser(JSON.parse(loginData)).subscribe(
        data => {
          //localStorage.setItem('regStatus', 'success');
          console.log(data);
          this.submitMessage = 'User registered successfully!';
          this.routerService.routeToLogin();
        },
        error => {
          if (typeof error.error === 'undefined') {
            this.submitMessage = error.message;
          } else {
            this.submitMessage = error.error.message;
          }
        }
      );
    }
  }
  cancel(){
    this.routerService.routeToLogin();
  }
  constructor(private authService: AuthenticationService,
    private routerService: RouterService) { }
}
