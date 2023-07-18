angular.module('fitness').controller('userScheduleController', function ($scope, $http, $localStorage) {
    const contextPath = 'http://localhost:3881/fitness/api/v1/workout';
    let gridCount;

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

        // const header = $('.header');
        // const hamburgerBar = $('.hamburger_bar');
        // const hamburger = $('.hamburger');

        // setHeader();
        //
        // function setHeader()
        // {
        //     if($(window).scrollTop() > 91)
        //     {
        //         header.addClass('scrolled');
        //         hamburgerBar.addClass('scrolled');
        //     }
        //     else
        //     {
        //         header.removeClass('scrolled');
        //         hamburgerBar.removeClass('scrolled');
        //     }
        // }
    };



    $scope.loadSchedule = function () {
        //TODO тут пересмотреть на необязательную отправку номера недели
        $http({
            url: contextPath + "/week",
            method: 'GET'
        }).then(function (response) {
            // console.log(response.data);
            $scope.schedule = response.data;
            gridCount = 0;
            for (const k of $scope.schedule.week) {
                gridCount += Object.keys(k.day).length;
            }
        });
    };




    // это работает.
    // window.onload = function() {
    //     // let $grid;
    //     const filters = {};
    //     const $grid = $('.grid').isotope({
    //         itemSelector: '.grid-item'
    //     });
    //     $('.timetable_filtering').on('click', '.item_filter_btn', function (event){
    //         const $button = $(event.currentTarget);
    //         const $buttonGroup = $button.parents('.button-group');
    //         const filterGroup = $buttonGroup.attr('data-filter-group');
    //         filters[filterGroup] = $button.attr('data-filter');
    //         const filterValue = concatValues(filters);
    //         $grid.isotope({filter: filterValue});
    //     });
    //     $('.button-group').each(function (i, buttonGroup){
    //         const $buttonGroup = $(buttonGroup);
    //         $buttonGroup.on('click', 'li', function (event) {
    //             $buttonGroup.find('.active').removeClass('active');
    //             const $button = $(event.currentTarget);
    //             $button.addClass('active');
    //         });
    //     });
    //     $('[data-filter = ""]').addClass("active");
    // };
    //
    // function concatValues( obj ) {
    //     let value = '';
    //     for (const prop in obj ) {
    //         value += obj[ prop ];
    //     }
    //     return value;
    // }

    // работает
    $scope.initIsotope = function (){
        const grid = $('.grid').isotope({
            itemSelector: '.grid-item',
            percentPosition: true,
            masonry:
                {
                    horizontalOrder: true
                },
            getSortData:
                {
                    price: function (itemElement) {
                        const priceEle = $(itemElement).find('.product_price').text().replace('$', '');
                        return parseFloat(priceEle);
                    },
                    name: '.tt_class_title'
                }
        });

        // Filtering
        $('.item_filter_btn').on('click', function()
        {
            const buttons = $('.item_filter_btn');
            buttons.removeClass('active');
            $(this).addClass('active');
            const filterValue = $(this).attr('data-filter');
            grid.isotope({ filter: filterValue });
        });
        $('[data-filter = ""]').addClass("active");
    };

    $scope.waitIsotope = function (){
        let gridItem = document.getElementsByClassName('grid-item');
        const interval = setInterval(function () {
            if (gridCount === gridItem.length) {
                clearInterval(interval);
                $scope.initIsotope();
            }
        }, 50);
    };

    $scope.loadSchedule();

    //отложенный запуск изотоп
    $scope.waitIsotope();

});