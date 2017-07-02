(function() {
    'use strict';
    angular
        .module('assalamApp')
        .factory('Kafil', Kafil);

    Kafil.$inject = ['$resource'];

    function Kafil ($resource) {
        var resourceUrl =  'api/kafils/:id';

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
