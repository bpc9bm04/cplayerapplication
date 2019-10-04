import { NgModule }              from '@angular/core';
import { RouterModule, Routes }  from '@angular/router';
//import { TmdbContainerComponent} from './components/tmdb-container/tmdb-container.component';
import { WatchlistComponent } from './components/watchlist/watchlist.component';
import { SearchComponent } from './components/search/search.component';
import { CanActivateRouteGuard } from '../../can-activate-route.guard';
const playerRoutes: Routes = [
    { path: 'players',
        children:[
            { path: '', redirectTo: '/players/search', pathMatch: 'full' , canActivate: [CanActivateRouteGuard]},
            { path: 'watchlist', component: WatchlistComponent, canActivate: [CanActivateRouteGuard]},
            { path: 'search', component: SearchComponent, canActivate: [CanActivateRouteGuard]},
        ]
}
];

@NgModule({
  imports: [
    RouterModule.forChild( playerRoutes)
  ],
  exports: [
    RouterModule
  ]
})
export class PlayerRouterModule {}