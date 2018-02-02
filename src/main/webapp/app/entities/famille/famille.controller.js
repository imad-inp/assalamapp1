(function() {
    'use strict';

    angular
        .module('assalamApp')
        .controller('FamilleController', FamilleController);

    FamilleController.$inject = ['Famille', 'ParseLinks', 'AlertService', 'paginationConstants','Demandeadhesion', '$stateParams'];

    function FamilleController(Famille, ParseLinks, AlertService, paginationConstants, Demandeadhesion, $stateParams) {

        var vm = this;

        vm.familles = [];
        vm.loadPage = loadPage;
        vm.itemsPerPage = paginationConstants.itemsPerPage;
        vm.page = 0;
        vm.links = {
            last: 0
        };
        vm.predicate = 'id';
        vm.reset = reset;
        vm.reverse = true;
        
        

        loadAll();

        function loadAll () {
            Famille.query({
                page: vm.page,
                size: vm.itemsPerPage,
                sort: sort(),
                searchType: vm.searchType ? vm.searchType :  'state',
                searchValue: vm.searchQuery ? vm.searchQuery : $stateParams.state
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
                    vm.familles.push(data[i]);
                }
                
            }

            function onError(error) {
                AlertService.error(error.data.message);
            }
        }

        vm.search = function(){
            reset();
        }

        vm.clear = function(){
            vm.searchType =null;
            vm.searchQuery = null;
        }

      
        function reset () {
            vm.page = 0;
            vm.familles = [];
            loadAll();
        }

        function loadPage(page) {
            vm.page = page;
            loadAll();
        }

        
  
    }
})();
