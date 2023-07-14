angular.module('fitness').controller('userScheduleController', function ($scope, $http, $localStorage) {
    const contextPath = 'http://localhost:3881/fitness/api/v1/workout';

    // Преднастройки страницы
    $scope.setStylesOffice = function () {
        document.getElementById('cssId1').href = '../../styles/services.css';
        document.getElementById('cssId2').href = '../../styles/services_responsive.css';

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

    $scope.loadSchedule = function () {
        //TODO тут пересмотреть на необязательную отправку номера недели
        $http({
            url: contextPath + "/week",
            method: 'GET'
        }).then(function (response) {
            console.log(response.data)

            $scope.schedule = response.data;
        });
    };

    $scope.loadSchedule();

});