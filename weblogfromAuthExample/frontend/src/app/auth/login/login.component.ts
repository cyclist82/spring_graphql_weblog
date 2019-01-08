import {Component, OnInit} from '@angular/core';
import {BackendService} from '../../services/backend.service';
import {SecurityService} from '../../services/security.service';
import {Router} from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {

  dataLoading = false;

  constructor(private backendService: BackendService, private security: SecurityService, private router: Router) {
  }

  ngOnInit() {
  }


  login(formData) {
    this.backendService.loginUser(formData).valueChanges.subscribe(res => {
      this.dataLoading = false;
      console.log(res);
      window.localStorage.setItem('token', res.data['login'].token);
      this.security.user$ = res.data['login'].user;
      this.router.navigate(['/']);
    }, (error) => {
      console.log(error);
    });
  }
}
