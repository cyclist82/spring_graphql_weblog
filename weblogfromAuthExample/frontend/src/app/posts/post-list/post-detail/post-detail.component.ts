import {Component, Input, OnInit} from '@angular/core';
import {Post} from '../../../models/post.model';
import {User} from '../../../models/user.model';
import {FormControl, FormGroup} from '@angular/forms';

@Component({
  selector: 'app-post-detail',
  templateUrl: './post-detail.component.html',
  styleUrls: ['./post-detail.component.scss']
})
export class PostDetailComponent implements OnInit {

  @Input() post: Post;
  @Input() currentUser: User;
  editMode = false;
  editPostForm = new FormGroup({
    id: new FormControl(''),
    title: new FormControl(''),
    text: new FormControl(''),
  });
  error: any;

  constructor() {
  }

  ngOnInit() {
  }

  enterEditMode() {
    this.editMode = !this.editMode;
    this.editPostForm.controls['id'].setValue(this.post.id);
    this.editPostForm.controls['title'].setValue(this.post.title);
    this.editPostForm.controls['text'].setValue(this.post.text);
  }

  savePost() {

  }
}
