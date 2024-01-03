import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { File } from '../models/File';

@Injectable({
  providedIn: 'root'
})
export class FileService {

  constructor(private http: HttpClient) { }

  private url = "http://localhost:8080/api/files";

  getFile(uuid: string): Observable<File> {
    return this.http.get<File>(this.url + "/download/" + uuid);
  }

  uploadFile(file: FormData) {
    return this.http.post<any>(this.url, file);
  }
}
