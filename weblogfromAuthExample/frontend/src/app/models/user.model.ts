import {Post} from './post.model';

export interface User {
  id: String;
  username: String;
  email: String;
  posts: Post[];
}
