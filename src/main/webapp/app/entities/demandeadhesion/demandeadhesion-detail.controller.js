(function() {
    'use strict';

    angular
        .module('assalamApp')
        .controller('DemandeadhesionDetailController', DemandeadhesionDetailController);

    DemandeadhesionDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'Demandeadhesion'];

    function DemandeadhesionDetailController($scope, $rootScope, $stateParams, previousState, entity, Demandeadhesion) {
        var vm = this;

        vm.demandeadhesion = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('assalamApp:demandeadhesionUpdate', function(event, result) {
            vm.demandeadhesion = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
