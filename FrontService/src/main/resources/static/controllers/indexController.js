(function () {
    angular
        .module('fitness', ['ngRoute', 'ngStorage'])
        .config(config)
        // .configuration(config)
        .run(run);


    function config($routeProvider){
        $routeProvider
            .when('/user_office', {
                templateUrl: 'pages/user_office.html',
                controller: 'userOfficeController'
            })
            .when('/trainer_office', {
                templateUrl: 'pages/trainer_office.html',
                controller: 'trainerOfficeController'
            })
            .when('/admin_office', {
                templateUrl: 'pages/admin_office.html',
                controller: 'adminOfficeController'
            })
            .when('/super_office', {
                templateUrl: 'pages/super_office.html',
                controller: 'superOfficeController'
            })
            .when('/contact', {
                templateUrl: 'pages/contact.html',
                controller: 'contactController'
            })
            .when('/blog', {
                templateUrl: 'pages/blog.html',
                controller: 'blogController'
            })
            .when('/services', {
                templateUrl: 'pages/services.html',
                controller: 'servicesController'
            })
            .when('/about', {
                templateUrl: 'pages/about.html',
                controller: 'aboutController'
            })
            .when('/', {
                templateUrl: 'pages/home.html',
                controller: 'homeController'
            })
            .otherwise({
                redirectTo: '/'
            });
    }

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

angular.module('fitness').controller('indexController', function ($rootScope, $scope, $http, $location, $localStorage) {
    const contextPath = 'http://localhost:3881/fitness';

    $scope.authentications = function () {
        $http.post(contextPath + '/auth', $scope.auth)
            .then(function (response) {
                if(response.data){
                    $scope.setOwner(response);
                    $('#authRes').click();
                }
            }).catch(function (response) {
            $scope.modalStatus = response.data.message;
        });
    };

    $scope.registrations = function () {
        $http.post(contextPath + '/auth/reg', $scope.auth)
            .then(function (response) {
                if(response.data){
                    $scope.setOwner(response);
                    $('#authRes').click();
                }
            }).catch(function (response) {
            $scope.modalStatus = response.data.message;
        });
    };

    $scope.setOwner = function (response){
        $http.defaults.headers.common.Authorization = 'Bearer ' + response.data.token;
        $localStorage.officeOwner = {
            token: response.data.token,
            username: response.data.username,
            surname: response.data.surname,
            role: response.data.role
        };
        console.log($localStorage.officeOwner.role);
        $scope.goToOffice();
    }

    $scope.goToOffice = function (){
        if($localStorage.officeOwner.role === "super"){
            $location.path('/super_office');
        } else if ($localStorage.officeOwner.role === "admin") {
            $location.path('/admin_office');
        } else if ($localStorage.officeOwner.role === "trainer") {
            $location.path('/trainer_office');
        } else {
            $location.path('/user_office');
        }
    }

    $scope.clearOwner = function (){
        delete $localStorage.officeOwner;
        $http.defaults.headers.common.Authorization = '';
        $location.path('/');
    }

    $scope.ownerIsEmpty = function (){
        return !!$localStorage.officeOwner;
    }

    // $scope.delBackHeader = function (){
    //     $("header").removeClass("background-header");
    // }

    // $scope.registrations = function () {
    //     $http.post(contextPathAuth + '/registrations', $scope.auth)
    //         .then(function (response) {
    //             if(response.data.token){
    //                 console.log("Токен получен")
    //                 $http.defaults.headers.common.Authorization = 'Bearer ' + response.data.token;
    //                 $localStorage.webmarketUser = {username: $scope.auth.username, token: response.data.token};
    //                 // $scope.buttonCart();
    //                 $('#authRes').click();
    //                 $scope.showCartCount();
    //
    //                 $location.path('/');
    //             }
    //         }).catch(function (response) {
    //         // console.log(response.data.message)
    //         $scope.modalStatus = response.data.message;
    //     });
    // };

    // $scope.showCart = function () {
    //     $http({
    //         url: contextPathCart + "/cart",
    //         method: 'GET',
    //     }).then(function (response) {
    //         // console.log(response.data)
    //         // console.log(response.data.cart)
    //         $scope.CardList = response.data.cart;
    //         $scope.CardTotalSize = $scope.CardList.length;
    //         let total = 0;
    //         let summ = 0;
    //         for (let p of $scope.CardList) {
    //             let count = p.count;
    //             let price = p.price;
    //             total += count;
    //             summ += (count * price);
    //         }
    //         $scope.CardTotalProduct = total;
    //         $scope.CardTotalSumm = summ;
    //     });
    // };

    // $scope.showCartCount = function () {
    //     if($localStorage.webmarketUser){
    //         $http({
    //             url: contextPathCart + "/cart/count",
    //             method: 'GET',
    //         }).then(function (response) {
    //             $scope.CardTotalProduct = response.data;
    //         });
    //     }
    // };

    // $scope.createOrder = function () {
    //     $http({
    //         url: contextPathOrder + "/orders/create",
    //         method: 'GET'
    //     }).then(function (response) {
    //         $scope.CardList = null;
    //         $scope.CardTotalProduct = 0;
    //         $('#cartRes').click();
    //         alert("Заказ оформлен успешно")
    //     }).catch(function (response){
    //         alert(response.data.message)
    //     });
    // };

    // $scope.setCountToCart = function (id, count) {
    //     $http({
    //         url: contextPathCart + "/cart/add_to_cart",
    //         method: 'GET',
    //         params: {
    //             id: id,
    //             count: count
    //         }
    //     }).then(function (response) {
    //         $scope.showCartCount();
    //         $scope.showCart();
    //     });
    // };

    // $scope.deleteProductFromCart = function (id) {
    //     $http({
    //         url: contextPathCart + "/cart/dell_from_cart",
    //         method: 'GET',
    //         params: {
    //             id: id
    //         }
    //     }).then(function (response) {
    //         $scope.showCart();
    //     });
    // };

    // $scope.clearUser = function (){
    //     delete $localStorage.webmarketUser;
    //     $http.defaults.headers.common.Authorization = '';
    //     $location.path('/')
    // }

    // $scope.ifUserLoggedIn = function (){
    //     return !!$localStorage.webmarketUser;
    // }
    $scope.starting = function (){

    }


    // $scope.filter = null;
    // $scope.showCartCount();
    $scope.starting();
});

