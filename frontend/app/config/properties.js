/**
 * Created by cschu on 06.05.2016.
 */
System.register([], function(exports_1, context_1) {
    "use strict";
    var __moduleName = context_1 && context_1.id;
    var Properties;
    return {
        setters:[],
        execute: function() {
            Properties = (function () {
                function Properties() {
                }
                Object.defineProperty(Properties, "API", {
                    get: function () { return 'http://localhost:8080'; },
                    enumerable: true,
                    configurable: true
                });
                return Properties;
            }());
            exports_1("Properties", Properties);
        }
    }
});
//# sourceMappingURL=properties.js.map