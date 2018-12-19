import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { HttpService } from 'src/app/Service/http.service';
import { Notifications } from 'src/app/Model/Notifications';
import { Team } from 'src/app/Model/Team';
import { Schedule } from 'src/app/Model/Schedule';
import { ExternalService } from 'src/app/Service/external.service';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent implements OnInit {
  showTabs: boolean = false
  showUser: boolean = true
  notification: Notifications[]
  nl: boolean = false;
  private favouriteTeamList: Team[] = new Array
  showFavTeam: boolean = false
  liveGames: Schedule[]

  private pastGames: Schedule[] = new Array
  private liveScoresList: Schedule[] = new Array
  private standardList: Team[]
  private notificationList: Notifications[] = new Array
  pp: boolean = false
  qq: boolean = false
  constructor(private router: Router, private http: HttpService, private external: ExternalService) { }

  ngOnInit() {
    console.log("in init navbar")
    if (localStorage.getItem('ID')) {
      this.showTabs = true
      this.external.getAll().subscribe(
        data => {
          console.log(data)
          this.standardList = data.league.standard
          console.log(this.standardList)
          this.http.getFavouriteTeamList(localStorage.getItem('ID')).subscribe(
            data => {
              this.favouriteTeamList = data
              this.external.getLiveScores().subscribe(data => {
                this.liveScoresList = data.games
                var currentTime = new Date()
                this.liveScoresList.forEach(element => {
                  var s = new Date(element.startTimeUTC)
                  var e = new Date(element.endTimeUTC)
                  if (currentTime > e) {
                    this.pastGames.push(element)
                    console.log("localStorage.getItem('dev'): " + localStorage.getItem('dev'))
                    if (localStorage.getItem('dev') === 'dev') {
                      this.favouriteTeamList.forEach(favTeam => {
                        if (element.hTeam.teamId == favTeam.teamId || element.vTeam.teamId == favTeam.teamId) {
                          var h = element.hTeam.score
                          var t = element.vTeam.score
                          var d = +h - +t
                          var msg: string
                          console.log("check")
                          if (element.hTeam.teamId == favTeam.teamId) {
                            if (d > 0) {
                              msg = this.getTeamFullNameById(favTeam.teamId) + " won the match in their home."
                            } else if (d < 0) {
                              msg = this.getTeamFullNameById(favTeam.teamId) + " lost their home match."
                            }
                          } else {
                            if (d > 0) {
                              msg = this.getTeamFullNameById(favTeam.teamId) + " lost their away match."
                            } else if (d < 0) {
                              msg = this.getTeamFullNameById(favTeam.teamId) + " won the match away from their home."
                            }
                          }
                          this.notificationList.push(new Notifications(msg, localStorage.getItem('ID'), new Date().toLocaleString(), false, element.gameId))
                        }
                      })
                    }
                  }
                });
                console.log(localStorage.getItem('dev'))
                if (localStorage.getItem('dev') === 'dev') {
                  console.log("adding notification")
                  console.log(this.notificationList)
                  this.http.addNotification(this.notificationList).subscribe(data => {
                    console.log(this.notificationList.length + " Notifications Added successfully")
                    this.http.getNotification(localStorage.getItem('ID')).subscribe(
                      data => {
                        console.log("fetchinf notification")
                        this.notification = data
                        console.log(this.notification)
                        if (this.notification.length > 0) {
                          this.nl = true
                        }
                      }
                    )
                  })
                  localStorage.removeItem('dev')
                  console.log("removing dev : " + localStorage.getItem('dev'))
                } else {                    
                    this.http.getNotification(localStorage.getItem('ID')).subscribe(
                      data => {
                        console.log("fetchinf notification")
                        this.notification = data
                        console.log(this.notification)
                        if (this.notification.length > 0) {
                          this.nl = true
                        }
                      }
                    )
                }
              })
            }
          )
        })
      console.log("Something there" + localStorage.getItem('ID'))
    }
    else {
      console.log("Something not there")
    }
  }


  getTeamFullNameById(id: number) {
    for (var i = 0; i < this.standardList.length; i++) {
      if (this.standardList[i].teamId == id) {
        return this.standardList[i].fullName
      }
    }
  }

  logout() {
    localStorage.removeItem('ID')
    localStorage.removeItem('admin')
    this.showTabs = false
    this.router.navigateByUrl("/signin");
  }
  markAllNotificationAsRead() {
    if (this.notification.length > 0) {
      this.http.markAllNotificationAsRead(localStorage.getItem('ID')).subscribe(
        data => {
          console.log(data)
          this.nl = false
        }
      )
    }
  }
}
