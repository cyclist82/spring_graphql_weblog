import {Injectable, OnInit} from '@angular/core';
import gql from 'graphql-tag';
import {HttpClient} from '@angular/common/http';
import {Apollo} from 'apollo-angular';
import {Post} from '../models/post.model';

const getPosts_Q = gql`
  query getPosts {
    allPosts {
      id
      title
      text
      createdAt
      creator {
        username
      }
    }
  }
`;


const createNewPost_M = gql`
  mutation createNewPost($title: String!, $text: String!) {
    createPost(title:$title, text:$text){
      id
      title
      text
      createdAt
      creator {
        username
      }
    }
  }`;

const updatePost_M = gql`
  mutation updateExistingPost($id: ID!, $title: String!, $text: String!) {
    updatePost(id: $id, title: $title, text: $text) {
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
    }
  }
`;

const deletePost_M = gql`
  mutation  deletePost($id: ID!){
    deletePost(id: $id)
  }
`;

@Injectable({
  providedIn: 'root'
})
export class PostService {


  constructor(private http: HttpClient, private apollo: Apollo) {
  }


  getPosts() {
    return this.apollo.watchQuery({query: getPosts_Q});
  }

  createNewPost(formData) {
    return this.apollo.mutate({
      mutation: createNewPost_M,
      variables: {
        title: formData.title,
        text: formData.text,
      }
    });
  }

  updatePost(formData) {
    return this.apollo.mutate({
      mutation: updatePost_M,
      variables: {
        id: formData.id,
        title: formData.title,
        text: formData.text,
      }
    });
  }

  deletePost(id) {
    return this.apollo.mutate({
      mutation: deletePost_M,
      variables: {
        id: id,
      }
    });
  }
}
