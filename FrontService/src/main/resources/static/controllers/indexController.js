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

angular.module('fitness').controller('indexController', function ($rootScope, $scope, $http, $location, $localStorage, $compile, $element) {
    const contextPath = 'http://localhost:3881/fitness';

    $scope.refreshSideMenu = async function () {
        let header = $('.lower_header_content');

        let home = document.createElement('div');
        home.classList.add('linc home_linc');
        let homeLinc = document.createElement('a');
        homeLinc.setAttribute('href', "#!/");
        homeLinc.textContent = "Главная";
        home.append(homeLinc);

        let about = document.createElement('div');
        about.classList.add('linc about_linc');
        let aboutLinc = document.createElement('a');
        aboutLinc.setAttribute('href', "#!/about");
        aboutLinc.textContent = "О клубе";
        about.append(aboutLinc);

        let services = document.createElement('div');
        services.classList.add('linc services_linc');
        let servicesLinc = document.createElement('a');
        servicesLinc.setAttribute('href', "#!/services");
        servicesLinc.textContent = "Сервисы и услуги";
        services.append(servicesLinc);

        let blog = document.createElement('div');
        blog.classList.add('linc blog_linc');
        let blogLinc = document.createElement('a');
        blogLinc.setAttribute('href', "#!/blog");
        blogLinc.textContent = "Блог";
        blog.append(blogLinc);

        let contact = document.createElement('div');
        contact.classList.add('linc contact_linc');
        let contactLinc = document.createElement('a');
        contactLinc.setAttribute('href', "#!/contact");
        contactLinc.textContent = "Контакты";
        contact.append(contactLinc);

        // await $scope.slow(header);

        // Promise.resolve(header.animate({right: 3000}, {duration: 400, queue: false}).promise()).
        // then(function () {
        //     return () => {
        //         for (let ch of header.children()) {
        //             ch.remove();
        //         }
        //     };
        // });




        // for (let ch of header.children()) {
        //     ch.remove();
        // }

        header.append(home);
        header.append(about);
        header.append(services);
        header.append(blog);
        header.append(contact);
        $scope.setOfficeLinc();
        header.addClass('side_menu');

        $scope.slow(header);
    };

    $scope.slow = function (element) {
        if (element.hasClass('visible')){
            if (element.hasClass('side_menu')){
                element.animate({"right":"4000px"}, "slow");
            } else {
                element.animate({"left":"4000px"}, "slow");
            }
            element.
            removeClass('visible').
            removeClass('side_menu');
        } else {
            if (element.hasClass('side_menu')){
                element.animate({"right":"0px"}, "slow").addClass('visible');
            } else {
                element.animate({"left":"0px"}, "slow").addClass('visible');
            }
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
        $scope.deleteOfficeLinc();
    };

    $scope.test1 = function () {
        let header = $('.lower_header_content');
        header.removeClass('visible');
        header.addClass('hidden');
        document.querySelector('.lower_header_content').addEventListener('transitionend', () => {
            header.removeClass('hidden');
            header.addClass('visible');
        }, { once: true });
    };

    $scope.deleteOfficeLinc = function (){
        let linc = $('.my_fitness_linc');
        linc.removeClass('visible');
        linc.addClass('hidden');
        document.querySelector('.my_fitness_linc').addEventListener('transitionend', () => {
            // linc.remove();
            $('.lower_header_content').children('.my_fitness_linc').remove();
        }, { once: true });


        // Promise.resolve($('.my_fitness_linc').animate({left: 600}, {duration: 400, queue: false}).promise()).
        // then(function () {
        //     return $('.lower_header_content').children('.my_fitness_linc').remove();
        // });

        // let linc = $('.my_fitness_linc');
        // const p1 = Promise.resolve(linc.animate({left: 600}, {duration: 400, queue: false}).promise());
        // Promise.all([p1]).then(function () {
        //     return $('.lower_header_content').children('.my_fitness_linc').remove();
        // });
    };

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
        let str = window.location.href;
        let str2 = str.substring(str.lastIndexOf('/') + 1);
        console.log(str2);
    };

    $scope.setOfficeLinc = function (){
        if ($scope.ownerIsEmpty()){
            let header = $('.lower_header_content');
            let myFitness =$('<div class="linc my_fitness_linc" ng-click="goToOffice()"><a href="#">Мой фитнес</a></div>');
            $compile(myFitness)($scope);
            myFitness.appendTo($element);
            header.append(myFitness);
        }
    };




    // $scope.filter = null;
    // $scope.showCartCount();
    // $scope.starting();
    // if(!$('.lower_header_content').hasClass('side_menu')){
    //     $scope.refreshSideMenu().then();
    // }
    $scope.setOfficeLinc();
});

