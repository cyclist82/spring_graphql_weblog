import {Component, Input, OnInit} from '@angular/core';
import {OldPost} from '../../../models/oldPost.model';

@Component({
  selector: 'app-history-detail',
  templateUrl: './history-detail.component.html',
  styleUrls: ['./history-detail.component.scss']
})
export class HistoryDetailComponent implements OnInit {

  @Input() oldPost: OldPost;

  constructor() {
  }

  ngOnInit() {
  }

}
