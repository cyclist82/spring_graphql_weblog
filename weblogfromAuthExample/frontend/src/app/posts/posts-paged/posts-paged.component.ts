import {Component, OnChanges, OnInit, SimpleChanges} from '@angular/core';
import {Post} from '../../models/post.model';
import {Apollo} from 'apollo-angular';
import {HttpClient} from '@angular/common/http';
import gql from 'graphql-tag';
import {User} from '../../models/user.model';
import {SecurityService} from '../../services/security.service';
import {PostPagedService} from './post-paged.service';


const getPostsSorted_Q = gql`
  query getPostsSorted($page: Int!, $size: Int!, $sortOrder: String!, $sortBy: String!) {
    allPostsSorted(page: $page, size: $size,sortOrder: $sortOrder, sortBy:  $sortBy) {
      amountPages
      posts{id
        title
        text
        createdAt
        lastModifiedAt
        creator{
          id
          username
        }
        lastModifier{
          id
          username
        }
      }
    }}
`;


@Component({
  selector: 'app-posts-paged',
  templateUrl: './posts-paged.component.html',
  styleUrls: ['./posts-paged.component.scss']
})
export class PostsPagedComponent implements OnInit {

  currentUser: User;
  posts: Post[] = [];
  page = 0;
  pages = [];
  postsPerPage = 4;
  amountPages: number;
  sortBy: String = 'createdAt';
  sortOrder: String = 'DESC';

  constructor(private postPagedService: PostPagedService, private apollo: Apollo, private http: HttpClient, private securityService: SecurityService) {
  }

  ngOnInit() {
    this.loadPagedPosts();
    this.loadCurrentUser();
  }

  private loadPagedPosts() {
    // this.apollo.watchQuery({
    //   query: getPostsSorted_Q,
    //   variables: {
    //     page: this.page,
    //     size: this.postsPerPage,
    //     sortOrder: this.sortOrder,
    //     sortBy: this.sortBy,
    //   }
    // }).valueChanges
    //   .subscribe((res) => {
    //     this.posts = res.data['allPostsSorted'].posts;
    //     this.amountPages = res.data['allPostsSorted'].amountPages;
    //     this.pages = [];
    //     for (let i = this.page - 1; i <= this.page + 3; i++) {
    //       if (i > 0 && i <= this.amountPages) {
    //         this.pages.push(i);
    //       }
    //     }
    //   }, (error) => {
    //     console.log(error);
    //   });
    this.postPagedService.loadPagedPosts(this.page, this.postsPerPage, this.sortBy, this.sortOrder);
    this.postPagedService.amountPages$.subscribe((res) => {
      this.amountPages = res;
      this.pages = [];
      for (let i = this.page - 1; i <= this.page + 3; i++) {
        if (i > 0 && i <= this.amountPages) {
          this.pages.push(i);
        }
      }
    }, (error) => {
      console.log(error);
    });
    this.postPagedService.posts$.subscribe((res) => {
      this.posts = res;
    }, (error) => {
      console.log(error);
    });
  }

  private loadCurrentUser() {
    this.securityService.getCurrentUser()
      .subscribe((user) => {
        this.currentUser = user;
      }, (error) => {
        console.log(error);
      });
  }

  setPage(indexPage: number) {
    this.page = indexPage - 1;
    this.loadPagedPosts();
  }

  pageAfter() {
    this.page = this.page + 1;
    this.loadPagedPosts();
  }

  pageBefore() {
    this.page = this.page - 1;
    this.loadPagedPosts();
  }
}
