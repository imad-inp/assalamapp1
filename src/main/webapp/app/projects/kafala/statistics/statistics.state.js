(function() {
    'use strict';

    angular
        .module('assalamApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider.state('statistics', {
            parent: 'kafala-project',
            url: '/statistics',
            data: {
                authorities: ['ROLE_KAFALA'],
                pageTitle: 'assalamApp.enfant.home.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/projects/kafala/statistics/statistics.html',
                    controller: 'DashboardController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                   
                    $translatePartialLoader.addPart('global');
                    return $translate.refresh();
                }]
            }
        })
     ;
    }
})();
