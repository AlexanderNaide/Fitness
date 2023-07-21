angular.module('fitness').controller('adminOfficeController', function ($rootScope, $scope, $http, $localStorage, $compile, $element) {
    const contextPath = 'http://localhost:3881/fitness/api/v1/admin';
    let pageClass = 'admin_menu';

    console.log("adminOfficeController");

    if ($localStorage.officeOwner) {
        try {
            let jwt = $localStorage.officeOwner.token;
            let payload = JSON.parse(atob(jwt.split('.')[1]));
            let currentTime = parseInt(new Date().getTime() / 1000);
            if (currentTime > payload.exp) {
                console.log("Время жизни токена истекло");
                delete $localStorage.officeOwner;
                $http.defaults.headers.common.Authorization = '';
                $location.path('/')
            } else {
                $http.defaults.headers.common.Authorization = 'Bearer ' + $localStorage.officeOwner.token;
            }
        } catch (e) {
        }
    }

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
        // document.getElementById('office_heading').style.backgroundImage="url(../images/contact.jpg)";
    };

    function createAdminMenu(header) {
        let side =$('<div class="linc"><a href="#!/" ng-click="refreshSideMenu()">На сайт</a></div>');
        $compile(side)($scope);
        side.appendTo($element);
        // let side =$('<div class="linc home_linc"><a href="/">На сайт</a></div>');
        let clients =$('<div class="linc active"><a href="#!/clients">Клиенты</a></div>');
        let personal =$('<div class="linc"><a href="#!/personal">Персонал</a></div>');
        let schedule =$('<div class="linc"><a href="#!/schedule">Расписания</a></div>');
        // let info =$('<div class="linc"><a href="" ui-sref="schedule">Информация</a></div>');
        for (let ch of header.children()) {
            ch.remove();
        }
        header.append(side);
        header.append(clients);
        header.append(personal);
        header.append(schedule);
    }

    $scope.refreshAdminMenu = function () {
        $rootScope.$emit('refreshMenu', createAdminMenu, pageClass);
    };

    $scope.setStylesOffice();
    $scope.refreshAdminMenu();
});