export class Chat{
    sender : string;
    dateOfSending : string;
    teamId : number;
    message : string;
    readUnread : boolean
    sID : string
    constructor(a,b,c,d,s){
        this.sender = a
        this.dateOfSending = b
        this.teamId = c
        this.message = d
        this.readUnread = false
        this.sID = s
    }
}