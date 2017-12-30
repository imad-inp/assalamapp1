(function() {
    'use strict';

    angular
        .module('assalamApp')
        .controller('PaiementDetailController', PaiementDetailController);

    PaiementDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'Paiement', 'Kafala'];

    function PaiementDetailController($scope, $rootScope, $stateParams, previousState, entity, Paiement, Kafala) {
        var vm = this;

        vm.paiement = entity;
        vm.previousState = previousState.name;
        var node = document.getElementById('exportthis');

    domtoimage.toPng(node)
    .then(function (dataUrl) {
        var img = new Image();
        img.src = dataUrl;
         var link = document.createElement('a');
        link.download = 'my-image-name.jpeg';
        link.href = dataUrl;
        link.click();
    })
    .catch(function (error) {
        console.error('oops, something went wrong!', error);
    });

        var unsubscribe = $rootScope.$on('assalamApp:paiementUpdate', function(event, result) {
            vm.paiement = result;
        });
        $scope.$on('$destroy', unsubscribe);

        

    }
})();
