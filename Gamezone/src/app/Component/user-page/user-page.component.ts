import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { ExternalService } from 'src/app/Service/external.service';
import { Team } from 'src/app/Model/Team';
import { HttpService } from 'src/app/Service/http.service';
import { Ids } from 'src/app/Model/Ids';
import { Schedule } from 'src/app/Model/Schedule';
import { MatCardModule } from '@angular/material/card';
import { MatTabsModule } from '@angular/material/tabs';
import { Notifications } from 'src/app/Model/Notifications';
@Component({
  selector: 'app-user-page',
  templateUrl: './user-page.component.html',
  styleUrls: ['./user-page.component.css']
})
export class UserPageComponent implements OnInit {
  private favouriteTeamList: Team[] = new Array
  showFavTeam: boolean = false
  liveGames: Schedule[]
  private upComingGames: Schedule[] = new Array
  private pastGames: Schedule[] = new Array
  private liveScoresList: Schedule[] = new Array
  private standardList: Team[]
  private notificationList: Notifications[] = new Array
  pp: boolean = false
  qq: boolean = false
  constructor(private external: ExternalService, private http: HttpService, private router: Router) { }

  ngOnInit() {
    this.external.getAll().subscribe(
      data => {
        this.standardList = data.league.standard
        this.http.getFavouriteTeamList(localStorage.getItem('ID')).subscribe(
          data => {
            this.favouriteTeamList = data
            if (this.favouriteTeamList.length > 0) {
              this.showFavTeam = true
            }
            this.external.getLiveScores().subscribe(data => {
              this.liveScoresList = data.games
              var currentTime = new Date()
              this.liveScoresList.forEach(element => {
                var s = new Date(element.startTimeUTC)
                var e = new Date(element.endTimeUTC)
                if (currentTime > e) {
                  this.pastGames.push(element)
                }
              });
              if (this.pastGames.length > 0) {
                this.pp = true
              }
              else {
                this.qq = true
              }
            })
          }
        )
      }
    )
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

  removeFav(i: any) {
    var r = confirm("Are you sure, you are willing to remove this team from your favourite list")
    if (r) {
      this.http.removeFromFav(new Ids(this.favouriteTeamList[i].teamId, localStorage.getItem('ID'))).subscribe(
        data => {
          this.ngOnInit()
          if (this.favouriteTeamList.length - 1 == 0)
            this.showFavTeam = false
        })
    }
  }

  getFavouriteTeamList(id: String) {
    this.http.getFavouriteTeamList(id).subscribe(
      data => {
        this.favouriteTeamList = data
        if (this.favouriteTeamList.length > 0) {
          this.showFavTeam = true
        }
      }
    )
  }
}
