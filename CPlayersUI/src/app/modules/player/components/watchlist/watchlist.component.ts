import { Component, OnInit } from '@angular/core';
import { CPlayer } from '../../cplayer';
import { PlayerService } from '../../player.service';
@Component({
  selector: 'movie-watchlist',
  templateUrl: './watchlist.component.html',
  styleUrls: ['./watchlist.component.css']
})
export class WatchlistComponent implements OnInit {
  players: Array<CPlayer>;
  watchlistApi: boolean;
  constructor(private playerService: PlayerService) {
    this.players = [];
    this.watchlistApi=true;
  }

  ngOnInit() {
    this.playerService.getWatchListedPlayers().subscribe((player) => {
      this.players.push(...player);
    });
  }

}
