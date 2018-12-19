export class Notifications{
    message : string
	userId : string
	notifyDate : string
	seen : boolean
	gameId : string
	constructor(a:string,b:string,c:string,d:boolean,e:string){
		this.message = a
		this.userId =  b
		this.notifyDate = c
		this.seen = d
		this.gameId  = e
	}
}