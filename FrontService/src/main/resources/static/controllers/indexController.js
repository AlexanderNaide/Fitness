(function () {
    angular
            // .module('fitness', ['ui.router', 'ngRoute', 'ngStorage'])
            // .config(config)
            // .run(run);


        .module('fitness', ['ui.router', 'ngRoute', 'ngStorage'])
        .config(function($stateProvider, $urlRouterProvider, $locationProvider){

            $urlRouterProvider.otherwise("/");

            $stateProvider
                .state('home', {
                    url: '/',
                    templateUrl: 'pages/home.html',
                    controller: 'homeController'
                })
                .state('about', {
                    url: '/about',
                    templateUrl: 'pages/about.html',
                    controller: 'aboutController'
                })
                .state('services', {
                    url: '/services',
                    templateUrl: 'pages/services.html',
                    controller: 'servicesController'
                })
                .state('blog', {
                    url: '/blog',
                    templateUrl: 'pages/blog.html',
                    controller: 'blogController'
                })
                .state('contact', {
                    url: '/contact',
                    templateUrl: 'pages/contact.html',
                    controller: 'contactController'
                })
                .state('user_office', {
                    url: '/user_office',
                    templateUrl: 'pages/user_office.html',
                    controller: 'userOfficeController'
                })
                .state('trainer_office', {
                    url: '/trainer_office',
                    templateUrl: 'pages/trainer_office.html',
                    controller: 'trainerOfficeController'
                })
                .state('admin_office', {
                    url: '/admin_office',
                    templateUrl: 'pages/admin_office.html',
                    controller: 'adminOfficeController'
                })
                .state('super_office', {
                    url: '/super_office',
                    templateUrl: 'pages/super_office.html',
                    controller: 'superOfficeController'
                })
                .state('user_office.schedule', {
                    // parent: 'user_office',
                    url: '/schedule',
                    templateUrl: '../pages/user/user_schedule.html',
                    controller: 'userScheduleController'
                })

        })
        .run(run);

    function run($rootScope, $http, $localStorage, $location) {
        console.log("Запускается проверка $localStorage.officeOwner");
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

    $scope.refreshMenu = async function () {



        let header = $('.lower_header_content');

        let information = document.createElement('div');
        information.classList.add('linc');
        let informationLinc = document.createElement('a');
        informationLinc.setAttribute('href', "#!/schedule");
        informationLinc.textContent = "Занятия";
        information.append(informationLinc);

        let services = document.createElement('div');
        services.classList.add('linc');
        let servicesLinc = document.createElement('a');
        servicesLinc.setAttribute('href', "#!/");
        servicesLinc.textContent = "Абонемент";
        services.append(servicesLinc);

        let info = document.createElement('div');
        info.classList.add('linc');
        let infoLinc = document.createElement('a');
        infoLinc.setAttribute('href', "#!/");
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

