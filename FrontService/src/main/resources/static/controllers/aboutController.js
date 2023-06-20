angular.module('fitness').controller('aboutController', function ($scope, $http, $localStorage) {
    const contextPath = 'http://localhost:3881/fitness/api/v1';

    $scope.setStylesAbout = function () {

        document.getElementById('cssId1').href = 'styles/about.css';
        document.getElementById('cssId2').href = 'styles/about_responsive.css';

        for (let i = 0; i < document.scripts.length; i++) {
            document.scripts.item(i).remove();
        }

        // $(this).removeData('image');
        // $(this).removeData('src');
        // $(this).removeData();
        // $(this).cleanData();


        $('#menuAboutId').addClass('active');
        $('#menuServicesId').removeClass('active');
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
    $scope.setStylesAbout();

});