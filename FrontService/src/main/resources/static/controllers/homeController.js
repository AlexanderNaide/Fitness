angular.module('fitness').controller('homeController', function ($scope, $http, $localStorage) {
    const contextPath = 'http://localhost:3881/fitness/api/v1';
    // $(document).setStyle("styles/main_styles.css");

    $scope.setStylesHome = function () {

        document.getElementById('cssId1').href = 'styles/main_styles.css';
        document.getElementById('cssId2').href = 'styles/responsive.css';

        $('#menuAboutId').removeClass('active');
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

    // $scope.setHeader = function () {
    //     // $scope.HeaderList = null;
    //     $(document).add(stylesheet, "styles/bootstrap-4.1.2/bootstrap.min.css");
    //
    //     $scope.HeaderList = "<link rel="stylesheet" type=\"text/css\" href=\"styles/bootstrap-4.1.2/bootstrap.min.css\">\n" +
    //         "<link href=\"plugins/font-awesome-4.7.0/css/font-awesome.min.css\" rel=\"stylesheet\" type=\"text/css\">\n" +
    //         "<link rel=\"stylesheet\" type=\"text/css\" href=\"plugins/OwlCarousel2-2.2.1/owl.carousel.css\">\n" +
    //         "<link rel=\"stylesheet\" type=\"text/css\" href=\"plugins/OwlCarousel2-2.2.1/owl.theme.default.css\">\n" +
    //         "<link rel=\"stylesheet\" type=\"text/css\" href=\"plugins/OwlCarousel2-2.2.1/animate.css\">\n" +
    //         "<link href=\"plugins/colorbox/colorbox.css\" rel=\"stylesheet\" type=\"text/css\">\n" +
    //         "<link rel=\"stylesheet\" type=\"text/css\" href=\"styles/main_styles.css\">\n" +
    //         "<link rel=\"stylesheet\" type=\"text/css\" href=\"styles/responsive.css\">";
    // };

    // $scope.loadMaintenance();
    // $scope.setHeader();
    $scope.setStylesHome();
});