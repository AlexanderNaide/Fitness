angular.module('fitness').controller('contactController', function ($scope, $http, $localStorage) {
    const contextPath = 'http://localhost:3881/fitness/api/v1';

    $scope.setStylesAbout = function () {

        document.getElementById('cssId1').href = 'styles/contact.css';
        document.getElementById('cssId2').href = 'styles/contact_responsive.css';

        $('#menuAboutId').removeClass('active');
        $('#menuServicesId').removeClass('active');
        $('#menuBlogId').removeClass('active');
        $('#menuContactId').addClass('active');

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
    $scope.setStylesAbout();

});