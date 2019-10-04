import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { PlayerModule } from './modules/player/player.module';
import { RouterModule, Routes }  from '@angular/router';
import { AppComponent } from './app.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MatToolbarModule } from '@angular/material/toolbar';
import { MatButtonModule } from '@angular/material/button';
import { MatDialogModule } from '@angular/material/dialog';
import { MatInputModule,MatFormFieldModule } from '@angular/material';
import { FormsModule } from '@angular/forms';
import { LoginComponent } from './modules/login/login.component';
import { RegisterComponent } from './modules/register/register.component';
//import { TmdbContainerComponent} from './modules/player/components/tmdb-container/tmdb-container.component';
import { ReactiveFormsModule } from '@angular/forms';
import { AuthenticationService } from '../app/modules/authentication.service';
import { RouterService } from '../app/modules/router.service';
import { CanActivateRouteGuard } from '../../src/app/can-activate-route.guard';

const appRoutes: Routes = [{
  path : 'login',
  component : LoginComponent
},
{
  path : 'register',
  component : RegisterComponent
},
{
  path : '',
  redirectTo: 'login',
  pathMatch: "full"
},
{
  path: 'logout',
  component : LoginComponent,
  //canActivate: [CanActivateRouteGuard]
},
{
  path : 'players/watchlist',
  component : LoginComponent
},
{
  path: 'players/search',
  component : LoginComponent
}
];

@NgModule({
  declarations: [
    AppComponent,LoginComponent,RegisterComponent
  ],
  imports: [
    BrowserModule,
    BrowserAnimationsModule,
    MatToolbarModule,
    MatButtonModule,
    MatDialogModule,
    MatInputModule,
    MatFormFieldModule,
    FormsModule,
    PlayerModule,
    ReactiveFormsModule,
    RouterModule.forRoot(appRoutes),
  ],
  providers: [AuthenticationService,RouterService,CanActivateRouteGuard],
  bootstrap: [AppComponent]
})
export class AppModule { }
