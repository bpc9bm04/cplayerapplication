import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { HttpClient,HttpClientModule } from '@angular/common/http';
import { MatCardModule } from '@angular/material/card';
import { MatButtonModule} from '@angular/material/button';
import {MatListModule} from '@angular/material/list';
import { MatSnackBarModule } from '@angular/material/snack-bar';
import { MatDialogModule } from '@angular/material/dialog';
import { MatInputModule,MatFormFieldModule } from '@angular/material';
import { FormsModule } from '@angular/forms';
import { ThumbnailComponent } from './components/thumbnail/thumbnail.component';
import { ContainerComponent } from './components/container/container.component';
//import { TmdbContainerComponent } from './components/tmdb-container/tmdb-container.component';
import { WatchlistComponent } from './components/watchlist/watchlist.component';
import { PlayerDailogComponent } from './components/player-dailog/player-dailog.component';
import { PlayerService } from './player.service';
import { PlayerRouterModule } from './player-router.module';
import { SearchComponent } from './components/search/search.component';
@NgModule({
  imports: [
    CommonModule,
    HttpClientModule,
    MatCardModule,
    MatButtonModule,
    MatListModule,
    MatSnackBarModule,
    MatInputModule,
    MatFormFieldModule,
    FormsModule,
  ],
  declarations: [
    ThumbnailComponent,
    ContainerComponent,
    WatchlistComponent,
   // TmdbContainerComponent,
    PlayerDailogComponent,
    SearchComponent,
  ],
  entryComponents :[PlayerDailogComponent],
  exports: [
    PlayerRouterModule,
    ThumbnailComponent,
    PlayerDailogComponent,
  ],
  providers: [
    PlayerService,
  ]
})
export class PlayerModule { }
