(function () {
    angular
        .module('admin', ['ui.router', 'ngRoute', 'ngStorage'])
        .config(function($stateProvider, $urlRouterProvider, $locationProvider ){

            $urlRouterProvider.otherwise("/clients");

            $stateProvider
                .state('clients', {
                    url: '/clients',
                    templateUrl: '../../pages/admin/admin_clients.html',
                    controller: 'adminClientsController'
                })
                .state('personal', {
                    url: '/personal',
                    templateUrl: '../../pages/admin/admin_personal.html',
                    // controller: 'adminOfficeController'
                })
                .state('schedule', {
                    url: '/schedule',
                    templateUrl: '../../pages/admin/admin_schedule.html',
                    controller: 'adminScheduleController'
                })
        })
        .run(run);

    function run($rootScope, $http, $localStorage, $location) {
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
    }
})();
