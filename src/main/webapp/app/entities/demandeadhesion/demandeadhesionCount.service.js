(function() {
    'use strict';

    angular
        .module('assalamApp')
        .factory('DemandeadhesionCount', DemandeadhesionCount);

    DemandeadhesionCount.$inject = ['$resource'];

    function DemandeadhesionCount($resource) {
        var resourceUrl =  'api/count/demandeadhesions';

        return $resource(resourceUrl, {}, {
            'query': { method: 'GET', isArray: false}
        });
    }
})();
