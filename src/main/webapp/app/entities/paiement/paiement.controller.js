(function() {
    'use strict';

    angular
        .module('assalamApp')
        .controller('PaiementController', PaiementController);

    PaiementController.$inject = ['Paiement', 'ParseLinks', 'AlertService', 'paginationConstants', '$stateParams'];

    function PaiementController(Paiement, ParseLinks, AlertService, paginationConstants, $stateParams) {

        var vm = this;

        vm.paiements = [];
        vm.loadPage = loadPage;
        vm.itemsPerPage = paginationConstants.itemsPerPage;
        vm.page = 0;
        vm.links = {
            last: 0
        };
        vm.predicate = 'id';
        vm.reset = reset;
        vm.reverse = true;
        
        vm.filter_kafalaId = $stateParams.kafalaId;

        loadAll();

        function loadAll () {
            Paiement.query({
                page: vm.page,
                size: vm.itemsPerPage,
                sort: sort(),
                kafalaId : $stateParams.kafalaId
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
                    vm.paiements.push(data[i]);
                }
            }

            function onError(error) {
                AlertService.error(error.data.message);
            }
        }

        function reset () {
            vm.page = 0;
            vm.paiements = [];
            loadAll();
        }

        function loadPage(page) {
            vm.page = page;
            loadAll();
        }
    }
})();
