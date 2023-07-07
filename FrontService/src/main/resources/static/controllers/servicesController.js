angular.module('fitness').controller('servicesController', function ($scope, $http, $localStorage) {
    const contextPath = 'http://localhost:3881/fitness/api/v1';

    $scope.setStylesServices = function () {

        document.getElementById('cssId1').href = 'styles/services.css';
        document.getElementById('cssId2').href = 'styles/services_responsive.css';

        jQuery(window).trigger('resize').trigger('scroll');

        $('.home_linc').removeClass('active');
        $('.about_linc').removeClass('active');
        $('.services_linc').addClass('active');
        $('.blog_linc').removeClass('active');
        $('.contact_linc').removeClass('active');

        console.log(window.location.href);
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