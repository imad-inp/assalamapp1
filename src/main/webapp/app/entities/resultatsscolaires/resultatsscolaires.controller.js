(function() {
    'use strict';

    angular
        .module('assalamApp')
        .controller('ResultatsscolairesController', ResultatsscolairesController);

    ResultatsscolairesController.$inject = ['Resultatsscolaires', 'ParseLinks', 'AlertService', 'paginationConstants', '$stateParams', 'Enfant'];

    function ResultatsscolairesController(Resultatsscolaires, ParseLinks, AlertService, paginationConstants, $stateParams, Enfant) {

        var vm = this;

        vm.resultatsscolaires = [];
        vm.loadPage = loadPage;
        vm.itemsPerPage = paginationConstants.itemsPerPage;
        vm.page = 0;
        vm.links = {
            last: 0
        };
        vm.predicate = 'id';
        vm.reset = reset;
        vm.reverse = true;
        vm.filter_enfantId = $stateParams.enfantId;
        vm.enfants = Enfant.query();
        
          vm.currentEnfant = Enfant.get({id : 1});
        loadAll();

        function loadAll () {
            Resultatsscolaires.query({
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
                    vm.resultatsscolaires.push(data[i]);
                }
            }

            function onError(error) {
                AlertService.error(error.data.message);
            }
        }
        
        
       
        function reset () {
            vm.page = 0;
            vm.resultatsscolaires = [];
            loadAll();
        }

        function loadPage(page) {
            vm.page = page;
            loadAll();
        }
    }
})();
