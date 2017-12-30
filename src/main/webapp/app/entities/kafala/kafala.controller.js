(function() {
    'use strict';

    angular
        .module('assalamApp')
        .controller('KafalaController', KafalaController);

    KafalaController.$inject = ['Kafala', 'ParseLinks', 'AlertService', 'paginationConstants', '$stateParams', 'DataUtils', 'KafalaLate'];

    function KafalaController(Kafala, ParseLinks, AlertService, paginationConstants, $stateParams, DataUtils, KafalaLate) {

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

        vm.openFile = DataUtils.openFile;
        
        vm.filter_kafilId = $stateParams.kafilId;

        if($stateParams.late){            loadLate();
        }
        else{        
            loadAll();
        }

        
        
        function isPaimentLate(kafala){
            var kafalaDate = new Date(kafala.datedebut);
            var monthDifference = currentDate.getMonth() - kafalaDate.getMonth() + (12 * (currentDate.getFullYear() - kafalaDate.getFullYear())) + 1;
            kafala.moisretard = Math.abs(monthDifference - kafala.moispayes);
            kafala.statut =  monthDifference > kafala.moispayes ? 'en retard': 'en avance'; 

           return  monthDifference > kafala.moispayes;          
        };

        function loadLate(){
             KafalaLate.query({}, function(data){
                   for (var i = 0; i < data.length; i++) {
                    data[i].isLate = true;
                    vm.kafalas.push(data[i]);
                }   });
        }

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
