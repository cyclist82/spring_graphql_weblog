import {Component, OnInit} from '@angular/core';
import {Post} from '../../models/post.model';
import {PostListService} from '../post-list.service';

@Component({
  selector: 'app-post-list',
  templateUrl: './post-list.component.html',
  styleUrls: ['./post-list.component.scss']
})
export class PostListComponent implements OnInit {

  posts: Post[] = [];

  constructor(private postListService: PostListService) {
  }

  ngOnInit() {
    this.postListService.posts$.subscribe((res) => {
      this.posts = res;
    });
  }
}
