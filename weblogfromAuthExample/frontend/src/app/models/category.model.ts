import {Post} from './post.model';

export interface Category {
  id: String;
  name: String;
  active: Boolean;
  posts: Post[];
}
