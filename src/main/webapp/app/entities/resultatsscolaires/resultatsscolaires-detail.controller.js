(function() {
    'use strict';

    angular
        .module('assalamApp')
        .controller('ResultatsscolairesDetailController', ResultatsscolairesDetailController);

    ResultatsscolairesDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'Resultatsscolaires', 'Enfant'];

    function ResultatsscolairesDetailController($scope, $rootScope, $stateParams, previousState, entity, Resultatsscolaires, Enfant) {
        var vm = this;

        vm.resultatsscolaires = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('assalamApp:resultatsscolairesUpdate', function(event, result) {
            vm.resultatsscolaires = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
