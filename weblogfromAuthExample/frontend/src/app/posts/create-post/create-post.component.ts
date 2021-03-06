import {Component, EventEmitter, OnInit, Output} from '@angular/core';
import {PostService} from '../../services/post.service';
import {Router} from '@angular/router';
import {PostListService} from '../post-list.service';

@Component({
  selector: 'app-create-post',
  templateUrl: './create-post.component.html',
  styleUrls: ['./create-post.component.scss']
})
export class CreatePostComponent implements OnInit {

  error: String;

  constructor(private postService: PostService, private router: Router, private postListService: PostListService) {
  }

  ngOnInit() {
  }

  createNewPost(createPostForm: any) {
    this.postService.createNewPost(createPostForm)
      .subscribe((res) => {
        // this.postListService.addPost(res.data['createPost']);
        // this.postListService.loadPosts();
        this.router.navigate(['/posts/postsPaged']);
      }, (error) => {
        this.error = error;
      });
  }
}
