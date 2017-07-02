(function() {
    'use strict';
    angular
        .module('assalamApp')
        .factory('Resultatsscolaires', Resultatsscolaires);

    Resultatsscolaires.$inject = ['$resource'];

    function Resultatsscolaires ($resource) {
        var resourceUrl =  'api/resultatsscolaires/:id';

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
