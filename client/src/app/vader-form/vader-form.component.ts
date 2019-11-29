import { Component, OnInit } from '@angular/core';

import {VaderService} from './vader.service';
import {VaderFormDto} from '../models/vader-form.dto';
import {VaderAnswerDto} from '../models/vader-answer.dto';
import {User} from '../models/user';
import {BehaviorSubject, Observable} from 'rxjs';
import {DataSource} from '@angular/cdk/table';
import {AnotherUser} from '../models/anotherUser';


@Component({
  selector: 'app-vader-form',
  templateUrl: './vader-form.component.html',
  styleUrls: ['./vader-form.component.css']
})
export class VaderFormComponent implements OnInit {
  dataSource = new UserDataSource(this.vaderService);
  displayedColumns = ['name', 'positive', 'negative', 'neutral', 'overall'];

  private currentUser:User;

  constructor(private vaderService: VaderService) {
  }

  vaderFormDto: VaderFormDto = new VaderFormDto();
  textValue = '';
  u :AnotherUser[];
  show = false;

  ngOnInit() {
  }

  myFunction() {

    this.vaderFormDto.text = this.textValue;
    // let user = localStorage.getItem("currentUser")
    this.currentUser = (JSON.parse(localStorage.getItem('currentUser')));
    this.vaderFormDto.user = this.currentUser;

    this.vaderService
      .addVaderForm(this.vaderFormDto).subscribe(result => this.u = result);
    this.show = true;

  }

}

export class UserDataSource extends DataSource<any> {

  constructor(private userService: VaderService) {
    super();
  }

  connect(): Observable<AnotherUser[]> {
    return this.userService.getUser();
  }

  disconnect() {}
}


