(function() {
    'use strict';
    angular
        .module('assalamApp')
        .factory('EnfantSearch', EnfantSearch);

    EnfantSearch.$inject = ['$resource'];

    function EnfantSearch ($resource) {
        var resourceUrl =  'api/enfants/search';

        return $resource(resourceUrl, {}, {
            'query': { method: 'GET', isArray: true},
           
                });
    }
})();


