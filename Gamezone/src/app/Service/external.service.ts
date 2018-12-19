import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { catchError } from 'rxjs/operators';
import { Observable, of } from 'rxjs'

@Injectable({
  providedIn: 'root'
})
export class ExternalService {
  teamListURL = "/api/10s/prod/v2/2018/teams.json"
  teamRankingURL = "/api/10s/prod/v1/2018/team_stats_rankings.json"
  getLiveScoresURL = "/api/10s/prod/v1/20181124/scoreboard.json"
  private d: Date
  constructor(private http: HttpClient) {
    this.d = new Date()
  }
  
  getAll():Observable<any>{
    console.log("/api/10s/prod/v2/"+this.d.getFullYear()+"/teams.json")
    return this.http.post<any>("https://ec2-54-145-106-140.compute-1.amazonaws.com:8080/externalTeams",this.d.getFullYear())
      .pipe(
        catchError(this.handleError('signIn', []))
      );
  }
  getTeamRanking():Observable<any>{
    console.log("/api/10s/prod/v1/"+this.d.getFullYear()+"/team_stats_rankings.json")
    return this.http.post<any>("https://ec2-54-145-106-140.compute-1.amazonaws.com:8080/ranking",this.d.getFullYear())
      .pipe(
        catchError(this.handleError('signIn', []))
      );
  }
  getLiveScores():Observable<any>{
    var m = this.d.getMonth() + 1
    var test = this.d.getDate()
    var t
    console.log("test: "+test)
    test = test - 1
    console.log("test: "+test)
    if(test < 10){
      t = "0"+test
    }else{
      t = test
    }
    var s = "" +  this.d.getFullYear()+m+t
    console.log("DATE: "+s)
    //console.log("/api/10s/prod/v1/"+this.d.getFullYear()+m+this.d.getDate()+"/scoreboard.json")
    return this.http.post<any>("https://ec2-54-145-106-140.compute-1.amazonaws.com:8080/testScoreoard",s)
    .pipe(
      catchError(this.handleError('signIn', []))
    );
  }
  private handleError<T>(operation = 'operation', result?: T) {
    return (error: any): Observable<T> => {
      console.error(error); 
      return of(result as T);
    };
  }
}
