(function() {
    'use strict';

    angular
        .module('assalamApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('reminder', {
            parent: 'entity',
            url: '/reminder',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'assalamApp.reminder.home.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/reminder/reminders.html',
                    controller: 'ReminderController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('reminder');
                    $translatePartialLoader.addPart('global');
                    return $translate.refresh();
                }]
            }
        })
        .state('reminder-detail', {
            parent: 'reminder',
            url: '/reminder/{id}',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'assalamApp.reminder.detail.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/reminder/reminder-detail.html',
                    controller: 'ReminderDetailController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('reminder');
                    return $translate.refresh();
                }],
                entity: ['$stateParams', 'Reminder', function($stateParams, Reminder) {
                    return Reminder.get({id : $stateParams.id}).$promise;
                }],
                previousState: ["$state", function ($state) {
                    var currentStateData = {
                        name: $state.current.name || 'reminder',
                        params: $state.params,
                        url: $state.href($state.current.name, $state.params)
                    };
                    return currentStateData;
                }]
            }
        })
        .state('reminder-detail.edit', {
            parent: 'reminder-detail',
            url: '/detail/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/reminder/reminder-dialog.html',
                    controller: 'ReminderDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Reminder', function(Reminder) {
                            return Reminder.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('^', {}, { reload: false });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('reminder.new', {
            parent: 'reminder',
            url: '/new',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/reminder/reminder-dialog.html',
                    controller: 'ReminderDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: function () {
                            return {
                                date: null,
                                type: null,
                                id: null
                            };
                        }
                    }
                }).result.then(function() {
                    $state.go('reminder', null, { reload: 'reminder' });
                }, function() {
                    $state.go('reminder');
                });
            }]
        })
        .state('reminder.edit', {
            parent: 'reminder',
            url: '/{id}/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/reminder/reminder-dialog.html',
                    controller: 'ReminderDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Reminder', function(Reminder) {
                            return Reminder.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('reminder', null, { reload: 'reminder' });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('reminder.delete', {
            parent: 'reminder',
            url: '/{id}/delete',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/reminder/reminder-delete-dialog.html',
                    controller: 'ReminderDeleteController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['Reminder', function(Reminder) {
                            return Reminder.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('reminder', null, { reload: 'reminder' });
                }, function() {
                    $state.go('^');
                });
            }]
        });
    }

})();
