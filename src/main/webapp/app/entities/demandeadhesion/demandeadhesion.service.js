(function() {
    'use strict';
    angular
        .module('assalamApp')
        .factory('Demandeadhesion', Demandeadhesion);

    Demandeadhesion.$inject = ['$resource', 'DateUtils'];

    function Demandeadhesion ($resource, DateUtils) {
        var resourceUrl =  'api/demandeadhesions/:id';

        return $resource(resourceUrl, {}, {
            'query': { method: 'GET', isArray: true},
            'get': {
                method: 'GET',
                transformResponse: function (data) {
                    if (data) {
                        data = angular.fromJson(data);
                        data.datedemande = DateUtils.convertLocalDateFromServer(data.datedemande);
                    }
                    return data;
                }
            },
            'update': {
                method: 'PUT',
                transformRequest: function (data) {
                    var copy = angular.copy(data);
                    copy.datedemande = DateUtils.convertLocalDateToServer(copy.datedemande);
                    return angular.toJson(copy);
                }
            },
            'save': {
                method: 'POST',
                transformRequest: function (data) {
                    var copy = angular.copy(data);
                    copy.datedemande = DateUtils.convertLocalDateToServer(copy.datedemande);
                    return angular.toJson(copy);
                }
            }
        });
    }
})();
