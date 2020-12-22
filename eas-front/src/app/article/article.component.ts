import { Component, OnInit } from '@angular/core';
import {Observable, of} from "rxjs";
import {Article} from "../domain/issue";
import {IssueService} from "../issue.service";
import {ActivatedRoute} from "@angular/router";

@Component({
  selector: 'app-article',
  templateUrl: './article.component.html',
  styleUrls: ['./article.component.sass']
})
export class ArticleComponent implements OnInit {

  articles: Article[] = []

  constructor(
    private issueService: IssueService,
    private route: ActivatedRoute
  ) {}

  ngOnInit(): void {
    // @ts-ignore
    let number: string = +this.route.snapshot.paramMap?.get('number')
    this.articles = this.issueService.getArticles(number)
  }

}
