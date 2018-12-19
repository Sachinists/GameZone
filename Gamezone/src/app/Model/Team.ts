export class Team {
    isNBAFranchise: boolean
    isAllStar: boolean
    city: string
    altCityName: string
    fullName: string
    tricode: string
    teamId: number
    nickname: string
    urlName: string
    confName: string
    divName:string 
    userId:string

    setUserId(userId:string){
        this.userId = userId
    }
}