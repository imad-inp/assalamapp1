'use strict';

describe('Controller Tests', function() {

    describe('Famille Management Detail Controller', function() {
        var $scope, $rootScope;
        var MockEntity, MockPreviousState, MockFamille, MockKafala, MockEnfant;
        var createController;

        beforeEach(inject(function($injector) {
            $rootScope = $injector.get('$rootScope');
            $scope = $rootScope.$new();
            MockEntity = jasmine.createSpy('MockEntity');
            MockPreviousState = jasmine.createSpy('MockPreviousState');
            MockFamille = jasmine.createSpy('MockFamille');
            MockKafala = jasmine.createSpy('MockKafala');
            MockEnfant = jasmine.createSpy('MockEnfant');
            

            var locals = {
                '$scope': $scope,
                '$rootScope': $rootScope,
                'entity': MockEntity,
                'previousState': MockPreviousState,
                'Famille': MockFamille,
                'Kafala': MockKafala,
                'Enfant': MockEnfant
            };
            createController = function() {
                $injector.get('$controller')("FamilleDetailController", locals);
            };
        }));


        describe('Root Scope Listening', function() {
            it('Unregisters root scope listener upon scope destruction', function() {
                var eventType = 'assalamApp:familleUpdate';

                createController();
                expect($rootScope.$$listenerCount[eventType]).toEqual(1);

                $scope.$destroy();
                expect($rootScope.$$listenerCount[eventType]).toBeUndefined();
            });
        });
    });

});
