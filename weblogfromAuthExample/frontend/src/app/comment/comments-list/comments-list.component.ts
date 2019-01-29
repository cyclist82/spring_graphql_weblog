import {Component, Input, OnInit} from '@angular/core';
import {PostPagedService} from '../../posts/posts-paged/post-paged.service';

@Component({
  selector: 'app-comments-list',
  templateUrl: './comments-list.component.html',
  styleUrls: ['./comments-list.component.scss']
})
export class CommentsListComponent implements OnInit {

  @Input() comments: Comment[] = [];
  @Input() postIndex: number;
  @Input() amountComments: number;

  constructor(private postPagedService: PostPagedService) {
  }

  ngOnInit() {
  }

  loadMoreComments() {
    const amountComments = this.comments.length;
    this.postPagedService.loadMoreComments(this.postIndex, 5, Math.floor(amountComments / 5), false);
  }

}
