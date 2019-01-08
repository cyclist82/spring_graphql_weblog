import {Component} from '@angular/core';
import {User} from './models/user.model';
import {SecurityService} from './services/security.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent {
  user: User | null;
  title = 'app';

  constructor(private security: SecurityService) {
    this.security.getCurrentUser().subscribe(user => {
      this.user = user;
    });
  }
}
