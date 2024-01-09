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

 // TODO how to pass param documentId??
  public updateFreezerItem(freezerItem: Freezeritem): Observable<any> {
    return this.http.put(`${this.urlBasic}/update`, freezerItem,
      );
  }

  public createFreezerItem(freezerItem: Freezeritem): Observable<Freezeritem> {
    return this.http.post<Freezeritem>(`${this.urlBasic}/create`, freezerItem);
  }
}
