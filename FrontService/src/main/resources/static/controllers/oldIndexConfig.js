(function () {
    angular
        .module('fitness', ['ui.router', 'ngRoute', 'ngStorage'])
        .config(function($stateProvider, $urlRouterProvider, $locationProvider ){

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

                // работает
/*                .state('user_office', {
                    url: '/user_office',
                    views: {
                        // the main template will be placed here (relatively named)
                        '': {
                            templateUrl: 'pages/user_office.html',
                            controller: 'userOfficeController'
                        },


                        // the child views will be defined here (absolutely named)
                        'userPlace@user_office': {
                            url: '/schedule',
                            templateUrl: 'pages/user/user_schedule.html',
                            controller: 'userScheduleController'
                        }
                    }
                })*/

            // https://itexpertsconsultant.wordpress.com/2016/01/25/routeprovider-vs-stateprovider-in-angularjs/

                .state('user_office', {
                    url: '/user_office',
                    templateUrl: 'pages/user_office.html',
                    controller: 'userOfficeController',
                })

                .state('user_office.schedule', {
                    // parent: 'user_office',
                    url: '/schedule',
                    templateUrl: 'pages/user/user_schedule.html',
                    controller: 'userScheduleController'
                })

                // хер работает
                // .state('user_office', {
                //     url: '/user_office',
                //     templateUrl: 'pages/user_office.html',
                //     controller: 'userOfficeController'
                // })
                // .state('user_office.schedule', {
                //
                // // .state('schedule', {
                // //     parent: 'user_office',
                //     url: '/schedule',
                //     views: {
                //         'userPlace@user_office': {
                //             // url: '/schedule',
                //             // url: '/user_office',
                //             templateUrl: 'pages/user/user_schedule.html',
                //             // templateUrl: 'pages/super_office.html',
                //             controller: 'userScheduleController'
                //         }
                //     }
                // })




                .state('trainer_office', {
                    url: '/trainer_office',
                    templateUrl: 'pages/trainer_office.html',
                    controller: 'trainerOfficeController'
                })
                // .state('admin_office', {
                //     url: '/admin_office',
                //     templateUrl: 'pages/admin_office.html',
                //     controller: 'adminOfficeController'
                // })
                .state('admin_office', {
                    url: '/admin_office',
                    views: {
                        // the main template will be placed here (relatively named)
                        '': {
                            templateUrl: 'pages/admin_office.html',
                            controller: 'adminOfficeController'
                        },


                        // the child views will be defined here (absolutely named)
                        'adminPlace@admin_office': {
                            url: '/schedule',
                            templateUrl: 'pages/user/user_schedule.html',
                            controller: 'userScheduleController'
                        },
                        'adminPlace@admin_office': {
                            url: '/schedule',
                            templateUrl: 'pages/user/user_schedule.html',
                            controller: 'userScheduleController'
                        }
                    }
                })
                .state('super_office', {
                    url: '/super_office',
                    templateUrl: 'pages/super_office.html',
                    controller: 'superOfficeController'
                })
                // .state('user_office.schedule', {
                // // .state('schedule', {
                // //     parent: 'user_office',
                //     url: '/schedule',
                //     // url: '/user_office',
                //     // templateUrl: 'pages/user/user_schedule.html',
                //     templateUrl: 'pages/super_office.html',
                //     controller: 'userScheduleController'
                // })

            // $stateProvider
            //     .state('home', {
            //         url: '/',
            //         templateUrl: 'pages/home.html',
            //         controller: 'homeController'
            //     })
            //     .state('about', {
            //         url: '/about',
            //         templateUrl: 'pages/about.html',
            //         controller: 'aboutController'
            //     })
            //     .state('services', {
            //         url: '/services',
            //         templateUrl: 'pages/services.html',
            //         controller: 'servicesController'
            //     })
            //     .state('blog', {
            //         url: '/blog',
            //         templateUrl: 'pages/blog.html',
            //         controller: 'blogController'
            //     })
            //     .state('contact', {
            //         url: '/contact',
            //         templateUrl: 'pages/contact.html',
            //         controller: 'contactController'
            //     })
            //     .state('user_office', {
            //         url: '/user_office',
            //         templateUrl: 'pages/user_office.html',
            //         controller: 'userOfficeController'
            //     })
            //     .state('trainer_office', {
            //         url: '/trainer_office',
            //         templateUrl: 'pages/trainer_office.html',
            //         controller: 'trainerOfficeController'
            //     })
            //     .state('admin_office', {
            //         url: '/admin_office',
            //         templateUrl: 'pages/admin_office.html',
            //         controller: 'adminOfficeController'
            //     })
            //     .state('super_office', {
            //         url: '/super_office',
            //         templateUrl: 'pages/super_office.html',
            //         controller: 'superOfficeController'
            //     })
            //     .state('user_office.schedule', {
            //     // .state('schedule', {
            //     //     parent: 'user_office',
            //         url: '/schedule',
            //         // url: '/user_office',
            //         // templateUrl: 'pages/user/user_schedule.html',
            //         templateUrl: 'pages/super_office.html',
            //         controller: 'userScheduleController'
            //     })

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