import { Component, OnInit } from '@angular/core';
import { Team } from 'src/app/Model/Team';
import { HttpService } from 'src/app/Service/http.service';
import { Chat } from 'src/app/Model/Chat';
import { FormsModule } from '@angular/forms';
import { Subscription, interval } from 'rxjs';

@Component({
  selector: 'app-chat',
  templateUrl: './chat.component.html',
  styleUrls: ['./chat.component.css']
})
export class ChatComponent implements OnInit {
  selected: string
  qweerty: string
  isSelected: boolean = false
  isNotSelected : boolean = true
  ccc: boolean = false
  userID: string
  zxcv: Subscription
  secondsCounter = interval(1000 * 5);
  private favouriteTeamList: Team[] = new Array
  chats: Chat[]

  constructor(private http: HttpService) { }

  ngOnInit() {
    this.userID = localStorage.getItem('ID')
    console.log("USERID: " + this.userID)
    this.http.getFavouriteTeamList(localStorage.getItem('ID')).subscribe(
      data => {
        this.ccc = true
        this.favouriteTeamList = data
      }
    )
  }

  sendMsg(i: any) {
    console.log(this.qweerty)
    var d = new Date().toLocaleTimeString() + " " + new Date().toLocaleDateString()
    var chat = new Chat("", d, this.selected, this.qweerty, this.userID)
    this.chats.push(chat)
    this.http.addMessage(chat).subscribe(data => {
      console.log(data)
      this.qweerty = ""
    })

  }

  doSomething(i: any) {
    // this.zxcv = this.secondsCounter.subscribe(n => {
    //   console.log(`It's been ${n} seconds since subscribing!`)
      this.fetchChat()
    // }
    // );
  }

  fetchChat() {
    console.log("fetching chat: "+this.selected)
    if(this.selected != undefined){
      this.http.getChats(this.selected).subscribe(
        data => {
          this.chats = data
          this.isSelected = true
          this.isNotSelected = false
        }
      )
      this.zxcv = this.secondsCounter.subscribe(n => {
        console.log(`It's been ${n} seconds since subscribing!`)
        this.http.getChats(this.selected).subscribe(
          data => {
            this.chats = data
            // this.isSelected = true
            // this.isNotSelected = false
          }
        )
      }
      );
    }else{
      this.isSelected = false
      this.isNotSelected = true
      this.zxcv.unsubscribe()
    }

  }
  ngOnDestroy() {
    if(this.zxcv)
    this.zxcv.unsubscribe()
  }
}
