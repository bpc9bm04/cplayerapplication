import { Component, OnInit, Inject, Input } from '@angular/core';
import { MatDialog, MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { CPlayer } from '../../cplayer';
import { MatSnackBar } from '@angular/material/snack-bar';
import { PlayerService } from '../../player.service';
import { CPlayerStats } from '../../cplayerstats';
@Component({
  selector: 'player-dailog',
  templateUrl: './player-dailog.component.html',
  styleUrls: ['./player-dailog.component.css']
})
export class PlayerDailogComponent implements OnInit {
  player: CPlayer;
  pid: string;
  actionType: string;
  cPlayerStats: CPlayerStats;
  constructor(public snackBar: MatSnackBar, public dailogRef: MatDialogRef<PlayerDailogComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any, private playerService: PlayerService) {
    this.player = data.obj;
    this.pid = data.obj.pid;
    this.actionType = data.actionType;
  }

  ngOnInit() {
    console.log("Call the Cric Player ApI based on PID to get player stats.");
    this.playerService.getPlayerStats(this.pid).subscribe((cPlayerStats)=>{
      this.cPlayerStats=cPlayerStats;
      console.log("Fetch player stats.");
    });
  }

  onNoClick() {
    this.dailogRef.close();
  }
}
