'use strict';

angular.module('courserater',['ngRoute','ngResource'])
  .config(['$routeProvider', function($routeProvider) {
    $routeProvider
      .when('/',{templateUrl:'views/landing.html',controller:'LandingPageController'})
      .when('/Ratings',{templateUrl:'views/Rating/search.html',controller:'SearchRatingController'})
      .when('/Ratings/new',{templateUrl:'views/Rating/detail.html',controller:'NewRatingController'})
      .when('/Ratings/edit/:RatingId',{templateUrl:'views/Rating/detail.html',controller:'EditRatingController'})
      .otherwise({
        redirectTo: '/'
      });
  }])
  .controller('LandingPageController', function LandingPageController() {
  })
  .controller('NavController', function NavController($scope, $location) {
    $scope.matchesRoute = function(route) {
        var path = $location.path();
        return (path === ("/" + route) || path.indexOf("/" + route + "/") == 0);
    };
  });
