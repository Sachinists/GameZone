import { Component, OnInit } from '@angular/core';
import { HttpService } from 'src/app/Service/http.service';
import { FormGroup, FormControl } from '@angular/forms';
import { ExtraSign } from 'src/app/Model/ExtraSignIn';
import { Router } from '@angular/router';
@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent implements OnInit {
  private id
  show:boolean = false;
  profileForm = new FormGroup({
    city: new FormControl(''),
    state: new FormControl(''),
    country: new FormControl(''),
    phone: new FormControl(''),
  });
  constructor(private httpService:HttpService,private router:Router) { }

  ngOnInit() {
    this.id = localStorage.getItem("ID");
    this.httpService.getDetails(localStorage.getItem("ID")).subscribe(
      data => {
        console.log(data[0])
        if(data[0]){
          if(data[0].secondPage == "true"){
            this.router.navigateByUrl('/user');
          }
          this.show = true;
        }else{
          localStorage.removeItem('ID')
          this.router.navigateByUrl('/');
        }
      }
    )
  }
  onSubmit() {
    this.httpService.extraDetails(new ExtraSign(this.id,this.profileForm.value.city,this.profileForm.value.state,this.profileForm.value.country,this.profileForm.value.phone))
      .subscribe(data => {
        console.log(data)
        if(data[0] === "1rows updated"){
          this.router.navigateByUrl('/user');
        }
      })
  }
}
