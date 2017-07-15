(function () {
    'use strict';
    angular
            .module('assalamApp')
            .controller('DemandeadhesionController', DemandeadhesionController);
    DemandeadhesionController.$inject = ['Demandeadhesion', 'DataUtils','$stateParams', 'paginationConstants', 'ParseLinks'];
    function DemandeadhesionController(Demandeadhesion, DataUtils, $stateParams, paginationConstants, ParseLinks) {

        
        var vm = this;
        vm.demandeadhesions = [];
        vm.demandesFilter =  $stateParams.status;
        vm.statut = 'EN_COURS';
        vm.itemsPerPage = paginationConstants.itemsPerPage;
        vm.page = 0;
        vm.links = {
            last: 0
        };
        vm.filtereddemandes = Demandeadhesion.get({ statut: 'EN_COURS' });
        vm.transition =transition;
        vm.openFile = DataUtils.openFile;
        
        vm.setState = function(statut){
            vm.statut = statut;
           reset();
        };
        loadAll();
        function loadAll() {
            Demandeadhesion.query({ page: vm.page,
                size: vm.itemsPerPage, statut : vm.statut }
                    ,onSuccess, onError);
            
             function onSuccess(data, headers) {
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
                search: vm.currentSearch
            });
        }
    }
})();
