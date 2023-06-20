angular.module('fitness').controller('aboutController', function ($scope, $http, $localStorage) {
    const contextPath = 'http://localhost:3881/fitness/api/v1';

    $scope.setStylesMain = function () {
        // var head  = document.getElementsByTagName('head')[0];
        // var link  = document.createElement('link');
        var link  = document.getElementById('cssId1');

        // link.id   = 'cssId1';
        // link.rel  = 'stylesheet';
        // link.type = 'text/css';
        link.href = 'styles/about.css';
        // head.appendChild(link);
        // link  = document.createElement('link');
        link  = document.getElementById('cssId2');
        // link.id   = 'cssId2';
        // link.rel  = 'stylesheet';
        // link.type = 'text/css';
        link.href = 'styles/about_responsive.css';
        // head.appendChild(link);
        console.log(document.getElementsByTagName('head')[0])
    };

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
    $scope.setStylesMain();

});