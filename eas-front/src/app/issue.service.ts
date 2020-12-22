import { Injectable } from '@angular/core';
import {Observable, of} from "rxjs";
import {Article, ArticlePages, Issue} from "./domain/issue";
import {HttpClient} from "@angular/common/http";
import {environment} from "../environments/environment";
import {Response} from "./domain/Response";
import {map} from "rxjs/operators";

@Injectable({
  providedIn: 'root'
})
export class IssueService {

  issues: Issue[] = [
    {
      number: "1",
      doi: "doi/com",
      publishingDate: "2020-12-21",
      pdfMeta: "https://s3.amazonaws.com/dq-blog-files/pandas-cheat-sheet.pdf",
      coverMeta: "https://upload.wikimedia.org/wikipedia/commons/1/1b/Square_200x200.png",
      articles: [
        {
          title: "Article 1 Title",
          author: "Article 1 Author",
          authorAbout: "Article 1 Author about",
          citation: "Article 1 Citation",
          annotation: "Lorem ipsum dolor sit amet, consectetur adipisicing elit. Atque commodi distinctio dolor, dolorem doloremque ex expedita fuga illo mollitia neque nisi odit praesentium quasi saepe sapiente suscipit tempora unde, velit!",
          rubric: "Article 1 rubric",
          keywords: ["keyword 1", "keyword 2"],
          pages: {
            pageStart: 0,
            pageEnd: 50
          },
          pdfMeta: "https://s3.amazonaws.com/dq-blog-files/pandas-cheat-sheet.pdf"
        },
        {
          title: "Article 2 Title",
          author: "Article 2 Author",
          authorAbout: "Article 2 Author about",
          citation: "Article 2 Citation",
          annotation: "Lorem ipsum dolor sit amet, consectetur adipisicing elit. Atque commodi distinctio dolor, dolorem doloremque ex expedita fuga illo mollitia neque nisi odit praesentium quasi saepe sapiente suscipit tempora unde, velit!",
          rubric: "Article 2 rubric",
          keywords: ["keyword 1", "keyword 2"],
          pages: {
            pageStart: 51,
            pageEnd: 100
          },
          pdfMeta: "https://s3.amazonaws.com/dq-blog-files/pandas-cheat-sheet.pdf"
        }
      ]
    },
    {
      number: "2",
      doi: "doi/com",
      publishingDate: "2020-12-21",
      pdfMeta: "https://s3.amazonaws.com/dq-blog-files/pandas-cheat-sheet.pdf",
      coverMeta: "https://upload.wikimedia.org/wikipedia/commons/1/1b/Square_200x200.png",
      articles: [
        {
          title: "Article 1 Title",
          author: "Article 1 Author",
          authorAbout: "Article 1 Author about",
          citation: "Article 1 Citation",
          annotation: "Lorem ipsum dolor sit amet, consectetur adipisicing elit. Atque commodi distinctio dolor, dolorem doloremque ex expedita fuga illo mollitia neque nisi odit praesentium quasi saepe sapiente suscipit tempora unde, velit!",
          rubric: "Article 1 rubric",
          keywords: ["keyword 1", "keyword 2"],
          pages: {
            pageStart: 0,
            pageEnd: 50
          },
          pdfMeta: "https://s3.amazonaws.com/dq-blog-files/pandas-cheat-sheet.pdf"
        }
      ]
    }
  ]

  constructor(private httpClient: HttpClient) {
    this.getIssues().subscribe(issues =>
      this.issues = issues
    )
  }

  getIssues(): Observable<Issue[]> {
    return this.httpClient.get<Response<Issue[]>>(environment.issueUrl)
      .pipe(
        map(response => response.data)
      )
  }

  getArticles(number: string): Article[] {
    let issue = this.issues.find(issue => issue.number == number)
    return issue ? issue.articles : [] ;
  }
}
