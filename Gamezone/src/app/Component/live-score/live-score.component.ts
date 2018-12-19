import { Component, OnInit, OnDestroy } from '@angular/core';
import { ExternalService } from 'src/app/Service/external.service';
import { Schedule } from 'src/app/Model/Schedule';
import { Team } from 'src/app/Model/Team';
import { HttpService } from 'src/app/Service/http.service';
import { interval, Subscription } from 'rxjs';

// Create an Observable that will publish a value on an interval




@Component({
  selector: 'app-live-score',
  templateUrl: './live-score.component.html',
  styleUrls: ['./live-score.component.css']
})
export class LiveScoreComponent implements OnInit, OnDestroy {
  liveGames: Schedule[]
  private upComingGames: Schedule[] = new Array
  private pastGames: Schedule[] = new Array
  private liveScoresList: Schedule[] = new Array
  private standardList: Team[]
  secondsCounter = interval(1000 * 60);
  qwerty: Subscription
  constructor(private external: ExternalService) {
  }

  ngOnInit() {

    // Subscribe to begin publishing values
    this.qwerty = this.secondsCounter.subscribe(n =>
      console.log(`It's been ${n} seconds since subscribing!`)
    );
    this.makeExternalAPI();
  }

  makeExternalAPI() {
    this.external.getLiveScores().subscribe(data => {
      console.log(data.games[0])
      this.liveScoresList = data.games
      var currentTime = new Date()
      this.liveScoresList.forEach(element => {
        var s = new Date(element.startTimeUTC)
        var e = new Date(element.endTimeUTC)
        if (currentTime >= s && currentTime <= e) {
          this.liveGames.push(element)
          console.log("Match in progress")
        } else if (currentTime < s) {
          this.upComingGames.push(element)
          var d = Math.abs(currentTime.getTime() - s.getTime());
          console.log("Match is about to start in " + d)
        } else {
          this.pastGames.push(element)
          var d = Math.abs(e.getTime() - currentTime.getTime());
          console.log("Match is finished " + JSON.stringify(this.pastGames[0].nugget.text))
        }
      });
    })
  }

  getTeamFullNameById(id: number) {
    for (var i = 0; i < this.standardList.length; i++) {
      if (this.standardList[i].teamId == id) {
        return this.standardList[i].fullName
      }
    }
  }

  getProperTime(a: any) {
    return new Date(a).toUTCString()
  }

  ngOnDestroy() {
    this.qwerty.unsubscribe()
  }
}
