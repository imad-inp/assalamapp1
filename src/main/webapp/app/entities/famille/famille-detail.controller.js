(function() {
    'use strict';

    angular
        .module('assalamApp')
        .controller('FamilleDetailController', FamilleDetailController);

    FamilleDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'Famille', 'Kafala', 'Enfant','$window','$sce', 'Files'];

    function FamilleDetailController($scope, $rootScope, $stateParams, previousState, entity, Famille, Kafala, Enfant, $window,$sce, Files) {
        var vm = this;

        vm.famille = entity;
        vm.previousState = previousState.name;

         if(vm.famille.photoMereRef){
          vm.isPictureLoading = true;
          Files.get({id:vm.famille.photoMereRef}, function(data){
            vm.isPictureLoading = false;
            vm.photoMere = data.file;
            vm.photoMereContentType = data.fileContentType;
        });
        }

          vm.print = function(){
            $window.print();

        }

          vm.openFile = function(famille, fileRef){
            vm.demande = Files.get({id:vm.famille[fileRef]}, function(data){
             $scope.content = $sce.trustAsResourceUrl('data:' +data.fileContentType + ';base64,' + data.file);
         
            var link = document.createElement("a");
            link.setAttribute("href", $scope.content );
            link.setAttribute("target", "_blank");
             link.setAttribute("download", "picture");
            console.log(link);
    
             document.body.appendChild(link); // Required for FF
            link.click(); // This will download the data file named "download_name.pdf"

            });
          
           
        }
        var unsubscribe = $rootScope.$on('assalamApp:familleUpdate', function(event, result) {
            vm.famille = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
