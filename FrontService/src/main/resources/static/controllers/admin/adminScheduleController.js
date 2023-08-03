angular.module('admin').controller('adminScheduleController', function ($scope, $http, $localStorage) {
    const contextPath = 'http://localhost:3881/fitness/api/v1/admin';
    let currentWeek;
    let gridCount;


    $scope.loadSchedule = function (delta) {
        delta = currentWeek === undefined ? null : currentWeek + delta;
        $http({
            url: contextPath + "/week",
            method: 'GET',
            params: {
                delta: delta
            }
        }).then(function (response) {
            // console.log(response.data);
            $scope.schedule = response.data;
            currentWeek = response.data.currentWeek;
            gridCount = 0;
            for (const k of response.data.week) {
                gridCount += Object.keys(k.day).length;
            }
            $scope.waitIsotope();
        });
    };

    $scope.closeModal = function (modal){
        $(modal).modal('hide');
    };

    $scope.getFullInformationForClass = function (id, dateTime) {
        // $scope.CurrentClass = {
        //     "id": id,
        //     "dateTime": dateTime
        // };

        $http({
            url: contextPath + "/class",
            method: 'POST',
            params: {
                id: id,
                dateTime: dateTime
            }
        }).then(function (response) {
            // console.log(response.data);
            $scope.CurrentClass = response.data;
            $('#modalClassInfo').modal('toggle');
        }).catch(function (response) {
            alert(response.data.message);
        });
    };




    // это работает.
    $scope.initIsotope = function (){
        const filters = {};
        const $grid = $('.grid').isotope({
            itemSelector: '.grid-item'
        });
        $('.timetable_filtering').on('click', '.item_filter_btn', function (event){
            const $button = $(event.currentTarget);
            const $buttonGroup = $button.parents('.button-group');
            const filterGroup = $buttonGroup.attr('data-filter-group');
            filters[filterGroup] = $button.attr('data-filter');
            const filterValue = $scope.concatValues(filters);
            $grid.isotope({filter: filterValue});
        });
        $('.button-group').each(function (i, buttonGroup){
            const $buttonGroup = $(buttonGroup);
            $buttonGroup.on('click', 'li', function (event) {
                $buttonGroup.find('.active').removeClass('active');
                const $button = $(event.currentTarget);
                $button.addClass('active');
            });
        });
        $('[data-filter = ""]').addClass("active");
    };

    // работает
/*    $scope.initIsotope = function (){
        const filters = {};
        const grid = $('.grid').isotope({
            itemSelector: '.grid-item',
            percentPosition: true,
            masonry:
                {
                    horizontalOrder: true
                },
            // getSortData:
            //     {
            //         price: function (itemElement) {
            //             const priceEle = $(itemElement).find('.product_price').text().replace('$', '');
            //             return parseFloat(priceEle);
            //         },
            //         name: '.tt_class_title'
            //     }
        });

        // Filtering old
/!*        $('.item_filter_btn').on('click', function()
        {
            const buttons = $('.item_filter_btn');
            buttons.removeClass('active');
            $(this).addClass('active');
            const filterValue = $(this).attr('data-filter');
            grid.isotope({ filter: filterValue });
        });*!/
        $('[data-filter = ""]').addClass("active");
    };*/

    // function concatValues( obj ) {
    $scope.concatValues = function (obj){
        let value = '';
        for (const prop in obj ) {
            console.log(obj[ prop ]);
            value += obj[ prop ];
        }
        if (value !== ''){
            value += ', .add';
        }

        return value;
    };

    $scope.waitIsotope = function (){
        let gridItem = document.getElementsByClassName('grid-item');
        const interval = setInterval(function () {
            if (gridCount === gridItem.length) {
                clearInterval(interval);
                let add =$('<div class="tt_class grid-item add" ng-click="getTicketById(t.id)" data-bs-toggle="modal" data-bs-target="#modalClassNew"><div class="tt_class_title">+</div></div>');
                $('.grid').append(add);
                $scope.initIsotope();

            }
        }, 50);
    };

    $scope.loadSchedule();

    //отложенный запуск изотоп
    $scope.waitIsotope();

});