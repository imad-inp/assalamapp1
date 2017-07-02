(function() {
    'use strict';

    angular
        .module('assalamApp')
        .controller('FamilleDialogController', FamilleDialogController);

    FamilleDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'entity', 'Famille', 'Kafala', 'Enfant'];

    function FamilleDialogController ($timeout, $scope, $stateParams, $uibModalInstance, entity, Famille, Kafala, Enfant) {
        var vm = this;

        vm.famille = entity;
        vm.clear = clear;
        vm.save = save;
        vm.kafalas = Kafala.query();
        vm.enfants = Enfant.query();

        $timeout(function (){
            angular.element('.form-group:eq(1)>input').focus();
        });

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function save () {
            vm.isSaving = true;
            if (vm.famille.id !== null) {
                Famille.update(vm.famille, onSaveSuccess, onSaveError);
            } else {
                Famille.save(vm.famille, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('assalamApp:familleUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }


    }
})();
