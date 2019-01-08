import {User} from './user.model';

export interface Post {
  id: String;
  title: String;
  text: String;
  creator: User;
  createdAt: Date;
  lastModifiedAt: Date;
  lastModifier: User;
}
