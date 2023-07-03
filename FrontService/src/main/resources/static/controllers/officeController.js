angular.module('fitness').controller('officeController', function ($scope, $http, $localStorage) {
    $scope.OfficeOwner = undefined;
    const contextPath = 'http://localhost:3881/fitness/api/v1/super';
    let number = 1;
    let totalNumber;

    // Преднастройки страницы
    $scope.setStylesOffice = function () {
        document.getElementById('cssId1').href = 'styles/office.css';
        document.getElementById('cssId2').href = 'styles/elements_responsive.css';
        $('.home_linc').removeClass('active');
        $('.about_linc').removeClass('active');
        $('.services_linc').removeClass('active');
        $('.blog_linc').removeClass('active');
        $('.contact_linc').removeClass('active');
        // document.getElementById('a').style.backgroundImage="url(images/img.jpg)"; // specify the image path here
        jQuery(window).trigger('resize').trigger('scroll');
        document.getElementById('office_heading').style.backgroundImage="url(../images/contact.jpg)";
    };

    $scope.setOfficeOwner = function () {
        $scope.OfficeOwner = {
            username: $localStorage.officeOwner.username,
            surname: $localStorage.officeOwner.surname
        };
        console.log($scope.OfficeOwner.username);
        console.log($scope.OfficeOwner.surname);
        console.log($localStorage.officeOwner.username);
        console.log($localStorage.officeOwner.surname);
    };

    // $scope.OwnerPath = function () {
    //     if($scope.OfficeOwner == null){
    //         $http.post(contextPath + '/auth', $scope.auth)
    //             .then(function (response) {
    //                 // console.log(response.data);
    //                 // if(response.data){
    //                 //     // $scope.buttonCart();
    //                 //     $('#authRes').click();
    //                 //     $scope.officeOwner = response.data;
    //                 // }
    //             }).catch(function (response) {
    //             // console.log(response.data.message)
    //             $scope.modalStatus = response.data.message;
    //         });
    //     }
    // };


    /***********************************
     * Управление таблицей пользователей
     ***********************************/

    $scope.loadUsers = function () {
        $http({
            url: contextPath + "/list",
            method: 'GET'
        }).then(function (response) {
            // console.log(response.data)
            number = 1;
            $scope.pagination(response);
            $scope.UserList = response.data.content;
        });
    };

    $scope.updateUsers = function () {
        $http({
            url: contextPath + "/updates",
            method: 'POST',
            params: {
                val: $scope.value !== null ? $scope.value : null,
                role: $scope.filter.role !== null ? $scope.filter.role.id : null,
                specialization: $scope.filter.specialization !== undefined ? $scope.filter.specialization.id : null,
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

    $scope.getRoleList = function () {
        $http({
            url: contextPath + "/role_list",
            method: 'POST'
        }).then(function (response) {
            $scope.RoleList = response.data;
        });
    };

    $scope.roleChange = function () {
        if($scope.filter.role === null){
            $scope.filter.specialization = undefined;
        } else {
            if($scope.filter.role.containsSpecializations){
                $('#spec').prop('disabled', false);
                $http({
                    url: contextPath + "/specialization_list",
                    method: 'POST',
                }).then(function (response) {
                    if(response.data.length === 0){
                        $scope.SpecializationList = null;
                        $('#spec').prop( 'disabled', true);
                    } else {
                        $scope.SpecializationList = response.data;
                    }
                });
            } else {
                $scope.filter.specialization = undefined;
                $scope.SpecializationList = null;
                $('#spec').prop( 'disabled', true);
            }
            $scope.resetAndUpdateUsers();
        }
    };

    $scope.specializationChange = function () {
        if($scope.filter.specialization === null){
            $scope.filter.specialization = undefined;
        }
        $scope.resetAndUpdateUsers();
    };

    $scope.resetAndUpdateUsers = function () {
        number = 1;
        $scope.updateUsers();
    };


    /***********************************
     * Управление таблицей специализаций
     ***********************************/

    $scope.loadSpecializations = function (pageNumber) {
        number = pageNumber;
        $http({
            url: contextPath + "/fit/spec_list",
            method: 'POST',
            params: {
                page: pageNumber
            }
        }).then(function (response) {
            console.log(response);
            $scope.pagination(response);
            $scope.SpecializationList = response.data.content;
        });
    };

    $scope.pageSpecClick = function (delta) {
        $scope.loadSpecializations(number + delta);
    };

    $scope.getSpecializations = function (id) {
        $http({
            url: contextPath + "/fit/spec_one",
            method: 'POST',
            params: {
                id: id
            }
        }).then(function (response) {
            // $scope.SpecializationList = response.data.content;
            $scope.Specialization = response.data;
        });
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
    $scope.setOfficeOwner();
    // $scope.OwnerPath();
    $scope.getRoleList();
    $scope.loadUsers();

});