(function () {
    'use strict';

    angular
            .module('assalamApp')
            .controller('EnfantDialogController', EnfantDialogController);

    EnfantDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'DataUtils', 'entity', 'Enfant',
     'Kafala', 'Resultatsscolaires', 'Famille', 'Files'];

    function EnfantDialogController($timeout, $scope, $stateParams, $uibModalInstance, DataUtils, entity, Enfant, Kafala, Resultatsscolaires,
     Famille, Files
    ) {
        var vm = this;

        vm.enfant = entity;
        vm.clear = clear;
        vm.byteSize = DataUtils.byteSize;
        vm.openFile = DataUtils.openFile;
        vm.save = save;
        vm.kafalas = Kafala.query();
        vm.resultatsscolaires = Resultatsscolaires.query();

        vm.photo ={};

		if($stateParams.familleId == null)
			vm.familles = Famille.query();
		else
			vm.familles = $stateParams.familleId;
        vm.datePickerOpenStatus = {};

        vm.openCalendar = openCalendar;

        vm.birthDay =  new Date(vm.enfant.dateDeNaissance);

        $timeout(function () {
            angular.element('.form-group:eq(1)>input').focus();
        });

        function clear() {
            $uibModalInstance.dismiss('cancel');
        }

        function save() {
            vm.isSaving = true;
            vm.enfant.dateDeNaissance = vm.birthDay.toISOString().split('T')[0];
            if (vm.enfant.id !== null) {
                Enfant.update(vm.enfant, onSaveSuccess, onSaveError);
            } else {
                Enfant.save(vm.enfant, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess(result) {
            $scope.$emit('assalamApp:enfantUpdate', result);
            $uibModalInstance.close(result);
            
        }

        function onSaveError() {
            vm.isSaving = false;
        }


        vm.setPhoto = function ($file, enfant) {
            if ($file && $file.$error === 'pattern') {
                return;
            }
            if ($file) {
                DataUtils.toBase64($file, function (base64Data) {
                    $scope.$apply(function () {
                        vm.enfant.tmpPhoto = base64Data;
                        vm.enfant.tmpPhotoContentType = $file.type;
                     
                  
                    });
                });
            }
        };

        function openCalendar(date) {
            vm.datePickerOpenStatus[date] = true;
            
        }

    }
})();
