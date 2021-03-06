import {User} from './user.model';
import {OldPost} from './oldPost.model';
import {Category} from './category.model';

export interface Post {
  id: String;
  title: String;
  text: String;
  creator: User;
  createdAt: Date;
  lastModifiedAt: Date;
  lastModifier: User;
  oldPosts: OldPost[];
  amountComments: number;
  commentsPaged: Comment[];
  categories: Category[];
}
