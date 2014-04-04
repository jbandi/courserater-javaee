
angular.module('courserater').controller('NewRatingController', function ($scope, $location, locationParser, RatingResource ) {
    $scope.disabled = false;
    $scope.$location = $location;
    $scope.rating = $scope.rating || {};
    

    $scope.save = function() {
        var successCallback = function(data,responseHeaders){
            var id = locationParser(responseHeaders);
            $location.path('/Ratings/edit/' + id);
            $scope.displayError = false;
        };
        var errorCallback = function() {
            $scope.displayError = true;
        };
        RatingResource.save($scope.rating, successCallback, errorCallback);
    };
    
    $scope.cancel = function() {
        $location.path("/Ratings");
    };
});