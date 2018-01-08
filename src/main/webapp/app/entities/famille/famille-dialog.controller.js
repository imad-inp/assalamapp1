(function() {
    'use strict';

    angular
        .module('assalamApp')
        .controller('FamilleDialogController', FamilleDialogController);

    FamilleDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'entity', 'Famille', 'Kafala', 'Enfant', 'DataUtils','$sce', 'Files'];

    function FamilleDialogController ($timeout, $scope, $stateParams, $uibModalInstance, entity, Famille, Kafala, Enfant,DataUtils,$sce, Files) {
        var vm = this;

        vm.famille = entity;
        vm.clear = clear;
        vm.save = save;
      

        
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

        vm.openFile = function(file, fileType){
             $scope.content = $sce.trustAsResourceUrl('data:' +fileType + ';base64,' + file);
         
            var link = document.createElement("a");
            link.setAttribute("href", $scope.content );
            link.setAttribute("target", "_blank");
             link.setAttribute("download", "picture");
            console.log(link);
    
             document.body.appendChild(link); // Required for FF
            link.click(); // This will download the data file named "download_name.pdf"
           
        }

        vm.setCertifMariage = function($file, famille){
              if ($file && $file.$error === 'pattern') {
                return;
            }
              if ($file) {
                DataUtils.toBase64($file, function(base64Data) {
                    $scope.$apply(function() {
                        famille.tmpCertifMariage = base64Data;
                        famille.tmpCertifMariageContentType = $file.type;
                        
                    });
                });
            }
        }
         vm.setRamid = function($file, famille){
              if ($file && $file.$error === 'pattern') {
                return;
            }
              if ($file) {
                DataUtils.toBase64($file, function(base64Data) {
                    $scope.$apply(function() {
                        famille.tmpRamid = base64Data;
                        famille.tmpRamidContentType = $file.type;
                        
                    });
                });
            }
        }

           vm.setCertifDeces = function($file, famille){
              if ($file && $file.$error === 'pattern') {
                return;
            }
              if ($file) {
                DataUtils.toBase64($file, function(base64Data) {
                    $scope.$apply(function() {
                        famille.tmpCertifDeces = base64Data;
                        famille.tmpCertifDecesContentType = $file.type;
                        
                    });
                });
            }
        };
           vm.setCertifDivorce = function($file, famille){
              if ($file && $file.$error === 'pattern') {
                return;
            }
              if ($file) {
                DataUtils.toBase64($file, function(base64Data) {
                    $scope.$apply(function() {
                        famille.tmpCertifDivorce = base64Data;
                        famille.tmpCertifDivorceContentType = $file.type;
                        
                    });
                });
            }
        };
         vm.setCinPereCopie = function($file, famille){
              if ($file && $file.$error === 'pattern') {
                return;
            }
              if ($file) {
                DataUtils.toBase64($file, function(base64Data) {
                    $scope.$apply(function() {
                        famille.tmpCinPereCopie = base64Data;
                        famille.tmpCinPereCopieContentType = $file.type;
                        
                    });
                });
            }
        }
         vm.setCinMereCopie = function($file, famille){
              if ($file && $file.$error === 'pattern') {
                return;
            }
              if ($file) {
                DataUtils.toBase64($file, function(base64Data) {
                    $scope.$apply(function() {
                        famille.tmpCinMereCopie = base64Data;
                        famille.tmpCinMereCopieContentType = $file.type;
                        
                    });
                });
            }
        }

          vm.setPhotoMere = function ($file, famille) {
            if ($file && $file.$error === 'pattern') {
                return;
            }
            if ($file) {
                DataUtils.toBase64($file, function (base64Data) {
                    $scope.$apply(function () {
                        famille.tmpPhotoMere = base64Data;
                        famille.tmpPhotoMereContentType = $file.type;
                     
                  
                    });
                });
            }
        };

        function onSaveError () {
            vm.isSaving = false;
        }


    }
})();
