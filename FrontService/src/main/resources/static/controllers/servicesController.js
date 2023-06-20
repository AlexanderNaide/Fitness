angular.module('fitness').controller('servicesController', function ($scope, $http, $localStorage) {
    const contextPath = 'http://localhost:3881/fitness/api/v1';

    // $scope.loadMaintenance = function () {
    //     $http({
    //         url: contextPathMaintenance + "/maintenance",
    //         method: 'POST'
    //     }).then(function (response) {
    //         // $scope.MaintenanceList = response.data.content;
    //         $scope.MaintenanceList = response.data;
    //         console.log(response.data)
    //     });
    // };

    // $scope.loadMaintenance();

});