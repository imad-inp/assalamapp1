(function() {
    'use strict';

    angular
        .module('assalamApp')
        .controller('ResultatsscolairesDialogController', ResultatsscolairesDialogController);

    ResultatsscolairesDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'entity', 'Resultatsscolaires', 'Enfant'];

    function ResultatsscolairesDialogController ($timeout, $scope, $stateParams, $uibModalInstance, entity, Resultatsscolaires, Enfant) {
        var vm = this;

        vm.resultatsscolaires = entity;
        vm.clear = clear;
        vm.save = save;
        vm.enfants = Enfant.query();

        $timeout(function (){
            angular.element('.form-group:eq(1)>input').focus();
        });

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function save () {
            vm.isSaving = true;
            if (vm.resultatsscolaires.id !== null) {
                Resultatsscolaires.update(vm.resultatsscolaires, onSaveSuccess, onSaveError);
            } else {
                Resultatsscolaires.save(vm.resultatsscolaires, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('assalamApp:resultatsscolairesUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }


    }
})();
