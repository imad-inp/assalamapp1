(function() {
    'use strict';
    angular
        .module('assalamApp')
        .factory('KafilCount', KafilCount);

    KafilCount.$inject = ['$resource'];

    function KafilCount ($resource) {
        var resourceUrl =  'api/kafils/count';

        return $resource(resourceUrl, {}, {
            'query': { method: 'GET', isArray: false},
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
