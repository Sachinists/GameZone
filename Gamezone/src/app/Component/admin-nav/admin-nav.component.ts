import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-admin-nav',
  templateUrl: './admin-nav.component.html',
  styleUrls: ['./admin-nav.component.css']
})
export class AdminNavComponent implements OnInit {
  showTabs : boolean = false
  constructor(private router:Router) { }

  ngOnInit() {
    if(localStorage.getItem('admin')){
      this.showTabs = true
      console.log("Something there"+localStorage.getItem('ID'))
    }else{
      console.log("Something not there")
    }
  }

  logout(){
    localStorage.removeItem('admin')
    this.showTabs = false
    this.router.navigateByUrl("/signin");
  }


}
