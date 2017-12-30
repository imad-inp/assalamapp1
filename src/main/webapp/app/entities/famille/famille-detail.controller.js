(function() {
    'use strict';

    angular
        .module('assalamApp')
        .controller('FamilleDetailController', FamilleDetailController);

    FamilleDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'Famille', 'Kafala', 'Enfant','$window'];

    function FamilleDetailController($scope, $rootScope, $stateParams, previousState, entity, Famille, Kafala, Enfant, $window) {
        var vm = this;

        vm.famille = entity;
        vm.previousState = previousState.name;

          vm.print = function(){
            $window.print();

        }

        var unsubscribe = $rootScope.$on('assalamApp:familleUpdate', function(event, result) {
            vm.famille = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
