(function() {
    'use strict';
    angular
        .module('assalamApp')
        .factory('KafilSearch', KafilSearch);

    KafilSearch.$inject = ['$resource'];

    function KafilSearch ($resource) {
        var resourceUrl =  'api/kafils/search';

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
