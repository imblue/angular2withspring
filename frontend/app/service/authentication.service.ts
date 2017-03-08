/**
 * Created by Christian Schuhmacher on 29.04.2016.
 */
import {Injectable} from "angular2/core";
import {Http, Headers} from "angular2/http";
import {Observable, Observer, Subject} from 'rxjs/Rx';
import {Properties} from "../config/properties";

export interface Message {
    id: number;
    toUserId: number;
    fromUserId: number;
    message: string;
    timestamp: Date;
}

@Injectable()
export class AuthenticationService {



    userId: number;
    private subject: Subject<MessageEvent>;

    constructor(private _http: Http) {
    }

    login(username: string, password: string) {
        var headers = new Headers();
        headers.append('Content-Type', 'application/json');

        var body = {
            username: username,
            password: password
        };

        this._http.post(Properties.API + '/authentication/authenticate', JSON.stringify(body), {
            headers: headers
        })
            .map(res => res.json())
            .subscribe(
                data => this.loginSuccessHandler(data),
                err => this.loginErrorHandler(err)
            );
    }
y

    getUserInfo() {
        var headers = new Headers();
        headers.append('Content-Type', 'application/json');

        this._http.get(Properties.API + '/authentication', {
            headers: headers
        })
            .map(res => res.json())
            .subscribe(
                data => this.userSuccessHandler(data),
                err => this.loginErrorHandler(err)
            );
    }


    private loginSuccessHandler(res: {token}) {
        if (!res.token) {
            return Observable.throw("no token set");
        }
        console.log("Successfully logged in. Token: ", res.token);
        this.getUserInfo();
        return res.token;
    }

    private loginErrorHandler(error: any) {
        if (error.status == 401) {
            return Observable.throw("Wrong username/password");
        }
        console.error(error);
        return Observable.throw("Connection error");
    }

    private userSuccessHandler(res) {
        this.userId = res.id;
        return res.id;
    }
}