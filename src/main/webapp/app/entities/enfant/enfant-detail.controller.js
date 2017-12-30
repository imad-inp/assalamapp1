(function() {
    'use strict';

    angular
        .module('assalamApp')
        .controller('EnfantDetailController', EnfantDetailController);

    EnfantDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'DataUtils', 'entity', 'Enfant', 'Kafala', 'Resultatsscolaires', 'Famille','$window'];

    function EnfantDetailController($scope, $rootScope, $stateParams, previousState, DataUtils, entity, Enfant, Kafala, Resultatsscolaires, Famille,$window) {
        var vm = this;
		
        vm.enfant = entity;
        vm.previousState = previousState.name;
        vm.byteSize = DataUtils.byteSize;
        vm.openFile = DataUtils.openFile;

        var unsubscribe = $rootScope.$on('assalamApp:enfantUpdate', function(event, result) {
            vm.enfant = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }

      vm.print = function(){
            $window.print();
           
        }
})();
