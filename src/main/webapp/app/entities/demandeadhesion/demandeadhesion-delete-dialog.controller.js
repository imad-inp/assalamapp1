(function() {
    'use strict';

    angular
        .module('assalamApp')
        .controller('DemandeadhesionDeleteController',DemandeadhesionDeleteController);

    DemandeadhesionDeleteController.$inject = ['$uibModalInstance', 'entity', 'Demandeadhesion'];

    function DemandeadhesionDeleteController($uibModalInstance, entity, Demandeadhesion) {
        var vm = this;

        vm.demandeadhesion = entity;
        vm.clear = clear;
        vm.confirmDelete = confirmDelete;

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function confirmDelete (id) {
            Demandeadhesion.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        }
    }
})();
