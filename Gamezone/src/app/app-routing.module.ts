import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { DashboardComponent } from './Component/dashboard/dashboard.component';
import { SigninComponent } from './Component/signin/signin.component';
import { UserPageComponent } from './Component/user-page/user-page.component';
import { AuthGuard }  from './auth.guard';
import { PageNotFoundComponent } from './Component/page-not-found/page-not-found.component';
import { AdminPageComponent } from './Component/admin-page/admin-page.component';
import { TeamRankingComponent } from './Component/team-ranking/team-ranking.component';
import { AllTeamComponent } from './Component/all-team/all-team.component';
import { TeamDetailsComponent } from './Component/team-details/team-details.component';
import { LiveScoreComponent } from './Component/live-score/live-score.component';
import { ChatComponent } from './Component/chat/chat.component';
const routes: Routes = [
  { 
    path: '', 
    component: SigninComponent
  },
  { 
    path: 'signin', 
    component: SigninComponent
  },
  { 
    path: 'dashboard',
    canActivate: [AuthGuard],
    component: DashboardComponent 
  },
  { 
    path: 'user',
    canActivate: [AuthGuard],
    component: UserPageComponent 
  },
  { 
    path: 'admin',
    canActivate: [AuthGuard],
    component: AdminPageComponent 
  },
  { 
    path: 'teamRanking',
    canActivate: [AuthGuard],
    component: TeamRankingComponent 
  },
  { 
    path: 'allTeam',
    canActivate: [AuthGuard],
    component: AllTeamComponent 
  },
  { 
    path: 'details',
    canActivate: [AuthGuard],
    component: TeamDetailsComponent 
  },
  { 
    path: 'liveScore',
    canActivate: [AuthGuard],
    component: LiveScoreComponent 
  },
  { 
    path: 'chat',
    canActivate: [AuthGuard],
    component: ChatComponent 
  },
  { 
    path: '**', 
    component: PageNotFoundComponent
  },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
