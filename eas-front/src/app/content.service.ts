import {Injectable, OnInit} from '@angular/core';
import {Observable, of} from "rxjs";
import {Content, ContentId} from "./domain/content";
import {HttpClient} from "@angular/common/http";
import {environment} from "../environments/environment";
import {map} from "rxjs/operators";
import {Response} from "./domain/Response";

@Injectable({
  providedIn: 'root'
})
export class ContentService {

  content: Content[] = []

  constructor(private httpClient: HttpClient) {
    this.getContent().subscribe(content => this.content = content)
  }

  getContent(): Observable<Content[]> {
    return this.httpClient.get<Response<Content[]>>(environment.contentUrl)
      .pipe(
        map(response => response.data)
      )
  }

  getContentById(id: number): string {
    // @ts-ignore
    let content = this.content.find(content => ContentId[content.id] == id)
    return content ? content.content : "Empty content"
  }
}
