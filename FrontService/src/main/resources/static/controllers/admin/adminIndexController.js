angular.module('admin').controller('adminIndexController', function ($rootScope, $scope, $http, $location, $localStorage, $compile, $element) {
    const contextPath = 'http://localhost:3881/fitness';
    console.log('adminIndexController');
    // let header = $('.lower_header_content');
    // let headerClasses = [];
    // let sidePageClass = 'side_menu';
    // headerClasses.push(sidePageClass);

    $scope.openFullscreen = function () {
        console.log("open");

        const elem = document.documentElement;
        if (elem.requestFullscreen) {
            elem.requestFullscreen();
        } else if (elem.webkitRequestFullscreen) { /* Safari */
            elem.webkitRequestFullscreen();
        } else if (elem.msRequestFullscreen) { /* IE11 */
            elem.msRequestFullscreen();
        }
    };

    $scope.closeFullscreen = function () {

        console.log("close");

        const elem = document.documentElement;
        if (document.exitFullscreen) {
            document.exitFullscreen();
        } else if (document.webkitExitFullscreen) { /* Safari */
            document.webkitExitFullscreen();
        } else if (document.msExitFullscreen) { /* IE11 */
            document.msExitFullscreen();
        }
    };


    $scope.fullScreen = function () {
        if (!document.fullscreenElement &&    // alternative standard method
            !document.mozFullScreenElement && !document.webkitFullscreenElement) {  // current working methods
            if (document.documentElement.requestFullscreen) {
                document.documentElement.requestFullscreen();
            } else if (document.documentElement.mozRequestFullScreen) {
                document.documentElement.mozRequestFullScreen();
            } else if (document.documentElement.webkitRequestFullscreen) {
                document.documentElement.webkitRequestFullscreen(Element.ALLOW_KEYBOARD_INPUT);
            }
        } else {
            if (document.cancelFullScreen) {
                document.cancelFullScreen();
            } else if (document.mozCancelFullScreen) {
                document.mozCancelFullScreen();
            } else if (document.webkitCancelFullScreen) {
                document.webkitCancelFullScreen();
            }
        }
    };



    // $scope.refreshSideMenu = function () {
    //     $rootScope.$emit('refreshMenu', $scope.createSideMenu, sidePageClass);
    // };

    // $rootScope.$on('refreshMenu', function (event, func, newPageClass) {
    //     if(!header.hasClass(newPageClass)){
    //         header.removeClass('visible');
    //         header.addClass('hidden');
    //         document.querySelector('.lower_header_content').addEventListener('transitionend', () => {
    //             func(header);
    //             header.removeClass('hidden');
    //             header.removeClass(headerClasses);
    //             headerClasses.push(newPageClass);
    //             header.addClass(newPageClass);
    //             header.addClass('visible');
    //         }, { once: true });
    //     }
    // });

    // $rootScope.$on('testEvent', function (event, pageClass, newPage) {
    //     console.log("start test in index");
    //     console.log(pageClass);
    //     newPage('callback page');
    // });

    // $scope.authentications = function () {
    //     $http.post(contextPath + '/auth', $scope.auth)
    //         .then(function (response) {
    //             if(response.data){
    //                 $scope.setOwner(response);
    //                 $('#authRes').click();
    //             }
    //         }).catch(function (response) {
    //         $scope.modalStatus = response.data.message;
    //     });
    // };

    // $scope.registrations = function () {
    //     $http.post(contextPath + '/auth/reg', $scope.auth)
    //         .then(function (response) {
    //             if(response.data){
    //                 $scope.setOwner(response);
    //                 $('#authRes').click();
    //             }
    //         }).catch(function (response) {
    //         $scope.modalStatus = response.data.message;
    //     });
    // };

    // $scope.setOwner = function (response){
    //     $http.defaults.headers.common.Authorization = 'Bearer ' + response.data.token;
    //     $localStorage.officeOwner = {
    //         token: response.data.token,
    //         username: response.data.username,
    //         surname: response.data.surname,
    //         role: response.data.role
    //     };
    //     $scope.goToOffice();
    // }

    // $scope.goToOffice = function (){
    //     if($localStorage.officeOwner.role === "super"){
    //         $location.path('/super_office');
    //     } else if ($localStorage.officeOwner.role === "admin") {
    //         $location.path('/admin_office/clients');
    //     } else if ($localStorage.officeOwner.role === "trainer") {
    //         $location.path('/trainer_office');
    //     } else {
    //         $location.path("/user_office/schedule");
    //     }
    // }

    // $scope.clearOwner = function (){
    //     delete $localStorage.officeOwner;
    //     $http.defaults.headers.common.Authorization = '';
    //     $location.path('/');
    //     $scope.deleteOfficeLinc();
    //     $scope.refreshSideMenu();
    // };

    // $scope.test1 = function () {
    //     let header = $('.lower_header_content');
    //     if (header.hasClass('visible')){
    //         header.removeClass('visible');
    //         header.addClass('hidden');
    //     } else {
    //         header.removeClass('hidden');
    //         header.addClass('visible');
    //     }
    // };

    // $scope.deleteOfficeLinc = function (){
    //     Promise.resolve($('.my_fitness_linc').animate({left: 600}, {duration: 400, queue: false}).promise()).
    //     then(function () {
    //         return $('.lower_header_content').children('.my_fitness_linc').remove();
    //     });
    // };

    // $scope.ownerIsEmpty = function (){
    //     return !!$localStorage.officeOwner;
    // }

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
    // $scope.starting = function (){
    //     let str = window.location.href;
    //     let str2 = str.substring(str.lastIndexOf('/') + 1);
    //     console.log(str2);
    // };

    // $scope.setOfficeLinc = function (){
    //     if ($scope.ownerIsEmpty()){
    //         // let header = $('.lower_header_content');
    //         let myFitness =$('<div class="linc my_fitness_linc"><a href="" ng-click="goToOffice()">Мой фитнес</a></div>');
    //         // let myFitness =$('<div class="linc my_fitness_linc" ui-sref="user_office.schedule"><a href="" >Мой фитнес</a></div>');
    //         $compile(myFitness)($scope);
    //         myFitness.appendTo($element);
    //         header.append(myFitness);
    //     }
    // };




    // $scope.filter = null;
    // $scope.showCartCount();
    // $scope.starting();
    // if(!$('.lower_header_content').hasClass('side_menu')){
    //     $scope.refreshSideMenu().then();
    // }
    // $scope.refreshSideMenu();
    // $scope.setOfficeLinc();
    // $scope.fullScreen();
    // window.onload = function() {
    //     console.log('onload');
    //     $scope.fullScreen();
    // };

    // $window.onload = function(e) {
    //     console.log('onload');
    //     $scope.fullScreen();
    // };
});

