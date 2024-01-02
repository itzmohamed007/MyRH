import { File } from "./File"

export interface Recruiter {
    uuid: string,
    full_name: string,
    email: string,
    password: string,
    phone: string,
    image: File
}