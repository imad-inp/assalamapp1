(function() {
    'use strict';

    angular
        .module('assalamApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('kafala', {
            parent: 'kafala-project',
            url: '/kafalat?kafilId?late',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'assalamApp.kafala.home.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/kafala/kafalas.html',
                    controller: 'KafalaController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('kafala');
                    $translatePartialLoader.addPart('global');
                    return $translate.refresh();
                }]
            }
        })
        .state('kafala-detail', {
            parent: 'kafala',
            url: '/kafalat/{id}',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'assalamApp.kafala.detail.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/kafala/kafala-detail.html',
                    controller: 'KafalaDetailController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('kafala');
                    return $translate.refresh();
                }],
                entity: ['$stateParams', 'Kafala', function($stateParams, Kafala) {
                    return Kafala.get({id : $stateParams.id}).$promise;
                }],
                previousState: ["$state", function ($state) {
                    var currentStateData = {
                        name: $state.current.name || 'kafala',
                        params: $state.params,
                        url: $state.href($state.current.name, $state.params)
                    };
                    return currentStateData;
                }]
            }
        })
        .state('kafala-detail.edit', {
            parent: 'kafala-detail',
            url: '/detail/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/kafala/kafala-dialog.html',
                    controller: 'KafalaDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Kafala', function(Kafala) {
                            return Kafala.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('^', {}, { reload: false });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('kafala.new', {
            parent: 'kafala',
            url: '/new?enfantId',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal','Enfant', function($stateParams, $state, $uibModal, Enfant) {
                $uibModal.open({
                    templateUrl: 'app/entities/kafala/kafala-dialog.html',
                    controller: 'KafalaDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: function () {
                            return {
                                montant: null,
                                datedebut: null,
                                id: null,
                                enfant : $stateParams.enfantId === null ? null : Enfant.get({id : $stateParams.enfantId})
                      
                            };
                        }
                    }
                }).result.then(function() {
                    $state.go('kafala', null, { reload: 'kafala' });
                }, function() {
                    $state.go('kafala');
                });
            }]
        })
        .state('kafala.edit', {
            parent: 'kafala',
            url: '/{id}/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/kafala/kafala-dialog.html',
                    controller: 'KafalaDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Kafala', function(Kafala) {
                            return Kafala.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('kafala', null, { reload: 'kafala' });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('kafala.paiements', {
            parent: 'kafala',
            url: '/{id}/paiments',
            data: {
                authorities: ['ROLE_USER']
            },
             views: {
                'paiements@': {
                    templateUrl: 'app/entities/paiement/paiements.html',
                    controller: 'PaiementController',
                    controllerAs: 'vm'
                }
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/kafala/kafala-paiements.html',
                    controller: 'KafalaDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Kafala', function(Kafala) {
                            return Kafala.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('kafala', null, { reload: 'kafala' });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('kafala.delete', {
            parent: 'kafala',
            url: '/{id}/delete',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/kafala/kafala-delete-dialog.html',
                    controller: 'KafalaDeleteController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['Kafala', function(Kafala) {
                            return Kafala.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('kafala', null, { reload: 'kafala' });
                }, function() {
                    $state.go('^');
                });
            }]
        });
    }

})();
