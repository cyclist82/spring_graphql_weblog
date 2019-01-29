import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {LoginComponent} from './auth/login/login.component';
import {CommonModule} from '@angular/common';
import {SignupComponent} from './auth/signup/signup.component';
import {UpdateUserComponent} from './auth/update-user/update-user.component';
import {AuthGuard} from './auth/auth-services/auth.guard';
import {CategoryComponent} from './category/category/category.component';

const routes: Routes = [
  {path: '', redirectTo: 'posts/postsPaged', pathMatch: 'full'},
  {path: 'login', component: LoginComponent},
  {path: 'signup', component: SignupComponent},
  {path: 'editUser', component: UpdateUserComponent, canActivate: [AuthGuard]},
  {path: 'category', component: CategoryComponent, canActivate: [AuthGuard]},
];

@NgModule({
  imports: [
    CommonModule, RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {
}
