'use strict';

describe('Controller Tests', function() {

    describe('Resultatsscolaires Management Detail Controller', function() {
        var $scope, $rootScope;
        var MockEntity, MockPreviousState, MockResultatsscolaires, MockEnfant;
        var createController;

        beforeEach(inject(function($injector) {
            $rootScope = $injector.get('$rootScope');
            $scope = $rootScope.$new();
            MockEntity = jasmine.createSpy('MockEntity');
            MockPreviousState = jasmine.createSpy('MockPreviousState');
            MockResultatsscolaires = jasmine.createSpy('MockResultatsscolaires');
            MockEnfant = jasmine.createSpy('MockEnfant');
            

            var locals = {
                '$scope': $scope,
                '$rootScope': $rootScope,
                'entity': MockEntity,
                'previousState': MockPreviousState,
                'Resultatsscolaires': MockResultatsscolaires,
                'Enfant': MockEnfant
            };
            createController = function() {
                $injector.get('$controller')("ResultatsscolairesDetailController", locals);
            };
        }));


        describe('Root Scope Listening', function() {
            it('Unregisters root scope listener upon scope destruction', function() {
                var eventType = 'assalamApp:resultatsscolairesUpdate';

                createController();
                expect($rootScope.$$listenerCount[eventType]).toEqual(1);

                $scope.$destroy();
                expect($rootScope.$$listenerCount[eventType]).toBeUndefined();
            });
        });
    });

});
