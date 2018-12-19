import { Component, OnInit } from '@angular/core';
import { Team } from 'src/app/Model/Team';
import { ExternalService } from 'src/app/Service/external.service';

@Component({
  selector: 'app-team-details',
  templateUrl: './team-details.component.html',
  styleUrls: ['./team-details.component.css']
})
export class TeamDetailsComponent implements OnInit {
  qqq: number[]
  private show: boolean = false;
  private standardList: Team[]
  constructor(private external: ExternalService) { }

  ngOnInit() {
    this.external.getAll().subscribe(
      data => {
        this.standardList = data.league.standard
        this.qqq = new Array(this.standardList.length/3)
        for(var i=0;i<this.qqq.length;i++)
          this.qqq[i] = i
      })
  }
  
  getTeam(i: any) {
    return this.standardList[i]
  }
}
