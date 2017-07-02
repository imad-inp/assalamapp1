(function() {
    'use strict';

    angular
        .module('assalamApp')
        .controller('KafilDetailController', KafilDetailController);

    KafilDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'DataUtils', 'entity', 'Kafil', 'Kafala'];

    function KafilDetailController($scope, $rootScope, $stateParams, previousState, DataUtils, entity, Kafil, Kafala) {
        var vm = this;

        vm.kafil = entity;
        vm.previousState = previousState.name;
        vm.byteSize = DataUtils.byteSize;
        vm.openFile = DataUtils.openFile;

        var unsubscribe = $rootScope.$on('assalamApp:kafilUpdate', function(event, result) {
            vm.kafil = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
