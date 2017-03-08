/**
 * Created by Christian Schuhmacher on 26.04.2016.
 */
import {Component} from 'angular2/core';
import {LoginComponent} from "./components/login/login.component";
import {HomeComponent} from "./components/home/home.component";
import {RouteConfig, ROUTER_DIRECTIVES} from "angular2/router";
import {AuthenticationService} from "./service/authentication.service";
import {HTTP_PROVIDERS} from "angular2/http";

@Component({
    selector: 'app',
    directives: [ROUTER_DIRECTIVES],
    templateUrl: 'app/app.component.html',
    providers: [AuthenticationService, HTTP_PROVIDERS]
})
@RouteConfig([
    {
        path: "/home",
        name: "Home",
        component: HomeComponent,
        useAsDefault: true
    }, {
        path: "/login",
        name: "Login",
        component: LoginComponent
    }
])
export class AppComponent {

}
