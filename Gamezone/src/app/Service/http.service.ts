import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { SignIn } from '../Model/SignIn';
import { catchError } from 'rxjs/operators';
import { Observable, of } from 'rxjs'
import { ExtraSign } from '../Model/ExtraSignIn';
import { Team } from '../Model/Team';
import { Ids } from '../Model/Ids';
import { Notifications } from '../Model/Notifications';
import { Chat } from '../Model/Chat';

@Injectable({
  providedIn: 'root'
})
export class HttpService {
  url = "https://ec2-54-145-106-140.compute-1.amazonaws.com:8080/"
  constructor(private http: HttpClient) { }

  signIn(signIn: SignIn): Observable<any[]> {
    return this.http.post<any[]>(this.url + "signIn", signIn)
      .pipe(
        catchError(this.handleError('signIn', []))
      );
  }

  removeFromFav(ids:Ids):Observable<any>{
    return this.http.post<any>(this.url + "removeFav", ids)
      .pipe(
        catchError(this.handleError('signIn', []))
      );
  }

  extraDetails(extra:ExtraSign):Observable<any> {
    return this.http.post<any>(this.url + "extraDetails", extra)
      .pipe(
        catchError(this.handleError('signIn', []))
      );
  }

  addFavTeam(team:Team):Observable<any>{
    return this.http.post<any>(this.url + "insertFavTeam",team)
      .pipe(
        catchError(this.handleError('signIn', []))
      )
  }

  markAllNotificationAsRead(id:string):Observable<any>{
    return this.http.get<any>(this.url + "removeNotificationById/"+id)
    .pipe(
      catchError(this.handleError('removeNotificationById',[]))
    )
  }

  addNotification(list : Notifications []): Observable<any>{
    console.log(list)
    return this.http.post<any>(this.url + "addNotification",list)
      .pipe(
        catchError(this.handleError('signIn', []))
      )
  }
  addMessage(msg : Chat): Observable<any>{
    return this.http.post<any>(this.url + "addChatMessage",msg)
      .pipe(
        catchError(this.handleError('signIn', []))
      )
  }
  getDetails(id: string): Observable<any[]> {
    console.log("URL: "+this.url + "getDetails/" + id)
    return this.http.get<any[]>(this.url + "getDetails/" + id)
      .pipe(
        catchError(this.handleError('signIn', []))
      );
  }

  getNotification(id: string): Observable<any[]> {
    return this.http.get<any[]>(this.url + "getAllNotification/" + id)
      .pipe(
        catchError(this.handleError('signIn', []))
      );
  }

  getChats(id: string): Observable<any[]> {
    return this.http.get<any[]>(this.url + "getAllChats/" + id)
      .pipe(
        catchError(this.handleError('signIn', []))
      );
  }

  getAll():Observable<any[]>{
    return this.http.get<any[]>(this.url + "getAll")
      .pipe(
        catchError(this.handleError('signIn', []))
      );
  }
  getFavouriteTeamList(id:String):Observable<any[]>{
    return this.http.get<any[]>(this.url + "getFavouriteTeams/"+id)
      .pipe(
        catchError(this.handleError('signIn', []))
      );
  }

  block(list: any): Observable<any[]> {
    return this.http.post<any[]>(this.url + "block", list)
      .pipe(
        catchError(this.handleError('block', []))
      );
  }

  private handleError<T>(operation = 'operation', result?: T) {
    return (error: any): Observable<T> => {
      console.error(error); 
      return of(result as T);
    };
  }
}
