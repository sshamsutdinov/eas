import {Component, OnInit} from '@angular/core';
import {ContentService} from "../content.service";
import {ActivatedRoute} from "@angular/router";

@Component({
  selector: 'app-main-content',
  templateUrl: './main-content.component.html',
  styleUrls: ['./main-content.component.sass']
})
export class MainContentComponent implements OnInit {

  currentContent = ''

  constructor(
    public contentService: ContentService,
    private route: ActivatedRoute
  ) {
  }

  ngOnInit(): void {
    // @ts-ignore
    const id = +this.route.snapshot.paramMap.get('id')
    this.currentContent = this.contentService.getContentById(id)
  }
}
