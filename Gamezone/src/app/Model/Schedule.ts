import { Arena } from "./Arena";
import { TeamSchedule } from "./TeamSchedule";
import { Nugget } from "./Nugget";

export class Schedule{
    arena: Arena
    attendance: string
    clock: string
    endTimeUTC: string
    extendedStatusNum: number
    gameDuration: {hours: string, minutes: string}
    gameId: string
    hTeam: TeamSchedule
    hasGameBookPdf: boolean
    isBuzzerBeater: boolean
    isGameActivated: boolean
    isPreviewArticleAvail: boolean
    isRecapArticleAvail: boolean
    isStartTimeTBD: boolean
    nugget: Nugget
    period: {current: number, type:number, maxRegular: number, isHalftime: boolean, isEndOfPeriod: boolean}
    seasonStageId: number
    seasonYear: string
    startDateEastern: string
    startTimeEastern: string
    startTimeUTC:string
    statusNum: number
    vTeam: TeamSchedule
}