import {Component, Input, OnInit, Output} from '@angular/core';
import {PostPagedService} from '../../posts/posts-paged/post-paged.service';
import {User} from '../../models/user.model';
import {SecurityService} from '../../services/security.service';
import gql from 'graphql-tag';
import {Apollo} from 'apollo-angular';

const deleteComment_M = gql`
  mutation deleteComment($id: ID!) {
    deleteComment(id: $id)
  }
`;

@Component({
  selector: 'app-show-comment',
  templateUrl: './show-comment.component.html',
  styleUrls: ['./show-comment.component.scss']
})
export class ShowCommentComponent implements OnInit {

  @Input() postIndex: number;
  @Input() comment: Comment;
  currentUser: User;

  constructor(private postPagedService: PostPagedService, private securityService: SecurityService, private apollo: Apollo) {
  }

  ngOnInit() {
    this.securityService.getCurrentUser().subscribe((user) => {
      this.currentUser = user;
    }, (error) => {
      console.log(error);
    });
  }

  onDeleteComment(id: String) {
    this.apollo.mutate({
      mutation: deleteComment_M,
      variables: {
        id: id,
      }
    }).subscribe((res) => {
      console.log(res);
      this.postPagedService.loadMoreComments(this.postIndex, 5, 0, true);
    }, (error) => {
      console.log(error);
    });

  }
}
