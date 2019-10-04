import { Component, OnInit, Input } from '@angular/core';
import { CPlayer } from '../../cplayer';
import { MatSnackBar } from '@angular/material/snack-bar';
import { PlayerService } from '../../player.service';
@Component({
  selector: 'player-container',
  templateUrl: './container.component.html',
  styleUrls: ['./container.component.css']
})

export class ContainerComponent implements OnInit {
  @Input()
  players: Array<CPlayer>;
  @Input()
  watchlistApi: boolean;
  constructor(private playerService: PlayerService, private matSnackBar: MatSnackBar) {

  }

  ngOnInit() {

  }

  addToWatchlist(player) {
    let message = `${player.name} added to Watchlist.`;
    this.playerService.addPlayerToWatchlist(player).subscribe((player) => {
      this.matSnackBar.open(message, '', {
        duration: 2000
      });
    });
  }
  deleteFromWatchlist(player) {
    let message = `${player.name} deleted from Watchlist.`;
    for(var i=0; i< this.players.length;i++){
      if(player.pid===this.players[i].pid){
        this.players.splice(i,1);
      }
    }
    this.playerService.deletePlayerFromWatchlist(player).subscribe((player) => {
      this.matSnackBar.open(message, '', {
        duration: 2000
      });
    });
  }
}
