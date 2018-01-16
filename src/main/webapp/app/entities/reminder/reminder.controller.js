(function() {
    'use strict';

    angular
        .module('assalamApp')
        .controller('ReminderController', ReminderController);

    ReminderController.$inject = ['Reminder'];

    function ReminderController(Reminder) {

        var vm = this;

        vm.reminders = [];

        loadAll();

        function loadAll() {
            Reminder.query(function(result) {
                vm.reminders = result;
                vm.searchQuery = null;
            });
        }
    }
})();
