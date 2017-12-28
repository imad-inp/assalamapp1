(function() {
    'use strict';

    angular
        .module('assalamApp')
        .factory('KafalaCount', KafalaCount);

    KafalaCount.$inject = ['$resource'];

    function KafalaCount($resource) {
        var resourceUrl =  'api/count/latekafalas';

        return $resource(resourceUrl, {}, {
            'query': { method: 'GET', isArray: false}
        });
    }
})();
