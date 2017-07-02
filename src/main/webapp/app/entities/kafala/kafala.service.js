(function() {
    'use strict';
    angular
        .module('assalamApp')
        .factory('Kafala', Kafala);

    Kafala.$inject = ['$resource', 'DateUtils'];

    function Kafala ($resource, DateUtils) {
        var resourceUrl =  'api/kafalas/:id';

        return $resource(resourceUrl, {}, {
            'query': { method: 'GET', isArray: true},
            'get': {
                method: 'GET',
                transformResponse: function (data) {
                    if (data) {
                        data = angular.fromJson(data);
                        data.datedebut = DateUtils.convertLocalDateFromServer(data.datedebut);
                    }
                    return data;
                }
            },
            'update': {
                method: 'PUT',
                transformRequest: function (data) {
                    var copy = angular.copy(data);
                    copy.datedebut = DateUtils.convertLocalDateToServer(copy.datedebut);
                    return angular.toJson(copy);
                }
            },
            'save': {
                method: 'POST',
                transformRequest: function (data) {
                    var copy = angular.copy(data);
                    copy.datedebut = DateUtils.convertLocalDateToServer(copy.datedebut);
                    return angular.toJson(copy);
                }
            }
        });
    }
})();
