(function() {
    'use strict';

    angular
        .module('assalamApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('enfant', {
            parent: 'kafala-project',
            url: '/enfant?familleFilterId&statut',
            data: {
                authorities: ['ROLE_INAYA'],
                pageTitle: 'assalamApp.enfant.home.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/enfant/enfants.html',
                    controller: 'EnfantController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('enfant');
                    $translatePartialLoader.addPart('global');
                    return $translate.refresh();
                }]
            }
        })
        .state('enfant-detail', {
            parent: 'enfant',
            url: '/enfant/{id}',
            data: {
                authorities: ['ROLE_INAYA'],
                pageTitle: 'assalamApp.enfant.detail.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/enfant/enfant-detail.html',
                    controller: 'EnfantDetailController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('enfant');
                    return $translate.refresh();
                }],
                entity: ['$stateParams', 'Enfant', function($stateParams, Enfant) {
                    return Enfant.get({id : $stateParams.id}).$promise;
                }],
                previousState: ["$state", function ($state) {
                    var currentStateData = {
                        name: $state.current.name || 'enfant',
                        params: $state.params,
                        url: $state.href($state.current.name, $state.params)
                    };
                    return currentStateData;
                }]
            }
        })
        .state('enfant-detail.edit', {
            parent: 'enfant-detail',
            url: '/detail/edit',
            data: {
                authorities: ['ROLE_INAYA']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/enfant/enfant-dialog.html',
                    controller: 'EnfantDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Enfant', function(Enfant) {
                            return Enfant.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('^', {}, { reload: false });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('enfant.new', {
            parent: 'enfant',
            url: '/new?familleId',
            data: {
                authorities: ['ROLE_INAYA']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/enfant/enfant-create-dialog.html',
                    controller: 'EnfantDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: function () {
                            return {
                                nom: null,
                                prenom: null,
                                age: null,
                                adresse: null,
                                tel: null,
                                photo: null,
                                photoContentType: null,
                                commentaires: null,
                                id: null,
								famille: {id: $stateParams.familleId}
                            };
                        }
                    }
                }).result.then(function() {
                    $state.go('enfant', null, { reload: 'enfant' });
                }, function() {
                    $state.go('enfant');
                });
            }]
        })
        .state('enfant.edit', {
            parent: 'enfant',
            url: '/{id}/edit',
            data: {
                authorities: ['ROLE_INAYA']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/enfant/enfant-dialog.html',
                    controller: 'EnfantDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Enfant', function(Enfant) {
                            return Enfant.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('enfant', null, { reload: 'enfant' });
                }, function() {
                    $state.go('^');
                });
            }]
        })
		
        .state('enfant.delete', {
            parent: 'enfant',
            url: '/{id}/delete',
            data: {
                authorities: ['ROLE_INAYA']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/enfant/enfant-delete-dialog.html',
                    controller: 'EnfantDeleteController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['Enfant', function(Enfant) {
                            return Enfant.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('enfant', null, { reload: 'enfant' });
                }, function() {
                    $state.go('^');
                });
            }]
        });
    }

})();
