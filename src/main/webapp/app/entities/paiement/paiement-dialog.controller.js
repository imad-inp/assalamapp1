(function() {
    'use strict';

    angular
        .module('assalamApp')
        .controller('PaiementDialogController', PaiementDialogController);

    PaiementDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'entity', 'Paiement', 'Kafala', 'DataUtils'];

    function PaiementDialogController ($timeout, $scope, $stateParams, $uibModalInstance, entity, Paiement, Kafala, DataUtils) {
        var vm = this;

        vm.paiement = entity;
        vm.clear = clear;
        vm.datePickerOpenStatus = {};
        vm.openCalendar = openCalendar;
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
            if (vm.paiement.id !== null) {
                
                Paiement.update(vm.paiement, onSaveSuccess, onSaveError);
            } else {
                Paiement.save(vm.paiement, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('assalamApp:paiementUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }

         vm.setProof = function ($file, paiement) {
             
            if ($file && $file.$error === 'pattern') {
                return;
            }
              if ($file) {
                DataUtils.toBase64($file, function(base64Data) {
                    $scope.$apply(function() {
                    
                       vm.paiement.tmpEvidence = base64Data;
                        vm.paiement.tmpEvidenceContentType = $file.type;

                          
                    });
                });
            }
        };

        vm.datePickerOpenStatus.date = false;

        function openCalendar (date) {
            vm.datePickerOpenStatus[date] = true;
        }
    }
})();
