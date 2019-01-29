import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {Apollo} from 'apollo-angular';
import gql from 'graphql-tag';
import {PostPagedService} from '../../posts/posts-paged/post-paged.service';

const createComment_M = gql`
  mutation createComment($postId: ID!, $text: String!){
    createComment(postId: $postId, text: $text){
      text
      createTime
      creator {
        username
      }
    }
  }
`;

@Component({
  selector: 'app-create-comment',
  templateUrl: './create-comment.component.html',
  styleUrls: ['./create-comment.component.scss']
})
export class CreateCommentComponent implements OnInit {

  @Input() postId: String;
  @Input() postIndex: number;
  @Output() cancelComment: EventEmitter<any> = new EventEmitter<any>();
  text = '';

  constructor(private apollo: Apollo, private postPagedService: PostPagedService) {
  }

  ngOnInit() {
  }

  onCreateComment() {
    this.apollo.mutate({
      mutation: createComment_M,
      variables: {
        postId: this.postId,
        text: this.text,
      }
    }).subscribe((res) => {
      this.postPagedService.loadMoreComments(this.postIndex, 5, 0, true);
      this.cancelComment.emit(null);
      this.text = '';
    }, (error) => {
      console.log(error);
    });
  }

  cancelCreateComment() {
    this.text = '';
    this.cancelComment.emit(null);
  }
}
