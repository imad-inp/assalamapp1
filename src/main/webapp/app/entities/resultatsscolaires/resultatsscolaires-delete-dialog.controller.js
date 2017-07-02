(function() {
    'use strict';

    angular
        .module('assalamApp')
        .controller('ResultatsscolairesDeleteController',ResultatsscolairesDeleteController);

    ResultatsscolairesDeleteController.$inject = ['$uibModalInstance', 'entity', 'Resultatsscolaires'];

    function ResultatsscolairesDeleteController($uibModalInstance, entity, Resultatsscolaires) {
        var vm = this;

        vm.resultatsscolaires = entity;
        vm.clear = clear;
        vm.confirmDelete = confirmDelete;

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function confirmDelete (id) {
            Resultatsscolaires.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        }
    }
})();
