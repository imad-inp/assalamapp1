(function() {
    'use strict';

    angular
        .module('assalamApp')
        .controller('ReminderDetailController', ReminderDetailController);

    ReminderDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'Reminder', 'Kafil'];

    function ReminderDetailController($scope, $rootScope, $stateParams, previousState, entity, Reminder, Kafil) {
        var vm = this;

        vm.reminder = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('assalamApp:reminderUpdate', function(event, result) {
            vm.reminder = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
