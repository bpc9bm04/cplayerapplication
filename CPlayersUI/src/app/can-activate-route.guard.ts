import { Injectable } from '@angular/core';
import { CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot } from '@angular/router';
import { Observable } from 'rxjs/Observable';
import { AuthenticationService } from './modules/authentication.service';
import { RouterService } from './modules/router.service';
import { Console } from '@angular/core/src/console';
//import { MovieRouterModule } from './modules/movie/movie-router.module';
@Injectable()
export class CanActivateRouteGuard implements CanActivate {

  constructor(private authService: AuthenticationService,
    private routerService: RouterService) { }

  canActivate(
    next: ActivatedRouteSnapshot,
    state: RouterStateSnapshot): Observable<boolean> | Promise<boolean> | boolean {
    return this.authService.isUserAuthenticated(this.authService.getBearerToken())
      .then((response) => {
        if (!response) {
          this.routerService.routeToLogin();
        }
        console.log(response);
        
        return response;
      });
  }

}
