angular.module('fitness').controller('officeController', function ($scope, $http, $localStorage) {
    const contextPath = 'http://localhost:3881/fitness/api/v1';
    let number = 1;
    let totalNumber;
    $scope.OfficeOwner = null;

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


        // $('.background_image').setAttribute('background-image', '../images/contact.jpg');
        // $('.background_image').style.backgroundImage = '../images/contact.jpg';
    };

    $scope.OwnerPath = function () {
        if($scope.OfficeOwner == null){
            $http.post(contextPath + '/auth', $scope.auth)
                .then(function (response) {
                    // console.log(response.data);
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

    $scope.loadUsers = function () {
        $http({
            url: contextPath + "/auth/list",
            method: 'GET'
        }).then(function (response) {
            // console.log(response.data)
            $scope.pagination(response);
            $scope.UserList = response.data.content;
        });
    };

    $scope.updateUsers = function () {
        let bf;
        let role;
        let specialization;
        // if ($scope.filter !== null){
        //     bf = true;
        //     role = $scope.filter.role !== undefined ? $scope.filter.role : null;
        //     specialization = $scope.filter.specialization !== undefined ? $scope.filter.specialization : null;
        // }
        $http({
            url: contextPath + "/auth/updates",
            method: 'POST',
            params: {
                val: $scope.value !== null ? $scope.value : null,
                role: bf ? role : null,
                specialization: bf ? specialization : null,
                page: number
            }
        }).then(function (response) {
            $scope.pagination(response);
            $scope.UserList = response.data.content;
            // console.log(response.data)
        });
    };

    $scope.pagination = function (response) {
        totalNumber = response.data.totalPages;
        $scope.totalNumber = response.data.totalPages;
        $scope.first = response.data.first === true ? 'pagination_non_active' : 'pagination_active';
        $scope.first10 = response.data.number < 10 ? 'pagination_non_active' : 'pagination_active';
        $scope.page1 = response.data.number + 1;
        $scope.last10 = response.data.number > totalNumber - 11 ? 'pagination_non_active' : 'pagination_active';
        $scope.last = response.data.last === true ? 'pagination_non_active' : 'pagination_active';
    };

    $scope.pageClick = function (delta) {
        number = number + delta;
        $scope.updateUsers();
    };

    $scope.pageStart = function () {
        number = 1;
        $scope.updateUsers();
    };

    $scope.pageFinish = function () {
        number = totalNumber;
        $scope.updateUsers();
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
    $scope.loadUsers();

});