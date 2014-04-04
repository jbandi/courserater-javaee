

angular.module('courserater').controller('EditRatingController', function($scope, $routeParams, $location, RatingResource ) {
    var self = this;
    $scope.disabled = false;
    $scope.$location = $location;
    
    $scope.get = function() {
        var successCallback = function(data){
            self.original = data;
            $scope.rating = new RatingResource(self.original);
        };
        var errorCallback = function() {
            $location.path("/Ratings");
        };
        RatingResource.get({RatingId:$routeParams.RatingId}, successCallback, errorCallback);
    };

    $scope.isClean = function() {
        return angular.equals(self.original, $scope.rating);
    };

    $scope.save = function() {
        var successCallback = function(){
            $scope.get();
            $scope.displayError = false;
        };
        var errorCallback = function() {
            $scope.displayError=true;
        };
        $scope.rating.$update(successCallback, errorCallback);
    };

    $scope.cancel = function() {
        $location.path("/Ratings");
    };

    $scope.remove = function() {
        var successCallback = function() {
            $location.path("/Ratings");
            $scope.displayError = false;
        };
        var errorCallback = function() {
            $scope.displayError=true;
        }; 
        $scope.rating.$remove(successCallback, errorCallback);
    };
    
    
    $scope.get();
});