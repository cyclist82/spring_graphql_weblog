import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Apollo} from 'apollo-angular';
import gql from 'graphql-tag';

const getUser_Q = gql`query userQueries {
  getUser_Q {
    name
    email
  }
}`;
const loginUser = gql`query userQueries($email: String!,$password: String!) {
  login(credentials:{email: $email, password: $password}){
    token
    user{
      id
      username
      email
    }
  }
}`;

const verifyUser = gql`query verifyUserQuery($token: String!){
  verify(token: $token){
    token
    user{
      id
      username
      email
    }
  }
}`;


const createUser_M = gql`mutation updateQueries($name: String!, $email: String!, $password: String!) {
  addUser_M(name:$name, email:$email, password:$password){
    email
  }
}`;
const updateUser_M = gql`mutation updateQueries($id: String!, $username: String!, $email: String!, $password: String, $passwordOld: String!) {
  update(id: $id, username:$username, email:$email, password:$password, passwordOld: $passwordOld){
    username
    email
  }
}`;

@Injectable({
  providedIn: 'root'
})

export class BackendService {

  constructor(private http: HttpClient, private apollo: Apollo) {
  }


  getUser() {
    return this.apollo.watchQuery({query: getUser_Q});
  }

  loginUser(formData) {
    return this.apollo.watchQuery({
      query: loginUser,
      variables: {
        email: formData.email,
        password: formData.password
      }
    });
  }

  verifyUser(token: String) {
    return this.apollo.watchQuery({
      query: verifyUser,
      variables: {
        token: token
      }
    });
  }

  createUser(formData) {
    return this.apollo.mutate({
      mutation: createUser_M,
      variables: {
        name: formData.name,
        email: formData.email,
        password: formData.password
      }
    });
  }

  updateUser(formData) {
    return this.apollo.mutate({
      mutation: updateUser_M,
      variables: {
        id: formData.id,
        username: formData.username,
        email: formData.email,
        password: formData.password,
        passwordOld: formData.passwordOld,
      }
    });
  }


}
