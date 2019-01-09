import {Component, OnInit} from '@angular/core';
import {SecurityService} from '../../services/security.service';
import {User} from '../../models/user.model';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.scss']
})
export class HeaderComponent implements OnInit {

  currentUser: User;

  constructor(private security: SecurityService) {
  }

  ngOnInit() {
    this.security.getCurrentUser()
      .subscribe((res) => {
        this.currentUser = res;
      }, (error) => {
        console.log(error);
      });
  }

  logout() {
    this.security.logout();
  }
}
