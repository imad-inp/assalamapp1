(function() {
    'use strict';

    angular
        .module('assalamApp')
        .controller('ReminderDialogController', ReminderDialogController);

    ReminderDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'entity', 'Reminder', 'Kafil'];

    function ReminderDialogController ($timeout, $scope, $stateParams, $uibModalInstance, entity, Reminder, Kafil) {
        var vm = this;

        vm.reminder = entity;
        vm.clear = clear;
        vm.save = save;
        vm.kafils = Kafil.query();

        $timeout(function (){
            angular.element('.form-group:eq(1)>input').focus();
        });

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function save () {
            vm.isSaving = true;
            if (vm.reminder.id !== null) {
                Reminder.update(vm.reminder, onSaveSuccess, onSaveError);
            } else {
                Reminder.save(vm.reminder, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('assalamApp:reminderUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }


    }
})();
