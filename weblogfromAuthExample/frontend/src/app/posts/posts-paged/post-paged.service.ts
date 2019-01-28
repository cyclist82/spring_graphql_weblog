import {Injectable} from '@angular/core';
import {BehaviorSubject} from 'rxjs';
import {Post} from '../../models/post.model';
import {Apollo} from 'apollo-angular';
import gql from 'graphql-tag';

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

@Injectable({
  providedIn: 'root'
})
export class PostPagedService {

  posts$ = new BehaviorSubject<Post[] | []>([]);
  amountPages$ = new BehaviorSubject<number | 0>(0);

  constructor(private apollo: Apollo) {
  }

  public loadPagedPosts(page: number, postsPerPage: number, sortBy: String, sortOrder: String) {
    this.apollo.watchQuery({
      query: getPostsSorted_Q,
      variables: {
        page: page,
        size: postsPerPage,
        sortOrder: sortOrder,
        sortBy: sortBy,
      },
      fetchPolicy: 'network-only',
    }).valueChanges
      .subscribe((res) => {
        this.posts$.next(res.data['allPostsSorted'].posts);
        this.amountPages$.next(res.data['allPostsSorted'].amountPages);
      }, (error) => {
        console.log(error);
      });
  }

  public updatePost(index: number, post: Post) {
    let postList: Post[];
    postList = this.posts$.getValue();
    postList[index] = post;
    this.posts$.next(postList);
  }
}
