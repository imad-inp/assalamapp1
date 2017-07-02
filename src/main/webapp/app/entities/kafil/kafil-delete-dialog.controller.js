(function() {
    'use strict';

    angular
        .module('assalamApp')
        .controller('KafilDeleteController',KafilDeleteController);

    KafilDeleteController.$inject = ['$uibModalInstance', 'entity', 'Kafil'];

    function KafilDeleteController($uibModalInstance, entity, Kafil) {
        var vm = this;

        vm.kafil = entity;
        vm.clear = clear;
        vm.confirmDelete = confirmDelete;

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function confirmDelete (id) {
            Kafil.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        }
    }
})();
