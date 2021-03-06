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
        

        vm.datePickerOpenStatus = {};
        vm.openCalendar = openCalendar;

         vm.kafilStartDate = !vm.kafil.dateDebut? '' : new Date(vm.kafil.dateDebut);
         
         vm.kafilEndDate =  !vm.kafil.dateFin? '' : new Date(vm.kafil.dateFin);

        $timeout(function (){
            angular.element('.form-group:eq(1)>input').focus();
        });

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function save () {
            vm.isSaving = true;
         
            if(vm.kafilStartDate){
            vm.kafilStartDate.setHours(0, -vm.kafilStartDate.getTimezoneOffset(), 0, 0);
            vm.kafil.dateDebut =  vm.kafilStartDate.toISOString().split('T')[0];
            }
            else{
                 vm.kafil.dateDebut =  vm.kafilStartDate;
            }
            if(vm.kafilEndDate){
            vm.kafilEndDate.setHours(0, -vm.kafilEndDate.getTimezoneOffset(), 0, 0);
            vm.kafil.dateFin =  vm.kafilEndDate.toISOString().split('T')[0];
            }
            else{
                vm.kafil.dateFin =  vm.kafilEndDate;
            }
            
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
         function openCalendar (date) {
            vm.datePickerOpenStatus[date] = true;
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
