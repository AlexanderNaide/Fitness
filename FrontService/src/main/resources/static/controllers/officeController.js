angular.module('fitness').controller('officeController', function ($scope, $http, $localStorage) {
    const contextPath = 'http://localhost:3881/fitness/api/v1';

    $scope.setStylesOffice = function () {

        document.getElementById('cssId1').href = 'styles/office.css';
        document.getElementById('cssId2').href = 'styles/elements_responsive.css';

        // for (let i = 0; i < document.scripts.length; i++) {
        //     document.scripts.item(i).remove();
        // }

        $('.home_linc').removeClass('active');
        $('.about_linc').removeClass('active');
        $('.services_linc').removeClass('active');
        $('.blog_linc').removeClass('active');
        $('.contact_linc').removeClass('active');
    };

    $scope.OwnerPath = function () {
        if($scope.OfficeOwner == null){
            $http.post(contextPath + '/auth', $scope.auth)
                .then(function (response) {
                    console.log(response.data);
                    if(response.data){
                        // $scope.buttonCart();
                        $('#authRes').click();
                        $scope.officeOwner = response.data;
                    }
                }).catch(function (response) {
                // console.log(response.data.message)
                $scope.modalStatus = response.data.message;
            });
        }
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
    $scope.setStylesOffice();
    $scope.OwnerPath();

});