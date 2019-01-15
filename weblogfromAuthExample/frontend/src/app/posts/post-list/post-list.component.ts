import {Component, OnInit} from '@angular/core';
import {Post} from '../../models/post.model';
import {PostListService} from '../post-list.service';
import {User} from '../../models/user.model';
import {SecurityService} from '../../services/security.service';
import {userError} from '@angular/compiler-cli/src/transformers/util';

@Component({
  selector: 'app-post-list',
  templateUrl: './post-list.component.html',
  styleUrls: ['./post-list.component.scss']
})
export class PostListComponent implements OnInit {

  posts: Post[] = [];
  currentUser: User;

  constructor(private postListService: PostListService, private securityService: SecurityService) {
  }

  ngOnInit() {
    this.postListService.posts$.subscribe((res) => {
      this.posts = res;
    });
    this.securityService.getCurrentUser()
      .subscribe((user) => {
        this.currentUser = user;
      }, (error) => {
        console.log(error);
      });
  }
}
