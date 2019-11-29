import { Injectable } from '@angular/core';
import {HttpClient, HttpErrorResponse, HttpHeaders} from '@angular/common/http';
import {catchError} from 'rxjs/internal/operators/catchError';
import {map} from 'rxjs/operators';
import {VaderFormDto} from '../models/vader-form.dto';
import {VaderAnswerDto} from '../models/vader-answer.dto';
import {AnotherUser} from '../models/anotherUser';




const httpOptions = {
  headers: new HttpHeaders({
    'Content-Type':  'application/json',
    'Authorization': 'my-auth-token'
  })
};

@Injectable({
  providedIn: 'root'
})
export class VaderService {

  constructor(private http: HttpClient) {
  }

  addVaderForm(vaderFormDto: VaderFormDto) {
  return this.http.post<AnotherUser[]>("//localhost:8080/new", vaderFormDto);

  }


  getUser() {
    return this.http.get<AnotherUser[]>("//localhost:8080/json");
  }
}
