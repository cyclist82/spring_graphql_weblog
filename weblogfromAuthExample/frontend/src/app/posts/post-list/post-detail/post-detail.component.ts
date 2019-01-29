import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {Post} from '../../../models/post.model';
import {User} from '../../../models/user.model';
import {FormControl, FormGroup} from '@angular/forms';
import {PostService} from '../../../services/post.service';
import {PostPagedService} from '../../posts-paged/post-paged.service';
import {CategoryService} from '../../../services/category.service';
import {Category} from '../../../models/category.model';
import gql from 'graphql-tag';
import {Apollo} from 'apollo-angular';

const addCategoryToPost_M = gql`
    mutation addCategoryToPost($postId: ID!, $categoryId: ID!) {
        addCategoryToPost(postId: $postId, categoryId: $categoryId)
    }
`;

@Component({
    selector: 'app-post-detail',
    templateUrl: './post-detail.component.html',
    styleUrls: ['./post-detail.component.scss']
})
export class PostDetailComponent implements OnInit {

    @Input() post: Post;
    @Input() currentUser: User;
    @Input() indexOfPost: number;
    @Input() historyMode: boolean;
    createComment = false;
    editMode = false;
    editPostForm = new FormGroup({
        id: new FormControl(''),
        title: new FormControl(''),
        text: new FormControl(''),
    });
    availableCategories: Category[] = [];
    error: any;
    @Output() updatePosts: EventEmitter<any> = new EventEmitter();

    constructor(private postService: PostService, private postPagedService: PostPagedService, private categoryService: CategoryService, private apollo: Apollo) {
    }

    ngOnInit() {
    }

    enterEditMode() {
        this.editMode = !this.editMode;
        this.editPostForm.controls['id'].setValue(this.post.id);
        this.editPostForm.controls['title'].setValue(this.post.title);
        this.editPostForm.controls['text'].setValue(this.post.text);
        this.availableCategories = [];
        let activeCategories = this.categoryService.activeCategories$.getValue();
   // TODO filter categories already on the post
    }


    updatePost() {
        this.postService.updatePost(this.editPostForm.value)
            .subscribe((res) => {
                this.editMode = false;
                this.postPagedService.updatePost(this.indexOfPost, res.data.updatePost);
            }, (error) => {
                console.log(error);
            });
    }

    deletePost(id: String) {
        this.postService.deletePost(id).subscribe(
            (res) => {
                console.log(res);
                this.updatePosts.emit(null);
            }, (error) => {
                console.log(error);
            });
    }

    toggleCreateComment() {
        this.createComment = !this.createComment;
    }

    onAddCategoryToPost(categoryId: String) {
        console.log(this.post.id);
        console.log(categoryId);
        this.apollo.mutate({
            mutation: addCategoryToPost_M,
            variables: {
                postId: this.post.id,
                categoryId: categoryId,
            }
        }).subscribe((res) => {
            console.log(res);
        }, (error) => {
            console.log(error);
        });
    }
}
