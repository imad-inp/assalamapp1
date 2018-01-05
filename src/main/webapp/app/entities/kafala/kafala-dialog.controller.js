(function() {
    'use strict';

    angular
        .module('assalamApp')
        .controller('KafalaDialogController', KafalaDialogController);

    KafalaDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'entity', 'Kafala', 'Paiement', 'Enfant', 'Famille', 'Kafil', 'DataUtils', 'KafilSearch'];

    function KafalaDialogController ($timeout, $scope, $stateParams, $uibModalInstance, entity, Kafala, Paiement, Enfant, Famille, Kafil, DataUtils,KafilSearch) {
        var vm = this;



        




        vm.kafala = entity;
        vm.kafalaStartDate = new Date(vm.kafala.startDate);
        vm.kafalaEndDate = vm.kafala.endDate === null ? null : new Date(vm.kafala.endDate);

       
        vm.clear = clear;
        vm.datePickerOpenStatus = {};
        vm.openCalendar = openCalendar;
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
            vm.kafalaStartDate.setHours(0, -vm.kafalaStartDate.getTimezoneOffset(), 0, 0);
            if(vm.kafalaEndDate !== null)
            vm.kafalaEndDate.setHours(0, -vm.kafalaEndDate.getTimezoneOffset(), 0, 0);
            vm.kafala.startDate = vm.kafalaStartDate.toISOString().split('T')[0];
            vm.kafala.endDate = vm.kafalaEndDate === null ? null: vm.kafalaEndDate.toISOString().split('T')[0];
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
        

          vm.search = function(){
             KafilSearch.query({
                searchValue: vm.searchQuery,
                searchType: 'name'            
            }, function(data){
                vm.kafils = data;
            });
            
        }


        vm.datePickerOpenStatus.datedebut = false;

            vm.setEngagement = function ($file, kafala) {
            if ($file && $file.$error === 'pattern') {
                return;
            }
              if ($file) {
                DataUtils.toBase64($file, function(base64Data) {
                    $scope.$apply(function() {
                       vm.kafala.tmpEngagement = base64Data;
                        vm.kafala.tmpEngagementContentType = $file.type;
                    });
                });
            }
        };

        function openCalendar (date) {
            vm.datePickerOpenStatus[date] = true;
        }

        
    }
})();
