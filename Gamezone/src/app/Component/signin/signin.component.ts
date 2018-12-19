import { Component, OnInit } from '@angular/core';
import { AuthService,FacebookLoginProvider } from "angular-6-social-login";
import {Router} from '@angular/router';
import { HttpService } from 'src/app/Service/http.service';
import { SignIn } from 'src/app/Model/SignIn';
import { FormGroup, FormControl } from '@angular/forms';
import { ScrollToService, ScrollToConfigOptions } from '@nicky-lenaers/ngx-scroll-to';

@Component({
  selector: 'app-signin',
  templateUrl: './signin.component.html',
  styleUrls: ['./signin.component.css']
})
export class SigninComponent implements OnInit {
  adminLogin = new FormGroup({
    username: new FormControl(''),
    password: new FormControl(''),
  });
  show:boolean = false;
  private dev:boolean = false;
  constructor( private authService: AuthService,private router:Router,private httpService:HttpService,private _scrollToService: ScrollToService) {  
  }

  ngOnInit() {
    if(localStorage.getItem('ID') != null || localStorage.getItem('ID' !+ undefined)){
      this.httpService.getDetails(localStorage.getItem('ID')).subscribe(
        data => {
          console.log("Testinh"+JSON.stringify(data[0].blocked))
          if(data[0].blocked === "false"){
            this.router.navigateByUrl('/dashboard');
          }else{
            localStorage.removeItem("ID")
            alert("Sorry but admin has blocked your account")
          }
        }
      )

    }
    console.log(localStorage.getItem("admin"))
    if(localStorage.getItem("admin") === "true"){
      this.router.navigateByUrl('/admin');
    }
  }


  public socialSignIn(socialPlatform : string) {
    let socialPlatformProvider ;  
    if(socialPlatform == "facebook"){
      socialPlatformProvider = FacebookLoginProvider.PROVIDER_ID;
    }
    this.authService.signIn(socialPlatformProvider).then(
      (userData) => {
        console.log("After Signin")
        console.log(userData)
        this.httpService.signIn(new SignIn(
          userData.email,
          userData.id,
          userData.image,
          userData.name,
          userData.provider,
          userData.token))
          .subscribe(d =>{
            console.log(d)
            this.httpService.getDetails(userData.id).subscribe(
              data => {
                console.log(data)
                if(data[0].blocked === "false"){
                  localStorage.setItem("ID",userData.id)
                  localStorage.setItem("dev",'dev')
                  this.router.navigateByUrl('/dashboard');
                }else{
                  localStorage.removeItem("ID")
                  alert("Sorry but admin has blocked your account")
                }
              }
            )
            // localStorage.setItem("ID",userData.id)
            // this.router.navigateByUrl('/dashboard');
          }
        )}
    ).catch(error => {
      console.log(error)
    });
  }

  onSubmit() {
    if((this.adminLogin.value.username === "dev") && (this.adminLogin.value.password === "dev")){
      localStorage.setItem("admin","true");
      this.router.navigateByUrl("/admin")
    }else{
      this.adminLogin.reset()
      this.dev = true;
    }
  }
  admin(){
    this.show = !this.show
    this.triggerScrollTo()
  }
  devv(){
    this.dev = false
  }
  public triggerScrollTo() {
    const config: ScrollToConfigOptions = {
      target: 'destination'
    };
    this._scrollToService.scrollTo(config);
  }
}


