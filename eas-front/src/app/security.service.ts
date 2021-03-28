import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {environment} from "../environments/environment";
import {Token} from "@angular/compiler";
import {Response} from "./domain/Response";
import {map} from "rxjs/operators";

@Injectable({
  providedIn: 'root'
})
export class SecurityService {

  constructor(private httpClient: HttpClient) { }

  signIn(username: string,password: string): Observable<Token> {
    return this.httpClient.post<Response<Token>>(environment.signInUrl, {
      username,
      password
    }).pipe(
      map(response => response.data)
    )
  }
}
