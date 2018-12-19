import { TeamSchedule } from "./TeamSchedule";
import { Team } from "./Team";

export class TeamCards{
    team: Team
    schedule : TeamSchedule
    constructor(a:Team,b:TeamSchedule){
        this.team = a
        this.schedule = b
    }
}