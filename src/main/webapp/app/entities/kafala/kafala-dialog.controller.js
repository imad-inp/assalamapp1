(function() {
    'use strict';

    angular
        .module('assalamApp')
        .controller('KafalaDialogController', KafalaDialogController);

    KafalaDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'entity', 'Kafala', 'Paiement', 'Enfant', 'Famille', 'Kafil', 'DataUtils'];

    function KafalaDialogController ($timeout, $scope, $stateParams, $uibModalInstance, entity, Kafala, Paiement, Enfant, Famille, Kafil, DataUtils) {
        var vm = this;



        




        vm.kafala = entity;
        console.log(vm.kafala);
        vm.clear = clear;
        vm.datePickerOpenStatus = {};
        vm.openCalendar = openCalendar;
        vm.save = save;
        
        vm.enfants = Enfant.query();
        
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

            vm.setEngagement = function ($file, kafala) {
            if ($file && $file.$error === 'pattern') {
                return;
            }
              if ($file) {
                DataUtils.toBase64($file, function(base64Data) {
                    $scope.$apply(function() {
                        kafala.engagement = base64Data;
                        kafala.engagementContentType = $file.type;
                    });
                });
            }
        };

        function openCalendar (date) {
            vm.datePickerOpenStatus[date] = true;
        }

        
    }
})();
