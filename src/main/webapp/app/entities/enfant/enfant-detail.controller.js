(function() {
    'use strict';

    angular
        .module('assalamApp')
        .controller('EnfantDetailController', EnfantDetailController);

    EnfantDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'DataUtils', 'entity', 'Enfant', 'Kafala', 'Resultatsscolaires',
     'Famille','$window','Files'];

    function EnfantDetailController($scope, $rootScope, $stateParams, previousState, DataUtils, entity, Enfant, Kafala, Resultatsscolaires,
     Famille,$window, Files) {

        var vm = this;
        
       
		 vm.print = function(){
            $window.print();
           
        }
        vm.enfant = entity;

        if(vm.enfant.photoRef){
        vm.isPictureLoading = true;
        vm.photo = Files.get({id:vm.enfant.photoRef}, function(data){
            vm.isPictureLoading = false;
        });
        }

        vm.previousState = previousState.name;
        vm.byteSize = DataUtils.byteSize;
        vm.openFile = DataUtils.openFile;

         vm.getAge = function () {
            var diff = new Date() - new Date(vm.enfant.dateDeNaissance); // This is the difference in milliseconds
            return Math.floor(diff / 31557600000); // Divide by 1000*60*60*24*365.25F
        };


        var unsubscribe = $rootScope.$on('assalamApp:enfantUpdate', function(event, result) {
            vm.enfant = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }

     
})();
