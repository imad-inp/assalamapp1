(function() {
    'use strict';

    angular
        .module('assalamApp')
        .controller('FamilleDetailController', FamilleDetailController);

    FamilleDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'Famille', 'Kafala', 'Enfant'];

    function FamilleDetailController($scope, $rootScope, $stateParams, previousState, entity, Famille, Kafala, Enfant) {
        var vm = this;

        vm.famille = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('assalamApp:familleUpdate', function(event, result) {
            vm.famille = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
