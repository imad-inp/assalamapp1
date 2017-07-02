(function() {
    'use strict';

    angular
        .module('assalamApp')
        .controller('KafalaDeleteController',KafalaDeleteController);

    KafalaDeleteController.$inject = ['$uibModalInstance', 'entity', 'Kafala'];

    function KafalaDeleteController($uibModalInstance, entity, Kafala) {
        var vm = this;

        vm.kafala = entity;
        vm.clear = clear;
        vm.confirmDelete = confirmDelete;

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function confirmDelete (id) {
            Kafala.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        }
    }
})();
