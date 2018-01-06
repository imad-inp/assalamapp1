(function() {
    'use strict';

    angular
        .module('assalamApp')
        .controller('DemandeadhesionDetailController', DemandeadhesionDetailController);

    DemandeadhesionDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'Demandeadhesion', 'Files','$sce'];

    function DemandeadhesionDetailController($scope, $rootScope, $stateParams, previousState, entity, Demandeadhesion, Files, $sce) {
        var vm = this;

        vm.demandeadhesion = entity;
        vm.previousState = previousState.name;
       

        var unsubscribe = $rootScope.$on('assalamApp:demandeadhesionUpdate', function(event, result) {
            vm.demandeadhesion = result;
        });

        $scope.$on('$destroy', unsubscribe);

        vm.openFile = function(demandeadhesion){
            vm.demande = Files.get({id:vm.demandeadhesion.demandeRef}, function(data){
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
    }
})();
