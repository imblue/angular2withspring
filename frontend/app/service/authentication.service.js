System.register(["angular2/core", "angular2/http", 'rxjs/Rx', "../config/properties"], function(exports_1, context_1) {
    "use strict";
    var __moduleName = context_1 && context_1.id;
    var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
        var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
        if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
        else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
        return c > 3 && r && Object.defineProperty(target, key, r), r;
    };
    var __metadata = (this && this.__metadata) || function (k, v) {
        if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
    };
    var core_1, http_1, Rx_1, properties_1;
    var AuthenticationService;
    return {
        setters:[
            function (core_1_1) {
                core_1 = core_1_1;
            },
            function (http_1_1) {
                http_1 = http_1_1;
            },
            function (Rx_1_1) {
                Rx_1 = Rx_1_1;
            },
            function (properties_1_1) {
                properties_1 = properties_1_1;
            }],
        execute: function() {
            AuthenticationService = (function () {
                function AuthenticationService(_http) {
                    this._http = _http;
                }
                AuthenticationService.prototype.login = function (username, password) {
                    var _this = this;
                    var headers = new http_1.Headers();
                    headers.append('Content-Type', 'application/json');
                    var body = {
                        username: username,
                        password: password
                    };
                    this._http.post(properties_1.Properties.API + '/authentication/authenticate', JSON.stringify(body), {
                        headers: headers
                    })
                        .map(function (res) { return res.json(); })
                        .subscribe(function (data) { return _this.loginSuccessHandler(data); }, function (err) { return _this.loginErrorHandler(err); });
                };
                AuthenticationService.prototype.getUserInfo = function () {
                    var _this = this;
                    var headers = new http_1.Headers();
                    headers.append('Content-Type', 'application/json');
                    this._http.get(properties_1.Properties.API + '/authentication', {
                        headers: headers
                    })
                        .map(function (res) { return res.json(); })
                        .subscribe(function (data) { return _this.userSuccessHandler(data); }, function (err) { return _this.loginErrorHandler(err); });
                };
                AuthenticationService.prototype.loginSuccessHandler = function (res) {
                    if (!res.token) {
                        return Rx_1.Observable.throw("no token set");
                    }
                    console.log("Successfully logged in. Token: ", res.token);
                    this.getUserInfo();
                    return res.token;
                };
                AuthenticationService.prototype.loginErrorHandler = function (error) {
                    if (error.status == 401) {
                        return Rx_1.Observable.throw("Wrong username/password");
                    }
                    console.error(error);
                    return Rx_1.Observable.throw("Connection error");
                };
                AuthenticationService.prototype.userSuccessHandler = function (res) {
                    this.userId = res.id;
                    return res.id;
                };
                AuthenticationService = __decorate([
                    core_1.Injectable(), 
                    __metadata('design:paramtypes', [http_1.Http])
                ], AuthenticationService);
                return AuthenticationService;
            }());
            exports_1("AuthenticationService", AuthenticationService);
        }
    }
});
//# sourceMappingURL=authentication.service.js.map