var sideMenuApp = angular.module('sideMenuApp', ['ionic', 'sideMenuApp.controllers', 'sideMenuApp.services']);

sideMenuApp.config(function ($compileProvider) {
    $compileProvider.aHrefSanitizationWhitelist(/^\s*(https?|ftp|mailto|file|tel):/);
})

sideMenuApp.config(['$stateProvider', '$urlRouterProvider',
    function($stateProvider, $urlRouterProvider) {
    // Ionic uses AngularUI Router which uses the concept of states
    // Learn more here: https://github.com/angular-ui/ui-router
    // Set up the various states which the app can be in.
    // Each state's controller can be found in controllers.js
    $stateProvider

        .state('one', {
            url: '/one',
            controller: 'OneController',
            templateUrl: 'templates/one.html'
        })
        .state('two', {
            url: '/two',
            controller: 'TwoController',
            templateUrl: 'templates/two.html'
        })
        .state('three', {
            url: '/three',
            controller: 'ThreeController',
            templateUrl: 'templates/three.html'
        });

    // if none of the above states are matched, use this as the fallback
    $urlRouterProvider.otherwise('/one');
    }
])