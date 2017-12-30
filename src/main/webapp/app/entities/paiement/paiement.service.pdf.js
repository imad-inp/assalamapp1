(function() {
    'use strict';
    angular
        .module('assalamApp')
        .factory('PaiementPdf', PaiementPdf);

    PaiementPdf.$inject = ['$resource', 'DateUtils'];

    function PaiementPdf ($resource, DateUtils) {
        var resourceUrl =  'api/paiements/pdf';

        return $resource(resourceUrl, {}, {
            pdf: {  
    method: 'POST',
    headers: {
        accept: 'application/pdf'
    },
    responseType: 'arraybuffer',
    cache: true,
     transformRequest: function (data) {
                    var copy = angular.copy(data);
                    return angular.toJson(copy);
                },
    transformResponse: function (data) {
        var pdf;
        if (data) {
            pdf = new Blob([data], {
                type: 'application/pdf'
            });
        }
        return {
            response: pdf
        };
    }
}        
           
        });
    }
})();
