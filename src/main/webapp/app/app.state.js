(function() {
    'use strict';

    angular
        .module('assalamApp')
        .config(stateConfig)
        .controller('MainCtrl', ['$scope','$translate', function($scope,$translate) {
            var vm = this;
    
    vm.currentLang =  $translate.proposedLanguage();
       
    $scope.$on('langChanged', function (event, args) {
     
      vm.currentLang = $translate.proposedLanguage();
     });

    }]);;

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider.state('app', {
            abstract: true,
            views: {
                'navbar@': {
                    templateUrl: 'app/layouts/navbar/navbar.html',
                    controller: 'NavbarController',
                    controllerAs: 'vm'
                },
                 'banner@': {
                    templateUrl: 'app/layouts/header/header.html',
                    controller: 'NavbarController',
                    controllerAs: 'vm'
                }
                
            },
            resolve: {
                authorize: ['Auth',
                    function (Auth) {
                        return Auth.authorize();
                    }
                ],
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('global');
                    $translatePartialLoader.addPart('enfant');
                    $translatePartialLoader.addPart('kafala');
                }]
            }
        });
    }
})();
