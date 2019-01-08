import {Injectable, OnInit} from '@angular/core';
import {PostService} from '../services/post.service';
import {Post} from '../models/post.model';
import {BehaviorSubject} from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class PostListService implements OnInit {

  posts$ = new BehaviorSubject<Post[] | []>([]);

  constructor(private postService: PostService) {
    this.loadPosts();
  }

  ngOnInit(): void {

  }

  loadPosts() {
    this.postService.getPosts().valueChanges
      .subscribe((res) => {
        this.posts$.next(res.data['allPosts']);
      }, (error) => {
        console.log(error);
      });
  }

  // Function to add created Post to PostList
  addPost(post: Post) {
    const currentValue = this.posts$.value;
    const updatedValue = [...currentValue, post];
    this.posts$.next(updatedValue);
    console.log(this.posts$.value);
  }
}
