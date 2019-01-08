import {Component, OnInit} from '@angular/core';
import {SignupService} from './signup.service';
import {FormControl, FormGroup, Validators} from '@angular/forms';
import {Router} from '@angular/router';

@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.scss']
})
export class SignupComponent implements OnInit {

  error: any;
  passwordDontMatch = false;
  signupForm = new FormGroup({
    username: new FormControl('', [Validators.required, Validators.minLength(3)]),
    email: new FormControl('', [Validators.required, Validators.email]),
    password: new FormControl('', [Validators.required, Validators.minLength(8)]),
    passwordConfirm: new FormControl('', [Validators.required, Validators.minLength(8)]),
  });

  constructor(private signupService: SignupService, private router: Router) {
  }

  ngOnInit() {
    this.passwordDontMatch = false;
  }


  signup() {
    if (this.signupForm.get('password').value !== this.signupForm.get('passwordConfirm').value) {
      this.passwordDontMatch = true;
      this.signupForm.patchValue({password: ''});
      this.signupForm.patchValue({passwordConfirm: ''});
    } else {
      this.signupService.createNewUser(this.signupForm.value)
        .subscribe((res) => {
          console.log(res);
          this.signupForm.reset();
          this.router.navigate(['/login']);
        }, (error) => {
          this.error = error;
          console.log(error);
        });
    }

  }
}
