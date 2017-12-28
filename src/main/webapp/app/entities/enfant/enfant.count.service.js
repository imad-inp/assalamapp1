(function() {
    'use strict';
    angular
        .module('assalamApp')
        .factory('EnfantCount', EnfantCount);

    EnfantCount.$inject = ['$resource'];

    function EnfantCount ($resource) {
        var resourceUrl =  'api/enfants/count';

        return $resource(resourceUrl, {}, {
            'query': { method: 'GET', isArray: false},
           
                });
    }
})();


