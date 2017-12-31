(function() {
    'use strict';

    angular
        .module('assalamApp')
        .controller('PaiementDetailController', PaiementDetailController);

    PaiementDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'Paiement', 'Kafala', '$window'];

    function PaiementDetailController($scope, $rootScope, $stateParams, previousState, entity, Paiement, Kafala, $window) {
        var vm = this;

        vm.paiement = entity;
        vm.previousState = previousState.name;
       

         vm.print = function(){
            $window.print();
           

        }

        var unsubscribe = $rootScope.$on('assalamApp:paiementUpdate', function(event, result) {
            vm.paiement = result;
        });
        $scope.$on('$destroy', unsubscribe);

        

    }
})();
