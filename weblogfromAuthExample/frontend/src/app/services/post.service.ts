import {Injectable} from '@angular/core';
import gql from 'graphql-tag';
import {HttpClient} from '@angular/common/http';
import {Apollo} from 'apollo-angular';

const createPost_M = gql`
  mutation createPost($title: String!, $text: String!) {
  createPost(title:$title, text:$text){
    id
    title
    text
    createdAt
    creator{username}
  }
}`;

@Injectable({
  providedIn: 'root'
})
export class PostService {

  constructor(private http: HttpClient, private apollo: Apollo) {
  }

  createPost(formData) {
    return this.apollo.mutate({
      mutation createPost_M,
      variables: {
        title: formData.title,
        text: formData.text,
      }
    });
  }
}
