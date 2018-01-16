(function() {
    'use strict';
    angular
        .module('assalamApp')
        .factory('Reminder', Reminder);

    Reminder.$inject = ['$resource'];

    function Reminder ($resource) {
        var resourceUrl =  'api/reminders/:id';

        return $resource(resourceUrl, {}, {
            'query': { method: 'GET', isArray: true},
            'get': {
                method: 'GET',
                transformResponse: function (data) {
                    if (data) {
                        data = angular.fromJson(data);
                    }
                    return data;
                }
            },
            'update': { method:'PUT' }
        });
    }
})();
