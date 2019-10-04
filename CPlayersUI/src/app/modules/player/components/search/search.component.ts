import { Component, OnInit } from '@angular/core';
import { CPlayer } from '../../cplayer';
import { PlayerService } from '../../player.service';
@Component({
  selector: 'player-search',
  templateUrl: './search.component.html',
  styleUrls: ['./search.component.css']
})
export class SearchComponent implements OnInit {
  
  players: Array<CPlayer>;

  constructor(private playerService: PlayerService) { }

  ngOnInit() {
  }
  onEnter(searchKey){
    this.playerService.searchPlayers(searchKey).subscribe((players)=>{
      this.players=players;
      console.log("Search Players.");
    });
  }
}
