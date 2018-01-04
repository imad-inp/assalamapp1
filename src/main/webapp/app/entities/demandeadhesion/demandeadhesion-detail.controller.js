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

        vm.demande = Files.get({id:vm.demandeadhesion.demandeRef});

        var unsubscribe = $rootScope.$on('assalamApp:demandeadhesionUpdate', function(event, result) {
            vm.demandeadhesion = result;
        });
        
        $scope.$on('$destroy', unsubscribe);

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
    }
})();
