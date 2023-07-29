angular.module('admin').controller('adminClientsController', function ($scope, $http, $localStorage) {
    const contextPath = 'http://localhost:3881/fitness/api/v1/admin';
    console.log('adminClientsController');
    let number = 1;
    let totalNumber;
    $scope.User = {
        name: '',
        surname: '',
        key: '',
        phone: '',
        email: ''
    };


    /***********************************
     * Управление таблицей пользователей
     ***********************************/

/*    $scope.loadUsers = function () {
        $http({
            url: contextPath + "/list",
            method: 'GET'
        }).then(function (response) {
            console.log(response.data)
            number = 1;
            $scope.pagination(response);
            $scope.UserList = response.data.content;
        });
    };*/

    $scope.updateUsers = function () {
        $http({
            url: contextPath + "/users",
            method: 'POST',
            params: {
                page: number,
                name: $scope.User.name,
                surname: $scope.User.surname,
                login: $scope.User.key,
                phone: $scope.User.phone,
                email: $scope.User.email
            }
        }).then(function (response) {
            $scope.pagination(response);
            $scope.UserList = response.data.content;
            // console.log(response.data)
        }).catch(function (response) {
            $scope.message = response.data.message;
            $('.formMessage').addClass('alarmMessage');
        });
    };

    $scope.pagination = function (response) {
        totalNumber = response.data.totalPages;
        $scope.totalNumber = response.data.totalPages;
        $scope.first = response.data.first === true ? 'page-item disabled' : 'page-item';
        $scope.first10 = response.data.number < 10 ? 'page-item disabled' : 'page-item';
        $scope.page1 = response.data.number + 1;
        $scope.last10 = response.data.number > totalNumber - 11 ? 'page-item disabled' : 'page-item';
        $scope.last = response.data.last === true ? 'page-item disabled' : 'page-item';
    };

    $scope.getUser = function (id) {
        $http({
            url: contextPath + "/user",
            method: 'POST',
            params: {
                id: id
            }
        }).then(function (response) {
            $scope.User = response.data;
        }).catch(function (response) {
            $scope.message = response.data.message;
            $('.formMessage').addClass('alarmMessage');
        });
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

    $scope.changes = function () {
        number = 1;
        $scope.setMessage();
        $scope.updateUsers();
    };

    $scope.resetChanges = function () {
        $scope.User.key = '';
        $scope.User.name = '';
        $scope.User.surname = '';
        $scope.User.phone = '';
        $scope.User.email = '';
        // $scope.User.birthday = '';
        number = 1;
        $scope.setMessage();
        $scope.updateUsers();
    };

    $scope.setMessage = function () {
        $('.formMessage').removeClass('alarmMessage');
        $scope.message = 'Введите данные';
    };

    // $scope.getRoleList = function () {
    //     $http({
    //         url: contextPath + "/role_list",
    //         method: 'POST'
    //     }).then(function (response) {
    //         $scope.RoleList = response.data;
    //     });
    // };

    // $scope.roleChange = function () {
    //     if($scope.filter.role === null){
    //         $scope.filter.specialization = undefined;
    //     } else {
    //         if($scope.filter.role.containsSpecializations){
    //             $('#spec').prop('disabled', false);
    //             $http({
    //                 url: contextPath + "/specialization_list",
    //                 method: 'POST',
    //             }).then(function (response) {
    //                 if(response.data.length === 0){
    //                     $scope.SpecializationList = null;
    //                     $('#spec').prop( 'disabled', true);
    //                 } else {
    //                     $scope.SpecializationList = response.data;
    //                 }
    //             });
    //         } else {
    //             $scope.filter.specialization = undefined;
    //             $scope.SpecializationList = null;
    //             $('#spec').prop( 'disabled', true);
    //         }
    //         $scope.resetAndUpdateUsers();
    //     }
    // };

    // $scope.specializationChange = function () {
    //     if($scope.filter.specialization === null){
    //         $scope.filter.specialization = undefined;
    //     }
    //     $scope.resetAndUpdateUsers();
    // };

    // $scope.resetAndUpdateUsers = function () {
    //     number = 1;
    //     $scope.updateUsers();
    // };


    /***********************************
     * Управление таблицей специализаций
     ***********************************/

    // $scope.loadSpecializations = function (pageNumber) {
    //     number = pageNumber;
    //     $http({
    //         url: contextPath + "/spec_list",
    //         method: 'POST',
    //         params: {
    //             page: pageNumber
    //         }
    //     }).then(function (response) {
    //         // console.log(response);
    //         $scope.pagination(response);
    //         $scope.SpecializationList = response.data.content;
    //     });
    // };

    // $scope.pageSpecClick = function (delta) {
    //     $scope.loadSpecializations(number + delta);
    // };






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
    // $scope.setStylesOffice();
    // $scope.setOfficeOwner();
    // $scope.OwnerPath();
    // $scope.getRoleList();
    $scope.setMessage();
    $scope.updateUsers();

});