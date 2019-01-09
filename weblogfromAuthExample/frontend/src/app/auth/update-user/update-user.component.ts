import {Component, OnInit} from '@angular/core';
import {FormControl, FormGroup, Validators} from '@angular/forms';
import {User} from '../../models/user.model';
import {SecurityService} from '../../services/security.service';
import {BackendService} from '../../services/backend.service';
import {Router} from '@angular/router';

@Component({
  selector: 'app-update-user',
  templateUrl: './update-user.component.html',
  styleUrls: ['./update-user.component.scss']
})
export class UpdateUserComponent implements OnInit {

  currentUser: User;
  error: String;
  passwordDontMatch = false;
  signupForm = new FormGroup({
    id: new FormControl(''),
    username: new FormControl('', [Validators.required, Validators.minLength(3)]),
    email: new FormControl('', [Validators.required, Validators.email]),
    passwordOld: new FormControl('', [Validators.required, Validators.minLength(8)]),
    password: new FormControl(''),
    passwordConfirm: new FormControl(''),
  });

  constructor(private securityService: SecurityService, private backendService: BackendService, private router: Router) {
  }

  ngOnInit() {
    this.currentUser = this.securityService.getCurrentUser().value;
    this.signupForm.controls['id'].setValue(this.currentUser.id);
    this.signupForm.controls['username'].setValue(this.currentUser.username);
    this.signupForm.controls['email'].setValue(this.currentUser.email);
  }


  onEditUser() {
    this.error = null;
    if (this.signupForm.get('password').value !== this.signupForm.get('passwordConfirm').value) {
      this.passwordDontMatch = true;
      this.signupForm.patchValue({password: ''});
      this.signupForm.patchValue({passwordConfirm: ''});
    } else {
      this.passwordDontMatch = false;
      this.backendService.updateUser(this.signupForm.value)
        .subscribe((res) => {
            let updatedUser = this.currentUser;
            updatedUser.username = res.data['update'].username;
            updatedUser.email = res.data['update'].email;
            this.securityService.setCurrentUser(updatedUser);
            this.router.navigate(['posts']);
          }, (error) => {
            this.signupForm.patchValue({passwordOld: ''});
            this.error = error;
          }
        );
    }
  }
}
