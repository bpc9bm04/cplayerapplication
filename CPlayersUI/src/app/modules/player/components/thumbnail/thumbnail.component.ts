import { Component, OnInit, Input, EventEmitter, Output } from '@angular/core';
import { CPlayer } from '../../cplayer';
import { MatSnackBar } from '@angular/material/snack-bar';
import { MatDialog, MatDialogRef, MAT_DIALOG_DATA } from '@angular/material';
import {MatListModule} from '@angular/material/list';
import { PlayerDailogComponent } from '../../components/player-dailog/player-dailog.component';
@Component({
  selector: 'player-thumbnail',
  templateUrl: './thumbnail.component.html',
  styleUrls: ['./thumbnail.component.css']
})
export class ThumbnailComponent implements OnInit {
  @Input() player: CPlayer;
  @Input() watchlistApi: boolean;
  @Output() addPlayer = new EventEmitter();
  @Output() deletePlayer = new EventEmitter();

  constructor(private matSnackBar: MatSnackBar, public dialog: MatDialog) {

  }

  ngOnInit() {

  }
  addToWatchlist() {
    this.addPlayer.emit(this.player);
  }
  deleteFromWatchlist() {
    this.deletePlayer.emit(this.player);
  }
  updateFromWatchlist(actionType) {
    console.log("Player is getting updated.");
    let dailogRef = this.dialog.open(PlayerDailogComponent, {
      width: '800px',
      height: '800px',
      data: { obj: this.player, actionType: actionType }
    });
    console.log('Open dailog');
    dailogRef.afterClosed().subscribe((results)=>{
      console.log('The dailog was closed.');
    });
  }

}
