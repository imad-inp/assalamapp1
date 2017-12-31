(function () {
    'use strict';
    angular
            .module('assalamApp')
            .controller('DemandeadhesionController', DemandeadhesionController);
    DemandeadhesionController.$inject = ['Demandeadhesion', 'DataUtils','$stateParams', 'paginationConstants', 'ParseLinks', '$state','pagingParams'];
    function DemandeadhesionController(Demandeadhesion, DataUtils, $stateParams, paginationConstants, ParseLinks, $state, pagingParams) {

        
        var vm = this;
        vm.demandeadhesions = [];
        vm.demandesFilter =  $stateParams.status;
        vm.itemsPerPage = paginationConstants.itemsPerPage;
        vm.statut =$stateParams.statut;

        vm.links = {
            last: 0
        };
       
        vm.transition =transition;
        vm.openFile = DataUtils.openFile;
        
        vm.setState = function(statut){
            vm.statut = statut;
            transition();
        };
        loadAll();
        function loadAll() {
            Demandeadhesion.query({ page: pagingParams.page - 1,
                size: pagingParams.itemsPerPage, statut : pagingParams.statut, familleId: $stateParams.familleId }
                    ,onSuccess, onError);
            
             function onSuccess(data, headers) {
                vm.demandeadhesions = [];
                vm.links = ParseLinks.parse(headers('link'));
                vm.totalItems = headers('X-Total-Count');
                for (var i = 0; i < data.length; i++) {
                    vm.demandeadhesions.push(data[i]);
                }
            }

            function onError(error) {
                AlertService.error(error.data.message);
            }
        }
        
        function reset() {
            vm.page = 0;
            vm.demandeadhesions = [];
            loadAll();
        }

        vm.loadPAge = function(page) {
            vm.page = page;
            loadAll();
        };
        
            function transition() {
            $state.transitionTo($state.$current, {
                page: vm.page,
                sort: vm.predicate + ',' + (vm.reverse ? 'asc' : 'desc'),
                search: vm.currentSearch,
                statut: vm.statut
            });
        }
    }
})();
