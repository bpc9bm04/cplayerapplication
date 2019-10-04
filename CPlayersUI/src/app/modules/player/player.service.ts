import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { map } from 'rxjs/operators/map';
import { CPlayer } from './cplayer';
import { CPlayerStats } from './cplayerstats';
import { Observable } from 'rxjs/Observable';
import { retry } from 'rxjs/operators';
@Injectable()
export class PlayerService {

  imagePrifix: string;
  apiKey: string;
  watchlistEndPoint: string;
  search: string;
  playerStatsEndPoint: string;
  constructor(private http: HttpClient) {
    this.search = 'https://cricapi.com/api/playerFinder?';
    this.apiKey = 'apikey=dRFV1VaAUCR95GYSys7ar7Boxtq1';
    this.watchlistEndPoint = "http://localhost:8082/api/v1/cplayerservice/cplayer";
    this.playerStatsEndPoint = "https://cricapi.com/api/playerStats?";
    //this.imagePrifix='https://image.tmdb.org/t/p/w500';
  }
  addHttpHeaders(){
    let headers: HttpHeaders = new HttpHeaders();
    headers = headers.append('Content-Type', 'application/json');
    headers = headers.append('Authorization', 'Bearer '+localStorage.getItem('bearerToken'));
    return headers;
  }
  addPlayerToWatchlist(player: CPlayer) {
    let headers: HttpHeaders =this.addHttpHeaders();
    return this.http.post(this.watchlistEndPoint, player,{headers});
  }
  deletePlayerFromWatchlist(player: CPlayer) {
    let options = {
      headers: this.addHttpHeaders()
    };
    const url = `${this.watchlistEndPoint}/${player.pid}`;
    return this.http.delete(url,options);
  }
  getWatchListedPlayers(): Observable<Array<CPlayer>> {
    let headers: HttpHeaders =this.addHttpHeaders();
    return this.http.get<Array<CPlayer>>(this.watchlistEndPoint, {headers});
  }

  getPlayerStats(pid: string): Observable<CPlayerStats> {
    //if (pid.length > 0) {
      const playerStatisticsEndPoint = `${this.playerStatsEndPoint}${this.apiKey}&pid=${pid}`;
      return this.http.get(playerStatisticsEndPoint).pipe(
        retry(3),
        map(this.pickPlayerStatsResults)
      );
    //}
  }
  searchPlayers(searchKey: string): Observable<Array<CPlayer>> {
    if (searchKey.length > 0) {
      const searchPlayersEndPoints = `${this.search}${this.apiKey}&name=${searchKey}`;
      return this.http.get(searchPlayersEndPoints).pipe(
        retry(3),
        map(this.pickPlayerResults),
        map(this.transformPosterPath).bind(this)
      );
    }
  }
  transformPosterPath(players): Array<CPlayer> {
    this.imagePrifix = 'https://image.tmdb.org/t/p/w500';
    return players.map((player) => {
      //movie.poster_path = `${this.imagePrifix}${movie.poster_path}`;
      //movie.movieId=`${movie.id}`;
      return player;
    });
  }
  pickPlayerResults(response) {
    return response['data'];
  }
  pickPlayerStatsResults(response) {
    console.log(response);
    return response;
  }
}
