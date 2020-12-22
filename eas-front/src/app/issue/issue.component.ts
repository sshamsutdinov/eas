import {Component, OnInit} from '@angular/core';
import {IssueService} from "../issue.service";

@Component({
  selector: 'app-issue',
  templateUrl: './issue.component.html',
  styleUrls: ['./issue.component.sass']
})
export class IssueComponent implements OnInit {

  constructor(public issueService: IssueService) {
  }

  ngOnInit(): void {
  }
}
