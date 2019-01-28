import {Component, OnInit} from '@angular/core';
import {Apollo} from 'apollo-angular';
import gql from 'graphql-tag';
import {ActivatedRoute} from '@angular/router';
import {Post} from '../../models/post.model';

const getPost_Q = gql`
  query getPost($id: ID!){
    getPost(id: $id){
      id
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
      oldPosts {
        title
        text
        savedAt
      }
    }
  }
`;

@Component({
  selector: 'app-post-history',
  templateUrl: './post-history.component.html',
  styleUrls: ['./post-history.component.scss']
})
export class PostHistoryComponent implements OnInit {

  postId: String;
  post: Post;

  constructor(private apollo: Apollo, private activatedRoute: ActivatedRoute) {
    this.activatedRoute.params.subscribe(params => {
      this.postId = params.id;
    });
  }

  ngOnInit() {
    this.loadPost();
  }

  loadPost() {
    this.apollo.watchQuery({
      query: getPost_Q,
      variables: {
        id: this.postId,
      },
      fetchPolicy: 'network-only',
    }).valueChanges.subscribe((res) => {
      this.post = res.data['getPost'];
    }, (error) => {
      console.log(error);
    });
  }

}
