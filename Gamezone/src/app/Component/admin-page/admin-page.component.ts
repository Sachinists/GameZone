import { Component, OnInit } from '@angular/core';
import { HttpService } from 'src/app/Service/http.service';
import { SignIn } from 'src/app/Model/SignIn';
import { NgxPaginationModule } from 'ngx-pagination';

@Component({
  selector: 'app-admin-page',
  templateUrl: './admin-page.component.html',
  styleUrls: ['./admin-page.component.css']
})
export class AdminPageComponent implements OnInit {
  show: boolean = false;
  showErr: boolean = false;
  private list: SignIn[]
  private changes

  constructor(private httpService: HttpService) { }

  ngOnInit() {
    this.httpService.getAll().subscribe(
      data => {
        console.log(data)
        this.list = data
        this.show = true
        if (this.list != null && this.list != undefined) {
          this.show = true;
          this.showErr = false;
          this.changes = new Array<boolean>(this.list.length);
          for (var i = 0; i < this.list.length; i++) {
            this.changes[i] = false
          }
        }
        else {
          this.show = false
          this.showErr = true;
        }
      }
    )
  }
  checkboxClickd(i) {
    var msg1 = "Are you sure you want to block the user."
    var r = confirm(msg1);
    if (r == true) {
      this.httpService.block(this.list[i]).subscribe(
        data => {
          this.list.splice(i,1)
          if(this.list.length === 0){
            this.showErr = true
            this.show = false
          }
        }
      )
    }
  }
}
