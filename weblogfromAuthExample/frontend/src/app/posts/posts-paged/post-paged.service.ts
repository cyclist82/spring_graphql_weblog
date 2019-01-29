import {Injectable} from '@angular/core';
import {BehaviorSubject} from 'rxjs';
import {Post} from '../../models/post.model';
import {Apollo} from 'apollo-angular';
import gql from 'graphql-tag';

const getPostsSorted_Q = gql`
    query getPostsSorted($page: Int!, $size: Int!, $sortOrder: String!, $sortBy: String!, $commentSize: Int!, $commentPage: Int!) {
        allPostsSorted(page: $page, size: $size,sortOrder: $sortOrder, sortBy:  $sortBy) {
            amountPages
            posts{id
                title
                text
                createdAt
                lastModifiedAt
                creator{
                    id
                    username
                }
                lastModifier{
                    id
                    username
                }
                amountComments
                categories {
                    id
                    name
                    active
                }
                commentsPaged(size: $commentSize, page: $commentPage){
                    id
                    text
                    createTime
                    creator {
                        id
                        username
                    }
                }
            }
        }}
`;

const getCommentsPaged_Q = gql`
    query getPost($id: ID!, $commentSize: Int!, $commentPage: Int!) {
        getPost(id: $id) {
            amountComments
            commentsPaged(size: $commentSize, page: $commentPage){
                id
                text
                createTime
                creator{id
                    username}}
        }
    }
`;

@Injectable({
    providedIn: 'root'
})
export class PostPagedService {

    posts$ = new BehaviorSubject<Post[] | []>([]);
    amountPages$ = new BehaviorSubject<number | 0>(0);

    constructor(private apollo: Apollo) {
    }

    public loadPagedPosts(page: number, postsPerPage: number, sortBy: String, sortOrder: String, commentSize: number, commentPage: number) {
        this.apollo.watchQuery({
            query: getPostsSorted_Q,
            variables: {
                page: page,
                size: postsPerPage,
                sortOrder: sortOrder,
                sortBy: sortBy,
                commentSize: commentSize,
                commentPage: commentPage,
            },
            fetchPolicy: 'network-only',
        }).valueChanges
            .subscribe((res) => {
                this.posts$.next(res.data['allPostsSorted'].posts);
                this.amountPages$.next(res.data['allPostsSorted'].amountPages);
            }, (error) => {
                console.log(error);
            });
    }

    public updatePost(index: number, post: Post) {
        let postList: Post[] = this.posts$.getValue();
        let originalPost: Post = postList[index];
        originalPost.lastModifiedAt = post.lastModifiedAt;
        originalPost.lastModifier = post.lastModifier;
        originalPost.text = post.text;
        originalPost.title = post.title;
        postList[index] = originalPost;
        this.posts$.next(postList);
    }

// Had to disable the reloadComment...fails after createComment for unknown reason...probably something with apollo caching...don't know
// WatchQuery didn't work this way...I think it somehow started a loop, as the length of comments changed during the method
//   public reloadComments(index: number, commentSize: number, commentPage: number) {
//     const postId = this.posts$.getValue()[index].id;
//     this.apollo.watchQuery({
//       query: getCommentsPaged_Q,
//       variables: {
//         id: postId,
//         commentSize: commentSize,
//         commentPage: commentPage,
//       },
//       fetchPolicy: 'network-only',
//     }).valueChanges.subscribe((res) => {
//       let posts: Post[] = this.posts$.getValue();
//       let post: Post = posts[index];
//       post.commentsPaged = res.data['getPost'].commentsPaged;
//       post.amountComments = res.data['getPost'].amountComments;
//       posts[index] = post;
//       this.posts$.next(posts);
//     }, (error) => {
//       console.log(error);
//     });
//   }

    loadMoreComments(postIndex: number, commentSize: number, commentPage: number, resetComments: boolean) {

        const postId = this.posts$.getValue()[postIndex].id;
        this.apollo.query({
            query: getCommentsPaged_Q,
            variables: {
                id: postId,
                commentSize: commentSize,
                commentPage: commentPage,
            },
            fetchPolicy: 'network-only',
        }).subscribe(
            (res) => {
                let posts = this.posts$.getValue();
                let post = posts[postIndex];
                if (resetComments) {
                    post.commentsPaged = [];
                }
                post.commentsPaged = [...post.commentsPaged, ...res.data['getPost'].commentsPaged];
                post.amountComments = res.data['getPost'].amountComments;
                posts[postIndex] = post;
                this.posts$.next(posts);
            }, (error) => {
                console.log(error);
            }
        );
    }
}
