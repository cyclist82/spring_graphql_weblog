import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {RouterModule, Routes} from '@angular/router';
import {PostsComponent} from '../../posts.component';
import {CreatePostComponent} from '../../create-post/create-post.component';
import {PostListComponent} from '../../post-list/post-list.component';
import {AuthGuard} from '../../../auth/auth-services/auth.guard';
import {PostsPagedComponent} from '../../posts-paged/posts-paged.component';
import {PostHistoryComponent} from '../../post-history/post-history.component';

const postRoutes: Routes = [
  {
    path: 'posts', component: PostsComponent,
    children: [
      {path: '', component: PostListComponent},
      {path: 'postsPaged', component: PostsPagedComponent},
      {path: 'new', component: CreatePostComponent, canActivate: [AuthGuard]},
      {path: ':id/history', component: PostHistoryComponent},
    ]
  },
];

@NgModule({
  declarations: [],
  imports: [
    CommonModule, RouterModule.forChild(postRoutes),
  ],
  exports: [RouterModule],
})
export class PostRoutingModule {
}
