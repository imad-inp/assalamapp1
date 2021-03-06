(function() {
    'use strict';

    angular
        .module('assalamApp')
        .controller('DemandeadhesionDialogController', DemandeadhesionDialogController);

    DemandeadhesionDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'entity', 'Demandeadhesion','DataUtils','Enfant'];

    function DemandeadhesionDialogController ($timeout, $scope, $stateParams, $uibModalInstance, entity, Demandeadhesion,DataUtils, Enfant) {
        var vm = this;

        vm.demandeadhesion = entity;
        vm.clear = clear;
        vm.datePickerOpenStatus = {};
        vm.openCalendar = openCalendar;
        vm.save = save;
        vm.enfants = Enfant.query();
        
        vm.reloadEnfants = function(){
             vm.enfants = Enfant.query();
        }

        $timeout(function (){
            angular.element('.form-group:eq(1)>input').focus();
        });

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function save () {
            vm.isSaving = true;
            if (vm.demandeadhesion.id !== null) {
                Demandeadhesion.update(vm.demandeadhesion, onSaveSuccess, onSaveError);
            } else {
                Demandeadhesion.save(vm.demandeadhesion, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('assalamApp:demandeadhesionUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }

        vm.datePickerOpenStatus.datedemande = false;

        function openCalendar (date) {
            vm.datePickerOpenStatus[date] = true;
        }
        
          vm.setDemande = function ($file, demandeadhesion) {
            if ($file && $file.$error === 'pattern') {
                return;
            }
              if ($file) {
                DataUtils.toBase64($file, function(base64Data) {
                    $scope.$apply(function() {
                        demandeadhesion.tmpDemande = base64Data;
                        demandeadhesion.tmpDemandeContentType = $file.type;
                    });
                });
            }
        };

    }
})();
