import {BrowserModule} from '@angular/platform-browser';
import {LOCALE_ID, NgModule, NO_ERRORS_SCHEMA} from '@angular/core';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';

import {AppComponent} from './app.component';

import {MDBBootstrapModule} from 'angular-bootstrap-md';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {HttpLink, HttpLinkModule} from 'apollo-angular-link-http';
import {environment} from '../environments/environment';
import {HttpClientModule, HttpHeaders} from '@angular/common/http';
import {InMemoryCache} from 'apollo-cache-inmemory';
import {setContext} from 'apollo-link-context';
import {APOLLO_OPTIONS, ApolloModule} from 'apollo-angular';
import {AuthGuard} from './auth/auth-services/auth.guard';
import {BackendService} from './services/backend.service';
import {LoginComponent} from './auth/login/login.component';
import {SecurityService} from './services/security.service';
import {HeaderComponent} from './common/header/header.component';
import {PostsComponent} from './posts/posts.component';
import {AppRoutingModule} from './app-routing.module';
import {PostRoutingModule} from './posts/shared/post-routing/post-routing.module';
import {CreatePostComponent} from './posts/create-post/create-post.component';
import {PostListComponent} from './posts/post-list/post-list.component';
import {PostDetailComponent} from './posts/post-list/post-detail/post-detail.component';
import {PostListService} from './posts/post-list.service';
import {PostService} from './services/post.service';
import {CommonModule} from '@angular/common';
import {CustomDatePipe} from './helpers/custom-date.pipe';
import {SignupComponent} from './auth/signup/signup.component';
import {SignupService} from './auth/signup/signup.service';
import {GraphqlerrorPipe} from './helpers/graphqlerror.pipe';
import { CustomTimePipe } from './helpers/custom-time.pipe';
import { UpdateUserComponent } from './auth/update-user/update-user.component';
import { PostsPagedComponent } from './posts/posts-paged/posts-paged.component';

export function createApollo(httpLink: HttpLink) {
  const http = httpLink.create({uri: environment.graphql});

  const auth = setContext((_, {headers}) => {
    // get the authentication token from local storage if it exists
    const token = localStorage.getItem('token');
    // return the headers to the context so httpLink can read them
    // in this example we assume headers property exists
    // and it is an instance of HttpHeaders
    if (!token) {
      return {};
    } else {
      return {
        headers: new HttpHeaders().set('Authorization', `Bearer ${token}`)
        // headers: new HttpHeaders().set('Authorization', `${token}`)
        // headers: new HttpHeaders().set('token', `${token}`)
      };
    }
  });
  return {
    // link: httpLink.create({uri: environment.graphql}),
    link: auth.concat(http),
    cache: new InMemoryCache(),
  };
}

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    HeaderComponent,
    PostsComponent,
    CreatePostComponent,
    PostListComponent,
    PostDetailComponent,
    CustomDatePipe,
    SignupComponent,
    GraphqlerrorPipe,
    CustomTimePipe,
    UpdateUserComponent,
    PostsPagedComponent,
  ],
  imports: [
    CommonModule,
    BrowserModule,
    BrowserAnimationsModule,
    MDBBootstrapModule.forRoot(),
    FormsModule,
    HttpClientModule,
    ApolloModule,
    HttpLinkModule,
    AppRoutingModule,
    PostRoutingModule,
    ReactiveFormsModule,
  ],
  providers: [
    BackendService,
    AuthGuard,
    SecurityService,
    PostListService,
    PostService,
    SignupService,
    {
      provide: APOLLO_OPTIONS,
      useFactory: createApollo,
      deps: [HttpLink],
    },
  ],
  bootstrap: [AppComponent],
  schemas: [NO_ERRORS_SCHEMA]
})
export class AppModule {
}
