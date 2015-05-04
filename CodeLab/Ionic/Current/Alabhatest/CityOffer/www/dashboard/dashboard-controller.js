'use strict';

angular.module('MyApp.controllers').controller('DashboardCtrl', 
  function($scope, $state, $firebase) {
 var ref = new Firebase("https://moneybytes.firebaseio.com/cityoffer");
        $scope.messages = $firebase(ref);
        $scope.name = "Anonymous";
        $scope.addMessage = function(e) {
           $scope.sendMsg = function() {
			    var today = new Date();
			    var dd = today.getDate();
			    var mm = today.getMonth()+1; //January is 0!

			    var yyyy = today.getFullYear();
			    if(dd<10){
			        dd='0'+dd
			    } 
			    if(mm<10){
			        mm='0'+mm
			    } 
			    var today = dd+'/'+mm+'/'+yyyy;

                  $scope.messages.$add({from: $scope.name, body: $scope.msg, date: today});
                  $scope.msg = "";
                }
        }
        $scope.clear = function(){
          $scope.name = "";
        }
  console.log('HomeTabCtrl');
  });
