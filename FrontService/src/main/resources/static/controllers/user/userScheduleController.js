angular.module('fitness').controller('userScheduleController', function ($scope, $http, $localStorage) {
    const contextPath = 'http://localhost:3881/fitness/api/v1/workout';

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
    };

    $scope.loadSchedule = function () {
        //TODO тут пересмотреть на необязательную отправку номера недели
        $http({
            url: contextPath + "/week",
            method: 'GET'
        }).then(function (response) {
            console.log(response.data)

            $scope.schedule = response.data;
        });
    };



/*    const $grid = $('.grid').isotope({
        itemSelector: '.grid-item'
    });

    const filters = {};

    $('.filters').on('click', '.item_filter_btn', function (event){
        const $button = $(event.currentTarget);
        const $buttonGroup = $button.parents('.button-group');
        const filterGroup = $buttonGroup.attr('data-filter-group');
        filters[filterGroup] = $button.attr('data-filter');
        const filterValue = concatValues(filters);
        $grid.isotope({filter: filterValue});
    });

    $('.button-group').each(function (i, buttonGroup){
        const $buttonGroup = $(buttonGroup);
        $buttonGroup.on('click', 'li', function (event) {
            $buttonGroup.find('.is-checked').removeClass('is-checked');
            const $button = $(event.currentTarget);
            console.log($button);
            $button.addClass('is-checked');
        });
    });

    function concatValues(obj){
        let value = '';
        for (const prop in obj){
            value += obj[prop];
        }
        console.log(value);
        return value;
    }*/

        // external js: isotope.pkgd.js

    // init Isotope
    const $grid = $('.grid-t').isotope({
        itemSelector: '.grid-item'
    });

    // store filter for each group
    const filters = {};

    $('.filters').on( 'click', '.button', function( event ) {
        const $button = $(event.currentTarget);
        // get group key
        const $buttonGroup = $button.parents('.button-group');
        const filterGroup = $buttonGroup.attr('data-filter-group');
        // set filter for group
            filters[ filterGroup ] = $button.attr('data-filter');
            // combine filters
        const filterValue = concatValues(filters);
        // set filter for Isotope
            $grid.isotope({ filter: filterValue });
        });

    // change is-checked class on buttons
        $('.button-group').each( function( i, buttonGroup ) {
            const $buttonGroup = $(buttonGroup);
            $buttonGroup.on( 'click', 'button', function( event ) {
                $buttonGroup.find('.is-checked').removeClass('is-checked');
                const $button = $(event.currentTarget);
                $button.addClass('is-checked');
            });
        });

    // flatten object by concatting values
        function concatValues( obj ) {
            let value = '';
            for (const prop in obj ) {
                value += obj[ prop ];
            }
            return value;
        }

    $scope.loadSchedule();

});