import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {Freezeritem} from "../models/freezeritem";

@Injectable(
  {providedIn: 'root'}
)
export class FreezerService {
  private urlBasic: string = "http://localhost:8080/freezer"
  private urlREAD: string;

  constructor(private http: HttpClient) {
    this.urlREAD = this.urlBasic + '/search';
  }

  public findAll(): Observable<Freezeritem[]> {
    return this.http.get<Freezeritem[]>(`${this.urlREAD}`);
  }

  public updateFreezerItem(freezerItem: Freezeritem): Observable<any> {
    return this.http.put(`${this.urlBasic}/put`, freezerItem);
  }
  public createFreezerItem(freezerItem: Freezeritem): Observable<any> {
    return this.http.post(`${this.urlBasic}/post`, freezerItem);
  }
}
