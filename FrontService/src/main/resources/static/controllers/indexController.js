angular.module('fitness').controller('indexController', function ($rootScope, $scope, $http, $location, $localStorage, $compile, $element) {
    const contextPath = 'http://localhost:3881/fitness';
    let header = $('.lower_header_content');
    let sidePageClass = 'side_menu';
    let userPageClass = 'user_menu';

    $scope.createSideMenu = function (header) {
        let home =$('<div class="linc home_linc active"><a href="#!/">Главная</a></div>');
        let about =$('<div class="linc about_linc"><a href="#!/about">О клубе</a></div>');
        let services =$('<div class="linc services_linc"><a href="#!/services">Сервисы и услуги</a></div>');
        let blog =$('<div class="linc blog_linc"><a href="#!/blog">Блог</a></div>');
        let contact =$('<div class="linc contact_linc"><a href="#!/contact">Контакты</a></div>');

        for (let ch of header.children()) {
            ch.remove();
        }
        header.append(home);
        header.append(about);
        header.append(services);
        header.append(blog);
        header.append(contact);
        $scope.setOfficeLinc();
    };

    $scope.refreshSideMenu = function () {
        $rootScope.$emit('refreshMenu', $scope.createSideMenu, sidePageClass);
    };

    $rootScope.$on('refreshMenu', function (event, func, newPageClass) {
        if(!header.hasClass(newPageClass)){
            header.removeClass('visible');
            header.addClass('hidden');
            document.querySelector('.lower_header_content').addEventListener('transitionend', () => {
                func(header);
                header.removeClass('hidden');
                header.removeClass(newPageClass === sidePageClass ? userPageClass : sidePageClass);
                header.addClass(newPageClass);
                header.addClass('visible');
            }, { once: true });
        }
    });

    $rootScope.$on('testEvent', function (event, pageClass, newPage) {
        console.log("start test in index");
        console.log(pageClass);
        newPage('callback page');
    });

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
        if (header.hasClass('visible')){
            header.removeClass('visible');
            header.addClass('hidden');
        } else {
            header.removeClass('hidden');
            header.addClass('visible');
        }


        // let header = $('.lower_header_content');
        // header.removeClass('visible');
        // header.addClass('hidden');
        // document.querySelector('.lower_header_content').addEventListener('transitionend', () => {
        //     header.removeClass('hidden');
        //     header.addClass('visible');
        // }, { once: true });
    };

    $scope.deleteOfficeLinc = function (){
        Promise.resolve($('.my_fitness_linc').animate({left: 600}, {duration: 400, queue: false}).promise()).
        then(function () {
            return $('.lower_header_content').children('.my_fitness_linc').remove();
        });

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
            // let header = $('.lower_header_content');
            let myFitness =$('<div class="linc my_fitness_linc"><a href="" ng-click="goToOffice()">Мой фитнес</a></div>');
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
    $scope.refreshSideMenu();
    // $scope.setOfficeLinc();
});

