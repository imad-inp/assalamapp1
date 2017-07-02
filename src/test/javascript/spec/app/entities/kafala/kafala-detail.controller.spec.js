'use strict';

describe('Controller Tests', function() {

    describe('Kafala Management Detail Controller', function() {
        var $scope, $rootScope;
        var MockEntity, MockPreviousState, MockKafala, MockPaiement, MockEnfant, MockFamille, MockKafil;
        var createController;

        beforeEach(inject(function($injector) {
            $rootScope = $injector.get('$rootScope');
            $scope = $rootScope.$new();
            MockEntity = jasmine.createSpy('MockEntity');
            MockPreviousState = jasmine.createSpy('MockPreviousState');
            MockKafala = jasmine.createSpy('MockKafala');
            MockPaiement = jasmine.createSpy('MockPaiement');
            MockEnfant = jasmine.createSpy('MockEnfant');
            MockFamille = jasmine.createSpy('MockFamille');
            MockKafil = jasmine.createSpy('MockKafil');
            

            var locals = {
                '$scope': $scope,
                '$rootScope': $rootScope,
                'entity': MockEntity,
                'previousState': MockPreviousState,
                'Kafala': MockKafala,
                'Paiement': MockPaiement,
                'Enfant': MockEnfant,
                'Famille': MockFamille,
                'Kafil': MockKafil
            };
            createController = function() {
                $injector.get('$controller')("KafalaDetailController", locals);
            };
        }));


        describe('Root Scope Listening', function() {
            it('Unregisters root scope listener upon scope destruction', function() {
                var eventType = 'assalamApp:kafalaUpdate';

                createController();
                expect($rootScope.$$listenerCount[eventType]).toEqual(1);

                $scope.$destroy();
                expect($rootScope.$$listenerCount[eventType]).toBeUndefined();
            });
        });
    });

});
