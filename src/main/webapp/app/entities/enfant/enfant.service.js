(function() {
    'use strict';
    angular
        .module('assalamApp')
        .factory('Enfant', Enfant);

    Enfant.$inject = ['$resource'];

    function Enfant($resource) {
        var resourceUrl =  'api/enfants/:id';

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
