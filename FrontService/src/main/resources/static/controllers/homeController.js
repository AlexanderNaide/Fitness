angular.module('fitness').controller('homeController', function ($scope, $http, $localStorage) {
    const contextPath = 'http://localhost:3881/fitness/api/v1';
    // $(document).setStyle("styles/main_styles.css");

    $scope.setStylesMain = function () {
        var head  = document.getElementsByTagName('head')[0];
        var link  = document.createElement('link');
        link.id   = 'cssId1';
        link.rel  = 'stylesheet';
        link.type = 'text/css';
        link.href = 'styles/main_styles.css';
        head.appendChild(link);
        link  = document.createElement('link');
        link.id   = 'cssId2';
        link.rel  = 'stylesheet';
        link.type = 'text/css';
        link.href = 'styles/responsive.css';
        head.appendChild(link);
        console.log(document.getElementsByTagName('head')[0])
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
    $scope.setStylesMain();
});