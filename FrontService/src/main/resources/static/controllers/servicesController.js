angular.module('fitness').controller('servicesController', function ($scope, $http, $localStorage) {
    const contextPath = 'http://localhost:3881/fitness/api/v1';

    $scope.setStylesServices = function () {

        document.getElementById('cssId1').href = 'styles/services.css';
        document.getElementById('cssId2').href = 'styles/services_responsive.css';

        for (let i = 0; i < document.scripts.length; i++) {
            document.scripts.item(i).remove();
        }

        // $('.team').cleanData();

        $('#menuAboutId').removeClass('active');
        $('#menuServicesId').addClass('active');
        $('#menuBlogId').removeClass('active');
        $('#menuContactId').removeClass('active');
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
    $scope.setStylesServices();

});