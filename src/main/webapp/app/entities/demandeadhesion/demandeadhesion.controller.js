(function () {
    'use strict';
    angular
            .module('assalamApp')
            .controller('DemandeadhesionController', DemandeadhesionController);
    DemandeadhesionController.$inject = ['Demandeadhesion', 'DataUtils'];
    function DemandeadhesionController(Demandeadhesion, DataUtils) {

        
        var vm = this;
        vm.demandeadhesions = [];
        vm.demandesFilter = ["EN_COURS", "OUVERTE"];

        vm.openFile = DataUtils.openFile;
        loadAll();
        function loadAll() {
            Demandeadhesion.query(function (result) {
                vm.demandeadhesions = result;
                vm.searchQuery = null;
            });
        }
    }
})();
