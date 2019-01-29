import {User} from './user.model';

export interface Comment {
  id: String,
  text: String,
  createTime: Date,
  creator: User,
}
