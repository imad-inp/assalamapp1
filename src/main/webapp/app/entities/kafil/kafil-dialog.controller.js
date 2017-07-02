(function() {
    'use strict';

    angular
        .module('assalamApp')
        .controller('KafilDialogController', KafilDialogController);

    KafilDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'DataUtils', 'entity', 'Kafil', 'Kafala'];

    function KafilDialogController ($timeout, $scope, $stateParams, $uibModalInstance, DataUtils, entity, Kafil, Kafala) {
        var vm = this;

        vm.kafil = entity;
        vm.clear = clear;
        vm.byteSize = DataUtils.byteSize;
        vm.openFile = DataUtils.openFile;
        vm.save = save;
        vm.kafalas = Kafala.query();

        $timeout(function (){
            angular.element('.form-group:eq(1)>input').focus();
        });

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function save () {
            vm.isSaving = true;
            if (vm.kafil.id !== null) {
                Kafil.update(vm.kafil, onSaveSuccess, onSaveError);
            } else {
                Kafil.save(vm.kafil, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('assalamApp:kafilUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }


        vm.setPhoto = function ($file, kafil) {
            if ($file && $file.$error === 'pattern') {
                return;
            }
            if ($file) {
                DataUtils.toBase64($file, function(base64Data) {
                    $scope.$apply(function() {
                        kafil.photo = base64Data;
                        kafil.photoContentType = $file.type;
                    });
                });
            }
        };

    }
})();
