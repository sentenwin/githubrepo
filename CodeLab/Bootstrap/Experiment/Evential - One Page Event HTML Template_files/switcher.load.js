/*
 * Smart Demo Switcher v1.5
 * http://www.smartplugins.info/plugin/javascript/smart-demo-switcher//
 * 
 * Copyright 2008 - 2014 Milan Petrovic (email: milan@gdragon.info)
 *
 * http://www.dev4press.com
 * http://www.millan.rs
 *
 */

var smartDemoSwitcherObj;

;(function ($, window, document, undefined) {
    smartDemoSwitcher.Loader = smartDemoSwitcher.Load.extend({
        display: {
            style: "light",
            location: "left",
            buttonContent: '<i class="fa fa-gear"></i>',
            formHeaderContent: '<h5>EVENTIAL</h5>'
        },
        stylesheets: {
            main: {
                columns: 2,
                boxFactor: .5,
                title: true,
                titleContent: "<h5>Styles</h5>",
                selector: "#main-style",
                default: 'css/color/green.css',
                list: [
                    {file: 'css/color/green.css', name: 'Green', colors: ['#00a99d']},
                    {file: 'css/color/blue.css', name: 'Blue', colors: ['#3498db']},
                    {file: 'css/color/red.css', name: 'Red', colors: ['#e74c3c']},
                    {file: 'css/color/yellow.css', name: 'Yellow', colors: ['#ffaa33']},
					{file: 'css/color/darkblue.css', name: 'Darkblue', colors: ['#34495e']},
					{file: 'css/color/purple.css', name: 'Purple', colors: ['#9b59b6']}
					
                ]
            }
        },
    });

    $(document).ready(function() {
        smartDemoSwitcherObj = new smartDemoSwitcher.Core();
    });
})(jQuery, window, document);
