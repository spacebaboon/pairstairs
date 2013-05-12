'use strict';

/* Controllers */

function PairingListCtrl($scope, $http) {
    $http.get('/pairstairs/team/pairings').success(function(data) {
        $scope.pairings = data;
    });

//    $scope.orderProp = 'age';
}


//angular.module('myApp.controllers', []).
//  controller('MyCtrl1', [function() {
//
//  }])
//  .controller('MyCtrl2', [function() {
//
//  }]);