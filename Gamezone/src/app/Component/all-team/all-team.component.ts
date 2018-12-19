import { Component, OnInit } from '@angular/core';
import { ExternalService } from 'src/app/Service/external.service';
import { HttpService } from 'src/app/Service/http.service';
import { Team } from 'src/app/Model/Team';
import { Router } from '@angular/router';
import { PageEvent } from '@angular/material';

@Component({
  selector: 'app-all-team',
  templateUrl: './all-team.component.html',
  styleUrls: ['./all-team.component.css']
})
export class AllTeamComponent implements OnInit {
  show: boolean = false;
  private standardList: Team[]
  private disabledList = new Array
  private favouriteList: Team[]

  length = 100;
  pageSize = 10;
  pageSizeOptions: number[] = [5, 10, 25, 100];
  pageEvent: PageEvent;

  constructor(private external: ExternalService, private http: HttpService, private router: Router) { }

  ngOnInit() {

    this.http.getFavouriteTeamList(localStorage.getItem("ID")).subscribe(
      data => {
        this.favouriteList = data
        if (!this.standardList) {
          this.external.getAll().subscribe(
            data => {
              this.standardList = data.league.standard
              console.log(this.standardList[0])

              this.disabledList = new Array(this.standardList.length)
              for (var i = 0; i < this.standardList.length; i++) {
                var temp = true
                for (var j = 0; j < this.favouriteList.length; j++) {
                  if (this.favouriteList[j].teamId == this.standardList[i].teamId) {
                    this.disabledList[i] = true
                    temp = false
                    break
                  }
                }
                if (temp) this.disabledList[i] = false
              }
              this.show = true
            }
          )
        } else {
          for (var i = 0; i < this.standardList.length; i++) {
            var temp = true
            for (var j = 0; j < this.favouriteList.length; j++) {
              if (this.favouriteList[j].teamId == this.standardList[i].teamId) {
                this.disabledList[i] = true
                temp = false
                break
              }
            }
            if (temp) this.disabledList[i] = false
          }
        }
      })

  }


  addToFav(i: any, p: any) {
    if (!p) {
      p = 1
    }
    var index = ((p - 1) * 8) + i
    var msg1 = "Are you sure you want to add this team as your favourite."
    var r = confirm(msg1);
    if (r == true) {
      this.standardList[index].userId = localStorage.getItem('ID')
      this.http.addFavTeam(this.standardList[index]).subscribe(
        data => {
          if (data[0] == "Success") {
            if (confirm("Your team has ben added to favorite list. Do you want to see the list?")) {
              this.router.navigateByUrl("/user")
            } else {
              this.ngOnInit()
            }
          } else {
            alert("Something went wrong in adding your team as favorite")
          }
        }
      )
    }
  }

}