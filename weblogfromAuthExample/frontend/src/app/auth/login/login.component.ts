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

  error: String;
  dataLoading = false;

  constructor(private backendService: BackendService, private security: SecurityService, private router: Router) {
  }

  ngOnInit() {
  }


  login(formData) {
    this.error = null;
    this.backendService.loginUser(formData).valueChanges.subscribe(res => {
      this.dataLoading = false;
      window.localStorage.setItem('token', res.data['login'].token);
      this.security.setCurrentUser(res.data['login'].user);
      this.router.navigate(['/']);
    }, (error) => {
      this.error = error;
      console.log(error);
    });
  }
}
