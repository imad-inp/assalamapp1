(function() {
    'use strict';

    angular
        .module('assalamApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider.state('kafala-project', {
            parent: 'app',
             abstract: true
 
        })
     ;
    }
})();
