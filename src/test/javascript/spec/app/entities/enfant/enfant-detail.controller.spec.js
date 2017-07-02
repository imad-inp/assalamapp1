'use strict';

describe('Controller Tests', function() {

    describe('Enfant Management Detail Controller', function() {
        var $scope, $rootScope;
        var MockEntity, MockPreviousState, MockEnfant, MockKafala, MockResultatsscolaires, MockFamille;
        var createController;

        beforeEach(inject(function($injector) {
            $rootScope = $injector.get('$rootScope');
            $scope = $rootScope.$new();
            MockEntity = jasmine.createSpy('MockEntity');
            MockPreviousState = jasmine.createSpy('MockPreviousState');
            MockEnfant = jasmine.createSpy('MockEnfant');
            MockKafala = jasmine.createSpy('MockKafala');
            MockResultatsscolaires = jasmine.createSpy('MockResultatsscolaires');
            MockFamille = jasmine.createSpy('MockFamille');
            

            var locals = {
                '$scope': $scope,
                '$rootScope': $rootScope,
                'entity': MockEntity,
                'previousState': MockPreviousState,
                'Enfant': MockEnfant,
                'Kafala': MockKafala,
                'Resultatsscolaires': MockResultatsscolaires,
                'Famille': MockFamille
            };
            createController = function() {
                $injector.get('$controller')("EnfantDetailController", locals);
            };
        }));


        describe('Root Scope Listening', function() {
            it('Unregisters root scope listener upon scope destruction', function() {
                var eventType = 'assalamApp:enfantUpdate';

                createController();
                expect($rootScope.$$listenerCount[eventType]).toEqual(1);

                $scope.$destroy();
                expect($rootScope.$$listenerCount[eventType]).toBeUndefined();
            });
        });
    });

});
