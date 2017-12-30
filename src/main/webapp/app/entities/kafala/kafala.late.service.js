(function() {
    'use strict';

    angular
        .module('assalamApp')
        .factory('KafalaLate', KafalaLate);

    KafalaLate.$inject = ['$resource'];

    function KafalaLate($resource) {
        var resourceUrl =  'api/kafalas/late';

        return $resource(resourceUrl, {}, {
            'query': { method: 'GET', isArray: true}
        });
    }
})();
