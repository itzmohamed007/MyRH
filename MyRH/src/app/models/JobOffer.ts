import { Recruiter } from "./Recruiter";

export interface JobOffer {
    uuid: string,
    city: string,
    description: string,
    profile: string,
    status: string,
    title: string,
    salary: string,
    recruiter: Recruiter
}