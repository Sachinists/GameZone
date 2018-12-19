import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { HttpClientModule }    from '@angular/common/http';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import {
  SocialLoginModule,
  AuthServiceConfig,
  FacebookLoginProvider
} from "angular-6-social-login";
import { SigninComponent } from './Component/signin/signin.component';
import { DashboardComponent } from './Component/dashboard/dashboard.component';
import { FormsModule }   from '@angular/forms';
import { ReactiveFormsModule } from '@angular/forms';
import { UserPageComponent } from './Component/user-page/user-page.component';
import { PageNotFoundComponent } from './Component/page-not-found/page-not-found.component';
import { NavbarComponent } from './Component/navbar/navbar.component';
import { AdminPageComponent } from './Component/admin-page/admin-page.component';
import { NgxPaginationModule } from 'ngx-pagination';
import { ScrollToModule } from '@nicky-lenaers/ngx-scroll-to';
import { TeamRankingComponent } from './Component/team-ranking/team-ranking.component';
import { AllTeamComponent } from './Component/all-team/all-team.component';
import {MatTooltipModule, MatFormFieldModule} from '@angular/material';
import {MatCardModule} from '@angular/material/card';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import {MatTabsModule} from '@angular/material/tabs';
import {MatExpansionModule} from '@angular/material/expansion';
import {MatTableModule} from '@angular/material/table';
import {MatPaginatorModule} from '@angular/material/paginator';
import { TeamDetailsComponent } from './Component/team-details/team-details.component';
import { LiveScoreComponent } from './Component/live-score/live-score.component';
import { ChatComponent } from './Component/chat/chat.component';
import { AdminNavComponent } from './Component/admin-nav/admin-nav.component';
import {MatSelectModule} from '@angular/material/select';

export function getAuthServiceConfigs() {
  let config = new AuthServiceConfig(
      [
        {
          id: FacebookLoginProvider.PROVIDER_ID,
          provider: new FacebookLoginProvider("201890604052292")
        }
      ]
  );
  return config;
}

@NgModule({
  declarations: [
    AppComponent,
    SigninComponent,
    DashboardComponent,
    UserPageComponent,
    PageNotFoundComponent,
    NavbarComponent,
    AdminPageComponent,
    TeamRankingComponent,
    AllTeamComponent,
    TeamDetailsComponent,
    LiveScoreComponent,
    ChatComponent,
    AdminNavComponent
  ],
  imports: [
    SocialLoginModule,NgxPaginationModule,
    BrowserModule,HttpClientModule,FormsModule,ReactiveFormsModule,
    AppRoutingModule,ScrollToModule.forRoot(),
    MatTooltipModule,MatCardModule, BrowserAnimationsModule,MatTabsModule,MatExpansionModule,
    MatTableModule,MatPaginatorModule,MatSelectModule,MatFormFieldModule
  ],
  providers: [{
    provide: AuthServiceConfig,
    useFactory: getAuthServiceConfigs
  }],
  bootstrap: [AppComponent]
})
export class AppModule { }
