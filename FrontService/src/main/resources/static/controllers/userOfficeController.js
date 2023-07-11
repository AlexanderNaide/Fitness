angular.module('fitness').
controller('userOfficeController', function ($rootScope, $scope, $http, $localStorage, $compile, $element, $location) {
    $scope.OfficeOwner = undefined;
    const contextPath = 'http://localhost:3881/fitness/api/v1/user';
    let pageClass = 'user_menu';


    let number = 1;
    let totalNumber;

    if($localStorage.officeOwner.role !== "user"){
        $scope.goToOffice();
    }

    // Преднастройки страницы
    $scope.setStylesOffice = function () {
        document.getElementById('cssId1').href = 'styles/office.css';
        // document.getElementById('cssId2').href = 'styles/elements_responsive.css';
        // document.getElementById('cssId1').href = 'styles/services.css';
        document.getElementById('cssId2').href = 'styles/services_responsive.css';

        // $('.home_linc').removeClass('active');

        // $('.about_linc').removeClass('active');
        // $('.services_linc').removeClass('active');
        // $('.blog_linc').removeClass('active');
        // $('.contact_linc').removeClass('active');
        // document.getElementById('a').style.backgroundImage="url(images/img.jpg)"; // specify the image path here
        jQuery(window).trigger('resize').trigger('scroll');
        document.getElementById('office_heading').style.backgroundImage="url(../images/contact.jpg)";
        // $scope.refreshUserMenu();
    };

    $scope.setOfficeOwner = function () {
        $scope.OfficeOwner = {
            username: $localStorage.officeOwner.username,
            surname: $localStorage.officeOwner.surname
        };
    };

    $scope.test = function () {
        console.log("start test");
        $rootScope.$emit('testEvent', pageClass, (page) => {
            console.log(page);
        });
    };

    function createUserMenu(header) {
    // $scope.createUserMenu = function (header) {
        // let header = $('.lower_header_content');
        // header.removeClass('side_menu');
        // let side =$('<div class="linc"><a href="#!/" ng-click="goToSide()">На сайт</a></div>');


        console.log("start createUserMenu");
        // let side =$('<div class="linc"><a href="#!/">На сайт</a></div>');
        // $compile(side)($scope);
        // side.appendTo($element);
        let side =$('<div class="linc home_linc"><a href="/">На сайт</a></div>');
        let information =$('<div class="linc active"><a href="/schedule">Занятия</a></div>');
        let services =$('<div class="linc"><a href="/schedule">Абонемент</a></div>');
        let info =$('<div class="linc"><a href="/schedule">Информация</a></div>');
        for (let ch of header.children()) {
            ch.remove();
        }
        header.append(side);
        header.append(information);
        header.append(services);
        header.append(info);


        // header.addClass('office_user_menu');
    }

    $scope.refreshUserMenu = function () {
        // $scope.$parent.refreshMenu($scope.createUserMenu, pageClass);
        $rootScope.$emit('refreshMenu', createUserMenu, pageClass);

        // let header = $('.lower_header_content');
        //
        // let side =$('<div class="linc" ng-click="goToSide()"><a href="#!/">На сайт</a></div>');
        // $compile(side)($scope);
        // side.appendTo($element);
        // let information =$('<div class="linc active"><a href="#!/schedule">Занятия</a></div>');
        // let services =$('<div class="linc"><a href="#!/schedule">Абонемент</a></div>');
        // let info =$('<div class="linc"><a href="#!/schedule">Информация</a></div>');
        //
        // await $scope.slow(header);
        //
        // for (let ch of header.children()) {
        //     ch.remove();
        // }
        //
        // header.append(side);
        // header.append(information);
        // header.append(services);
        // header.append(info);
        // header.addClass('office_user_menu');
        //
        // $scope.slow(header);
    };

    $scope.goToSide = function () {
        // console.log("go");
        // $location.path('/');
        // $scope.refreshSideMenu();
    };


    /***********************************
     * Управление таблицей пользователей
     ***********************************/

    // $scope.loadUsers = function () {
    //     $http({
    //         url: contextPath + "/list",
    //         method: 'GET'
    //     }).then(function (response) {
    //         // console.log(response.data)
    //         number = 1;
    //         $scope.pagination(response);
    //         $scope.UserList = response.data.content;
    //     });
    // };

    // $scope.updateUsers = function () {
    //     $http({
    //         url: contextPath + "/updates",
    //         method: 'POST',
    //         params: {
    //             val: $scope.value !== null ? $scope.value : null,
    //             role: $scope.filter.role !== null ? $scope.filter.role.id : null,
    //             specialization: $scope.filter.specialization !== undefined ? $scope.filter.specialization.id : null,
    //             page: number
    //         }
    //     }).then(function (response) {
    //         $scope.pagination(response);
    //         $scope.UserList = response.data.content;
    //         // console.log(response.data)
    //     });
    // };

    // $scope.pagination = function (response) {
    //     totalNumber = response.data.totalPages;
    //     $scope.totalNumber = response.data.totalPages;
    //     $scope.first = response.data.first === true ? 'pagination_non_active' : 'pagination_active';
    //     $scope.first10 = response.data.number < 10 ? 'pagination_non_active' : 'pagination_active';
    //     $scope.page1 = response.data.number + 1;
    //     $scope.last10 = response.data.number > totalNumber - 11 ? 'pagination_non_active' : 'pagination_active';
    //     $scope.last = response.data.last === true ? 'pagination_non_active' : 'pagination_active';
    // };

    // $scope.pageClick = function (delta) {
    //     number = number + delta;
    //     $scope.updateUsers();
    // };

    // $scope.pageStart = function () {
    //     number = 1;
    //     $scope.updateUsers();
    // };

    // $scope.pageFinish = function () {
    //     number = totalNumber;
    //     $scope.updateUsers();
    // };

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

    // $scope.getSpecializations = function (id) {
    //     $http({
    //         url: contextPath + "/spec_one",
    //         method: 'POST',
    //         params: {
    //             id: id
    //         }
    //     }).then(function (response) {
    //         // $scope.SpecializationList = response.data.content;
    //         $scope.Specialization = response.data;
    //     });
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
    // if(!$('.lower_header_content').hasClass('office_user_menu')){
    //     $scope.refreshUserMenu().then();
    // }

    $scope.setStylesOffice();
    $scope.setOfficeOwner();
    $scope.refreshUserMenu();
    // $scope.OwnerPath();
    // $scope.getRoleList();
    // $scope.loadUsers();

});