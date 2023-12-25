import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {Freezeritem} from "../models/freezeritem";

@Injectable({
  providedIn: 'root'
})
export class FreezerServiceService {
  private urlBasic: string = "http://localhost:8080/freezer"
  private urlREAD: string;

  constructor(private http: HttpClient) {
    this.urlREAD = this.urlBasic + '/search';
  }

  public findAll(): Observable<Freezeritem[]> {
    return this.http.get<Freezeritem[]>(this.urlREAD);
  }
}
