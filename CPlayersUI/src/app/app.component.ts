import { Component } from '@angular/core';
import { AuthenticationService } from './modules/authentication.service';
import { RouterService } from './modules/router.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'C Player Application';
  showLogout : boolean;
  constructor(private authService: AuthenticationService , private routerService: RouterService) { }
  ngAfterViewChecked(){
    //delay(0);
    setTimeout(() => {
      if(localStorage.getItem('userId')!=null){
        this.showLogout = true;
      }
  });
    
  }
   
  ngOnInit() {
    this.showLogout = false;
  }
  logout() {
    this.showLogout = false;
    localStorage.removeItem('userId');
    localStorage.removeItem('bearerToken');
    this.routerService.routeToLogin();
  }
}
