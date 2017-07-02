(function() {
    'use strict';

    angular
        .module('assalamApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('resultatsscolaires-filter', {
            parent: 'kafala-project',
            url: '/resultatsscolaires?enfantId',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'assalamApp.resultatsscolaires.home.title',
                
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/resultatsscolaires/resultatsscolaires.html',
                    controller: 'ResultatsscolairesController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('resultatsscolaires');
                    $translatePartialLoader.addPart('global');
                    return $translate.refresh();
                }]
            }
        })
        .state('resultatsscolaires', {
            parent: 'kafala-project',
            url: '/resultatsscolaires',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'assalamApp.resultatsscolaires.home.title',
                
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/resultatsscolaires/resultatsscolaires.html',
                    controller: 'ResultatsscolairesController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('resultatsscolaires');
                    $translatePartialLoader.addPart('global');
                    return $translate.refresh();
                }]
            }
        })
        .state('resultatsscolaires-detail', {
            parent: 'resultatsscolaires-filter',
            url: '/resultatsscolaires/{id}',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'assalamApp.resultatsscolaires.detail.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/resultatsscolaires/resultatsscolaires-detail.html',
                    controller: 'ResultatsscolairesDetailController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('resultatsscolaires');
                    return $translate.refresh();
                }],
                entity: ['$stateParams', 'Resultatsscolaires', function($stateParams, Resultatsscolaires) {
                    return Resultatsscolaires.get({id : $stateParams.id}).$promise;
                }],
                previousState: ["$state", function ($state) {
                    var currentStateData = {
                        name: $state.current.name || 'resultatsscolaires-filter',
                        params: $state.params,
                        url: $state.href($state.current.name, $state.params)
                    };
                    return currentStateData;
                }]
            }
        })
        .state('resultatsscolaires-detail.edit', {
            parent: 'resultatsscolaires-detail',
            url: '/detail/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/resultatsscolaires/resultatsscolaires-dialog.html',
                    controller: 'ResultatsscolairesDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Resultatsscolaires', function(Resultatsscolaires) {
                            return Resultatsscolaires.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('^', {}, { reload: false });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('resultatsscolaires.new', {
            parent: 'resultatsscolaires-filter',
            url: '/new',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/resultatsscolaires/resultatsscolaires-dialog.html',
                    controller: 'ResultatsscolairesDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: function () {
                            return {
                                annee: null,
                                description: null,
                                id: null
                                
                            };
                        }
                    }
                }).result.then(function() {
                    $state.go('resultatsscolaires-filter', null, { reload: 'resultatsscolaires-filter' });
                }, function() {
                    $state.go('resultatsscolaires-filter');
                });
            }]
        })
        .state('resultatsscolaires.edit', {
            parent: 'resultatsscolaires-filter',
            url: '/{id}/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/resultatsscolaires/resultatsscolaires-dialog.html',
                    controller: 'ResultatsscolairesDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Resultatsscolaires', function(Resultatsscolaires) {
                            return Resultatsscolaires.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('resultatsscolaires', null, { reload: 'resultatsscolaires' });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('resultatsscolaires.delete', {
            parent: 'resultatsscolaires',
            url: '/{id}/delete',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/resultatsscolaires/resultatsscolaires-delete-dialog.html',
                    controller: 'ResultatsscolairesDeleteController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['Resultatsscolaires', function(Resultatsscolaires) {
                            return Resultatsscolaires.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('resultatsscolaires', null, { reload: 'resultatsscolaires' });
                }, function() {
                    $state.go('^');
                });
            }]
        });
    }

})();
