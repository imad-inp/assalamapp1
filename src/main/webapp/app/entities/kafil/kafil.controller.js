(function() {
    'use strict';

    angular
        .module('assalamApp')
        .controller('KafilController', KafilController);

    KafilController.$inject = ['DataUtils', 'Kafil', 'ParseLinks', 'AlertService', 'paginationConstants', 'KafilSearch', '$window'];

    function KafilController(DataUtils, Kafil, ParseLinks, AlertService, paginationConstants,KafilSearch, $window) {

        var vm = this;

        vm.kafils = [];
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
        vm.byteSize = DataUtils.byteSize;

        loadAll();

        function loadAll () {
            Kafil.query({
                page:  vm.page,
                size: vm.itemsPerPage,
                searchType: 'name',
                searchValue: vm.searchQuery
                
            }, onSuccess, onError);
         

           
        }

 function onSuccess(data, headers) {
                
                vm.links = ParseLinks.parse(headers('link'));
                vm.totalItems = headers('X-Total-Count');
                for (var i = 0; i < data.length; i++) {
                data[i].dateId =  data[i].dateDebut === null ? '' : data[i].dateDebut.split('-')[0];
                    vm.kafils.push(data[i]);
                }
            }

            function onError(error) {
                AlertService.error(error.data.message);
            }
        function loadSearch () {
            KafilSearch.query({
                page:  vm.page,
                size: vm.itemsPerPage,
                searchType: 'name',
                searchValue: vm.searchQuery
                
            }, onSuccess, onError);
                   
        }
         /*function transition() {
            $state.transitionTo($state.$current, {
                page: vm.page,
               
                search: vm.currentSearch,
                statut: vm.statut
         })}*/
          vm.search = function(){
             vm.page = 0;
            vm.kafils = [];
            loadSearch();
        }

        vm.clear = function(){
            vm.searchType =null;
            vm.searchQuery = null;
            reset();
        }

        function reset () {
            vm.page = 0;
            vm.kafils = [];
            loadAll();
        }

        function loadPage(page) {
            vm.page = page;
            loadAll();
        }
        vm.print = function(){
            $window.print();
           

        }
    }
})();
