import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { first } from 'rxjs/operators';
import {AuthenticationService} from '../../services/authentication.service';
import {UserService} from '../../services/user.service';
import {AlertService} from '../../services/alert.service';
import {UserRegisterDto} from './userRegister.dto';



@Component({templateUrl: 'register.component.html'})
export class RegisterComponent implements OnInit {
  registerForm: FormGroup;
  loading = false;
  submitted = false;
  userRegisterDto: UserRegisterDto = new UserRegisterDto();

  constructor(
    private formBuilder: FormBuilder,
    private router: Router,
    private authenticationService: AuthenticationService,
    private userService: UserService,
    private alertService: AlertService
  ) {
    // redirect to home if already logged in
    if (this.authenticationService.currentUserValue) {
      this.router.navigate(['/']);
    }
  }

  ngOnInit() {
    this.registerForm = this.formBuilder.group({
      firstName: ['', Validators.required],
      lastName: ['', Validators.required],
      username: ['', Validators.required],
      password: ['', [Validators.required, Validators.minLength(6)]]
    });
  }

  // convenience getter for easy access to form fields
  get f() { return this.registerForm.controls; }

  onSubmit() {
    this.submitted = true;

    // stop here if form is invalid
    if (this.registerForm.invalid) {
      return;
    }

    // this.userLoginDto.username = this.f.username.value;
    // this.userLoginDto.password = this.f.password.value;

    this.userRegisterDto.firstName = this.f.firstName.value;
    this.userRegisterDto.lastName = this.f.lastName.value;
    this.userRegisterDto.username = this.f.username.value;
    this.userRegisterDto.password = this.f.password.value;

    this.loading = true;
    this.userService.register(this.userRegisterDto)
      .pipe(first())
      .subscribe(
        data => {
          console.log("Jestem w success! :):):)")
          console.log(data);
          this.alertService.success('Registration successful', true);
          this.router.navigate(['/login']);
        },
        error => {
          console.log("Jestem w error :(")
          console.log(error);
          this.alertService.error(error);
          this.loading = false;
        }
        );
  }
}
