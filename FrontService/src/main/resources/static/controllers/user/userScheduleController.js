angular.module('fitness').controller('userScheduleController', function ($scope, $http, $localStorage) {
    const contextPath = 'http://localhost:3881/fitness/api/v1/user';

    // Преднастройки страницы
    $scope.setStylesOffice = function () {
        document.getElementById('cssId1').href = 'styles/office.css';
        // document.getElementById('cssId2').href = 'styles/elements_responsive.css';
        // document.getElementById('cssId1').href = 'styles/services.css';
        document.getElementById('cssId2').href = 'styles/services_responsive.css';


        // $scope.refreshMenu();

        // $('.home_linc').removeClass('active');
        // $('.about_linc').removeClass('active');
        // $('.services_linc').removeClass('active');
        // $('.blog_linc').removeClass('active');
        // $('.contact_linc').removeClass('active');
        // document.getElementById('a').style.backgroundImage="url(images/img.jpg)"; // specify the image path here
        jQuery(window).trigger('resize').trigger('scroll');
        document.getElementById('office_heading').style.backgroundImage="url(../images/contact.jpg)";
    };

    $scope.refreshMenu = async function () {
        let header = $('.lower_header_content');

        let information = document.createElement('div');
        information.classList.add('linc');
        let informationLinc = document.createElement('a');
        informationLinc.setAttribute('href', "yourlink.htm");
        informationLinc.textContent = "Занятия";
        information.append(informationLinc);

        let services = document.createElement('div');
        services.classList.add('linc');
        let servicesLinc = document.createElement('a');
        servicesLinc.setAttribute('href', "yourlink.htm");
        servicesLinc.textContent = "Абонемент";
        services.append(servicesLinc);

        let info = document.createElement('div');
        info.classList.add('linc');
        let infoLinc = document.createElement('a');
        infoLinc.setAttribute('href', "yourlink.htm");
        infoLinc.textContent = "Информация";
        info.append(infoLinc);

        await $scope.slow(header);
        for (let ch of header.children()) {
            ch.remove();
        }

        header.append(information);
        header.append(services);
        header.append(info);

        $scope.slow(header);
    };

    $scope.slow = function (header) {
        if (header.hasClass('visible')){
            header.animate({"right":"-4000px"}, "slow").removeClass('visible');
        } else {
            header.animate({"right":"0px"}, "slow").addClass('visible');
        }
    };

    $scope.test = function () {
        $scope.refreshMenu();
    };


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
            url: contextPath + "/spec_list",
            method: 'POST',
            params: {
                page: pageNumber
            }
        }).then(function (response) {
            // console.log(response);
            $scope.pagination(response);
            $scope.SpecializationList = response.data.content;
        });
    };

    $scope.pageSpecClick = function (delta) {
        $scope.loadSpecializations(number + delta);
    };

    $scope.getSpecializations = function (id) {
        $http({
            url: contextPath + "/spec_one",
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