(function() {
    'use strict';

    angular
        .module('assalamApp')
        .controller('EnfantDialogController', EnfantDialogController);

    EnfantDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'DataUtils', 'entity', 'Enfant', 'Kafala', 'Resultatsscolaires', 'Famille'];

    function EnfantDialogController ($timeout, $scope, $stateParams, $uibModalInstance, DataUtils, entity, Enfant, Kafala, Resultatsscolaires, Famille) {
        var vm = this;

        vm.enfant = entity;
        vm.clear = clear;
        vm.byteSize = DataUtils.byteSize;
        vm.openFile = DataUtils.openFile;
        vm.save = save;
        vm.kafalas = Kafala.query();
        vm.resultatsscolaires = Resultatsscolaires.query();
        vm.familles = Famille.query();

        $timeout(function (){
            angular.element('.form-group:eq(1)>input').focus();
        });

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function save () {
            vm.isSaving = true;
            if (vm.enfant.id !== null) {
                Enfant.update(vm.enfant, onSaveSuccess, onSaveError);
            } else {
                Enfant.save(vm.enfant, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('assalamApp:enfantUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }


        vm.setPhoto = function ($file, enfant) {
            if ($file && $file.$error === 'pattern') {
                return;
            }
            if ($file) {
                DataUtils.toBase64($file, function(base64Data) {
                    $scope.$apply(function() {
                        enfant.photo = base64Data;
                        enfant.photoContentType = $file.type;
                    });
                });
            }
        };

    }
})();
