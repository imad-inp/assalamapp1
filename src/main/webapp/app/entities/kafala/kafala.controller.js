(function() {
    'use strict';

    angular
        .module('assalamApp')
        .controller('KafalaController', KafalaController);

    KafalaController.$inject = ['Kafala', 'ParseLinks', 'AlertService', 'paginationConstants', '$stateParams'];

    function KafalaController(Kafala, ParseLinks, AlertService, paginationConstants, $stateParams) {

        var vm = this;
        
        var currentDate = new Date();
        vm.kafalas = [];
        vm.loadPage = loadPage;
        vm.itemsPerPage = paginationConstants.itemsPerPage;
        vm.page = 0;
        vm.links = {
            last: 0
        };
        vm.predicate = 'id';
        vm.reset = reset;
        vm.reverse = true;
        
        vm.filter_kafilId = $stateParams.kafilId;

        loadAll();
        
        
        function isPaimentLate(kafala){
            var kafalaDate = new Date(kafala.datedebut);
            var monthDifference = currentDate.getMonth() - kafalaDate.getMonth() + (12 * (currentDate.getFullYear() - kafalaDate.getFullYear())) + 1;
            kafala.moisretard = monthDifference - kafala.moispayes;
           return  monthDifference > kafala.moispayes;          
        };

        function loadAll () {
            Kafala.query({
                page: vm.page,
                size: vm.itemsPerPage,
                sort: sort()
            }, onSuccess, onError);
            function sort() {
                var result = [vm.predicate + ',' + (vm.reverse ? 'asc' : 'desc')];
                if (vm.predicate !== 'id') {
                    result.push('id');
                }
                return result;
            }

            function onSuccess(data, headers) {
                vm.links = ParseLinks.parse(headers('link'));
                vm.totalItems = headers('X-Total-Count');
                for (var i = 0; i < data.length; i++) {
                    data[i].isLate = isPaimentLate(data[i]);
                    vm.kafalas.push(data[i]);
                }
            }

            function onError(error) {
                AlertService.error(error.data.message);
            }
        }

        function reset () {
            vm.page = 0;
            vm.kafalas = [];
            loadAll();
        }

        function loadPage(page) {
            vm.page = page;
            loadAll();
        }
    }
})();
