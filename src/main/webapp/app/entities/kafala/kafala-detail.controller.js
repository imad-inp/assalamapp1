(function() {
    'use strict';

    angular
        .module('assalamApp')
        .controller('KafalaDetailController', KafalaDetailController);

    KafalaDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'Kafala', 'Paiement', 'Enfant', 'Famille', 'Kafil'];

    function KafalaDetailController($scope, $rootScope, $stateParams, previousState, entity, Kafala, Paiement, Enfant, Famille, Kafil) {
        var vm = this;

        vm.kafala = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('assalamApp:kafalaUpdate', function(event, result) {
            vm.kafala = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
