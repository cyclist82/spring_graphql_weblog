import {Injectable} from '@angular/core';
import {BehaviorSubject} from 'rxjs';
import {User} from '../models/user.model';
import {BackendService} from './backend.service';
import {Router} from '@angular/router';
import {async} from 'rxjs/internal/scheduler/async';

@Injectable({
  providedIn: 'root'
})
export class SecurityService {

  private user$ = new BehaviorSubject<User | null>(null);

  constructor(private backendService: BackendService, private router: Router) {
    this.reloadUser();
  }


  reloadUser() {
    if (localStorage.getItem('token') !== null) {
      this.backendService.verifyUser(localStorage.getItem('token')).valueChanges
        .subscribe(res => {
          this.user$.next(res.data['verify'].user);
          localStorage.setItem('token', res.data['verify'].token);
        });
    }
  }

  getCurrentUser() {
    return this.user$;
  }

  logout() {
    localStorage.removeItem('token');
    this.user$.next(null);
    this.router.navigate(['/']);
  }

  setCurrentUser(user: User) {
    this.user$.next(user);
  }
}
