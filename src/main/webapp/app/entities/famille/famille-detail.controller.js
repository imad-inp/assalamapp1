(function() {
    'use strict';

    angular
        .module('assalamApp')
        .controller('FamilleDetailController', FamilleDetailController);

    FamilleDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'Famille', 'Kafala', 'Enfant','$window','$sce'];

    function FamilleDetailController($scope, $rootScope, $stateParams, previousState, entity, Famille, Kafala, Enfant, $window,$sce) {
        var vm = this;

        vm.famille = entity;
        vm.previousState = previousState.name;

          vm.print = function(){
            $window.print();

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

        var unsubscribe = $rootScope.$on('assalamApp:familleUpdate', function(event, result) {
            vm.famille = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
