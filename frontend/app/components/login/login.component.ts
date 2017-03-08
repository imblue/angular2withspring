/**
 * Created by cschu on 29.04.2016.
 */
import {Component} from "angular2/core";
import {FormBuilder, Validators} from "angular2/common";
import {AuthenticationService} from "../../service/authentication.service";

@Component({
    selector: 'login',
    templateUrl: 'app/components/login/login.component.html',
    styleUrls: ['app/styles/login.css']
})
export class LoginComponent {
    credentials;

    constructor(fb: FormBuilder, private _authService: AuthenticationService) {
        this.credentials = fb.group({
            username: ["", Validators.required],
            password: ["", Validators.required],
            rememberMe: [""]
        })
    }

    login() {
        this._authService.login(this.credentials.value.username, this.credentials.value.password);
    }

    
}