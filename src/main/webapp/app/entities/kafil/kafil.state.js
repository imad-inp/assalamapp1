(function() {
    'use strict';

    angular
        .module('assalamApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('kafil', {
            parent: 'kafala-project',
            url: '/kafil',
            data: {
                authorities: ['ROLE_KAFALA'],
                pageTitle: 'assalamApp.kafil.home.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/kafil/kafils.html',
                    controller: 'KafilController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('kafil');
                    $translatePartialLoader.addPart('global');
                    return $translate.refresh();
                }]
            }
        })
        .state('kafil-detail', {
            parent: 'kafil',
            url: '/kafil/{id}',
            data: {
                authorities: ['ROLE_KAFALA'],
                pageTitle: 'assalamApp.kafil.detail.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/kafil/kafil-detail.html',
                    controller: 'KafilDetailController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('kafil');
                    return $translate.refresh();
                }],
                entity: ['$stateParams', 'Kafil', function($stateParams, Kafil) {
                    return Kafil.get({id : $stateParams.id}).$promise;
                }],
                previousState: ["$state", function ($state) {
                    var currentStateData = {
                        name: $state.current.name || 'kafil',
                        params: $state.params,
                        url: $state.href($state.current.name, $state.params)
                    };
                    return currentStateData;
                }]
            }
        })
        .state('kafil-detail.edit', {
            parent: 'kafil-detail',
            url: '/detail/edit',
            data: {
                authorities: ['ROLE_KAFALA']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/kafil/kafil-dialog.html',
                    controller: 'KafilDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Kafil', function(Kafil) {
                            return Kafil.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('^', {}, { reload: false });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('kafil.new', {
            parent: 'kafil',
            url: '/new',
            data: {
                authorities: ['ROLE_KAFALA']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/kafil/kafil-dialog.html',
                    controller: 'KafilDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: function () {
                            return {
                                nom: null,
                                prenom: null,
                                adresse: null,
                                tel: null,
                                membre: null,
                                commentaires: null,
                                photo: null,
                                photoContentType: null,
                                id: null
                            };
                        }
                    }
                }).result.then(function() {
                    $state.go('kafil', null, { reload: 'kafil' });
                }, function() {
                    $state.go('kafil');
                });
            }]
        })
        .state('kafil.edit', {
            parent: 'kafil',
            url: '/{id}/edit',
            data: {
                authorities: ['ROLE_KAFALA']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/kafil/kafil-dialog.html',
                    controller: 'KafilDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Kafil', function(Kafil) {
                            return Kafil.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('kafil', null, { reload: 'kafil' });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('kafil.delete', {
            parent: 'kafil',
            url: '/{id}/delete',
            data: {
                authorities: ['ROLE_KAFALA']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/kafil/kafil-delete-dialog.html',
                    controller: 'KafilDeleteController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['Kafil', function(Kafil) {
                            return Kafil.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('kafil', null, { reload: 'kafil' });
                }, function() {
                    $state.go('^');
                });
            }]
        });
    }

})();
