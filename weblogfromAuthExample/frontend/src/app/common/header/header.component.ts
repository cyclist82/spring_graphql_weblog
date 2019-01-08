import { Component, OnInit } from '@angular/core';
import {SecurityService} from '../../services/security.service';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.scss']
})
export class HeaderComponent implements OnInit {

  constructor(private security: SecurityService) { }

  ngOnInit() {
  }

  logout() {
    this.security.logout();
  }
}
