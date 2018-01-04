(function() {
    'use strict';

    angular
        .module('assalamApp')
        .controller('PaiementController', PaiementController);

    PaiementController.$inject = ['Paiement', 'ParseLinks', 'AlertService', 'paginationConstants', '$stateParams','DataUtils', 'PaiementPdf','$scope' ,'$sce', '$window', 'Files'];

    function PaiementController(Paiement, ParseLinks, AlertService, paginationConstants, $stateParams, DataUtils, PaiementPdf,$scope , $sce, $window, Files) {

        var vm = this;

        vm.paiements = [];
        vm.openFile = DataUtils.openFile;
        vm.loadPage = loadPage;
        vm.itemsPerPage = paginationConstants.itemsPerPage;
        vm.page = 0;
        vm.links = {
            last: 0
        };
        vm.predicate = 'id';
        vm.reset = reset;
        vm.reverse = true;
       
        
        vm.filter_kafalaId = $stateParams.kafalaId;

        loadAll();

        
        vm.print = function(){
            $window.print();
           

        }
           vm.openFile = function(paiement){
             var paiement = Files.get({id: paiement.evidenceRef}, function(result){
                  $scope.content = $sce.trustAsResourceUrl('data:' + result.fileContentType + ';base64,' + result.file);
         
            var link = document.createElement("a");
            link.setAttribute("href", $scope.content );
            link.setAttribute("target", "_blank");
             link.setAttribute("download", "picture");
           
             document.body.appendChild(link); // Required for FF
            link.click(); // This will download the data file named "download_name.pdf"
             });
            
           
        }

        function loadAll () {
            Paiement.query({
                page: vm.page,
                size: vm.itemsPerPage,
                sort: sort(),
                kafalaId : $stateParams.kafalaId
            }, onSuccess, onError);
            function sort() {
                var result = [vm.predicate + ',' + (vm.reverse ? 'asc' : 'desc')];
                if (vm.predicate !== 'id') {
                    result.push('id');
                }
                return result;
            }

            function onSuccess(data, headers) {               
                vm.totalItems = headers('X-Total-Count');
                for (var i = 0; i < data.length; i++) {
                    vm.paiements.push(data[i]);
                }
            }

            function onError(error) {
                AlertService.error(error.data.message);
            }
        }

          
        function reset () {
            vm.page = 0;
            vm.paiements = [];
            loadAll();
        }

        function loadPage(page) {
            vm.page = page;
            loadAll();
        }
    }
})();
