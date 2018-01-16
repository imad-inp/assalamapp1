(function() {
    'use strict';

    angular
        .module('assalamApp')
        .controller('ReminderDeleteController',ReminderDeleteController);

    ReminderDeleteController.$inject = ['$uibModalInstance', 'entity', 'Reminder'];

    function ReminderDeleteController($uibModalInstance, entity, Reminder) {
        var vm = this;

        vm.reminder = entity;
        vm.clear = clear;
        vm.confirmDelete = confirmDelete;

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function confirmDelete (id) {
            Reminder.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        }
    }
})();
