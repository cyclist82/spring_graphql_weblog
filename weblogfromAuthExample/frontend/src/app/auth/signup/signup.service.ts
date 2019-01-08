import {Injectable} from '@angular/core';
import gql from 'graphql-tag';
import {Apollo} from 'apollo-angular';

const createNewUser_M = gql`
  mutation createNewUser($username: String!, $email: String!, $password: String!){
    createUser(username: $username, email: $email, password: $password) {
      username
      email
    }
  }
`;

@Injectable({
  providedIn: 'root'
})
export class SignupService {

  constructor(private apollo: Apollo) {
  }

  createNewUser(formData) {
    return this.apollo.mutate({
      mutation: createNewUser_M,
      variables: {
        username: formData.username,
        email: formData.email,
        password: formData.password,
      }
    });
  }

}
