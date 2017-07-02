(function() {
    'use strict';

    angular
        .module('assalamApp')
        .controller('KafalaDialogController', KafalaDialogController);

    KafalaDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'entity', 'Kafala', 'Paiement', 'Enfant', 'Famille', 'Kafil'];

    function KafalaDialogController ($timeout, $scope, $stateParams, $uibModalInstance, entity, Kafala, Paiement, Enfant, Famille, Kafil) {
        var vm = this;

        vm.kafala = entity;
        vm.clear = clear;
        vm.datePickerOpenStatus = {};
        vm.openCalendar = openCalendar;
        vm.save = save;
        vm.paiements = Paiement.query();
        vm.enfants = Enfant.query();
        vm.familles = Famille.query();
        vm.kafils = Kafil.query();

        $timeout(function (){
            angular.element('.form-group:eq(1)>input').focus();
        });

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function save () {
            vm.isSaving = true;
            if (vm.kafala.id !== null) {
                Kafala.update(vm.kafala, onSaveSuccess, onSaveError);
            } else {
                Kafala.save(vm.kafala, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('assalamApp:kafalaUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }

        vm.datePickerOpenStatus.datedebut = false;

        function openCalendar (date) {
            vm.datePickerOpenStatus[date] = true;
        }
    }
})();
