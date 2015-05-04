cordova.define('cordova/plugin_list', function(require, exports, module) {
module.exports = [
    {
        "file": "plugins/com.rjfun.cordova.sms/www/SMS.js",
        "id": "com.rjfun.cordova.sms.SMS",
        "clobbers": [
            "window.SMS"
        ]
    }
];
module.exports.metadata = 
// TOP OF METADATA
{
    "com.rjfun.cordova.sms": "1.0.2"
}
// BOTTOM OF METADATA
});