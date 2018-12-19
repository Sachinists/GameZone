import { Component, OnInit } from '@angular/core';
import { ExternalService } from 'src/app/Service/external.service';
import { TeamRanking } from 'src/app/Model/TeamRanking';
import { CustomRank } from 'src/app/Model/CustomRank';
import { Team } from 'src/app/Model/Team';
import { Router } from '@angular/router';

@Component({
  selector: 'app-team-ranking',
  templateUrl: './team-ranking.component.html',
  styleUrls: ['./team-ranking.component.css']
})
export class TeamRankingComponent implements OnInit {
  private teamRankingStandardRegularSeason: TeamRanking[]
  private teamRankingStandardPreseason: TeamRanking[]
  private tt: CustomRank[] = new Array
  private seasonYear: number
  private allTEams: Team[]
  show: boolean = false
  constructor(private external: ExternalService,private router : Router) { }

  ngOnInit() {
    console.log("in init" + localStorage.getItem('dev'))
    // if(localStorage.getItem('dev')==='dev'){
    //   localStorage.removeItem('dev')
    //   this.router.navigateByUrl('/user')
    // }
    //else{
      this.external.getAll().subscribe(
        allTEamData => {
          this.allTEams = allTEamData.league.standard
          this.external.getTeamRanking().subscribe(
            data => {
              this.teamRankingStandardRegularSeason = data.league.standard.regularSeason.teams
              this.teamRankingStandardPreseason = data.league.standard.preseason.teams
              this.seasonYear = data.league.standard.seasonYear
              this.ranking()
              this.generateForDisplay()
              this.show = true
            }
          )
        }
      )
  // }
  }
  generateForDisplay(){
    this.tt.forEach(i => {
      var temp : Team = this.searchTeamObject(i.teamId)
      i.city = temp.city
      i.fullName = temp.fullName
      i.tricode = temp.tricode
    })
  }
  searchTeamObject(tId){
    for(var i = 0; i < this.allTEams.length; i++){
      if(tId == this.allTEams[i].teamId){
        return this.allTEams[i]
      }
    }
  }
  ranking() {
    this.teamRankingStandardRegularSeason.forEach(o => {
      var tId = o.teamId
      var r = parseInt(o.drpg.rank, 10)
      var a = parseFloat(o.drpg.avg)
      this.tt.push(new CustomRank(tId, a, r))
    })
    this.tt.sort(this.compare)
    for (var i = this.tt.length - 1; i >= 0; i--) {
      if (Number.isNaN(this.tt[i].rank)) {
        this.tt.splice(i, 1)
      }
    }
  }
  compare(a: CustomRank, b: CustomRank) {
    if (a.rank < b.rank)
      return -1;
    if (a.rank > b.rank)
      return 1;
    return 0;
  }


}
