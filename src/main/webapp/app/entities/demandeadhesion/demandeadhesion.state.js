(function() {
    'use strict';

    angular
        .module('assalamApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('demandeadhesion', {
            parent: 'kafala-project',
            url: '/demandeadhesion?statut&familleId&page&sort&search',
            data: {
                authorities: ['ROLE_INAYA'],
                pageTitle: 'assalamApp.demandeadhesion.home.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/demandeadhesion/demandeadhesions.html',
                    controller: 'DemandeadhesionController',
                    controllerAs: 'vm'
                }
            },
             params: {
                 statut : {
                    value: null,
                    squash: true
                },
                page: {
                    value: '1',
                    squash: true
                },
                sort: {
                    value: 'datedemande,asc',
                    squash: true
                },
                search: null
            },
            resolve: {
                pagingParams: ['$stateParams', 'PaginationUtil', function ($stateParams, PaginationUtil) {
                    return {
                        page: PaginationUtil.parsePage($stateParams.page),
                        sort: $stateParams.sort === null ? 'datedemande,asc' :  $stateParams.sort   ,
                        predicate: PaginationUtil.parsePredicate($stateParams.sort),
                        ascending: PaginationUtil.parseAscending($stateParams.sort),
                        search: $stateParams.search,
                        statut: $stateParams.statut === null ? 'OUVERTE' :  $stateParams.statut
                    };
                }],
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('demandeadhesion');
                    $translatePartialLoader.addPart('statut');
                    $translatePartialLoader.addPart('global');
                    return $translate.refresh();
                }]
            }
        })
        .state('demandeadhesion-detail', {
            parent: 'demandeadhesion',
            url: '/demandeadhesion/{id}',
            data: {
                authorities: ['ROLE_INAYA'],
                pageTitle: 'assalamApp.demandeadhesion.detail.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/demandeadhesion/demandeadhesion-detail.html',
                    controller: 'DemandeadhesionDetailController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('demandeadhesion');
                    $translatePartialLoader.addPart('statut');
                    return $translate.refresh();
                }],
                entity: ['$stateParams', 'Demandeadhesion', function($stateParams, Demandeadhesion) {
                    return Demandeadhesion.get({id : $stateParams.id}).$promise;
                }],
                previousState: ["$state", function ($state) {
                    var currentStateData = {
                        name: $state.current.name || 'demandeadhesion',
                        params: $state.params,
                        url: $state.href($state.current.name, $state.params)
                    };
                    return currentStateData;
                }]
            }
        })
        .state('demandeadhesion-detail.edit', {
            parent: 'demandeadhesion-detail',
            url: '/detail/edit',
            data: {
                authorities: ['ROLE_INAYA']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/demandeadhesion/demandeadhesion-dialog.html',
                    controller: 'DemandeadhesionDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Demandeadhesion', function(Demandeadhesion) {
                            return Demandeadhesion.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('^', {}, { reload: false });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('demandeadhesion.new', {
            parent: 'demandeadhesion',
            url: '/new',
            data: {
                authorities: ['ROLE_INAYA']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/demandeadhesion/demandeadhesion-create-dialog.html',
                    controller: 'DemandeadhesionDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: function () {
                            return {
                                datedemande: null,
                                statut: 'OUVERTE',
                                id: null,
								famille:{id: $stateParams.familleId}
                            };
                        }
                    }
                }).result.then(function() {
                    $state.go('demandeadhesion', null, { reload: 'demandeadhesion' });
                }, function() {
                    $state.go('demandeadhesion');
                });
            }]
        })
        .state('demandeadhesion.edit', {
            parent: 'demandeadhesion',
            url: '/{id}/edit',
            data: {
                authorities: ['ROLE_INAYA']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/demandeadhesion/demandeadhesion-dialog.html',
                    controller: 'DemandeadhesionDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Demandeadhesion', function(Demandeadhesion) {
                            return Demandeadhesion.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('demandeadhesion', null, { reload: 'demandeadhesion' });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('demandeadhesion.delete', {
            parent: 'demandeadhesion',
            url: '/{id}/delete',
            data: {
                authorities: ['ROLE_INAYA']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/demandeadhesion/demandeadhesion-delete-dialog.html',
                    controller: 'DemandeadhesionDeleteController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['Demandeadhesion', function(Demandeadhesion) {
                            return Demandeadhesion.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('demandeadhesion', null, { reload: 'demandeadhesion' });
                }, function() {
                    $state.go('^');
                });
            }]
        });
    }

})();
