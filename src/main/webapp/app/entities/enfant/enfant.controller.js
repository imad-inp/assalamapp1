(function () {
    'use strict';

    angular
            .module('assalamApp')
            .controller('EnfantController', EnfantController);

    EnfantController.$inject = ['DataUtils', 'Enfant', 'ParseLinks', 'AlertService', 'paginationConstants','$stateParams','$scope','$sce'];

    function EnfantController(DataUtils, Enfant, ParseLinks, AlertService, paginationConstants, $stateParams,$scope,$sce) {

        var vm = this;

        var currentDate = new Date();
		vm.familleFilterId = $stateParams.familleFilterId;
        vm.familleContext = ($stateParams.familleId == null);
        vm.enfants = [];
        vm.loadPage = loadPage;
        vm.itemsPerPage = paginationConstants.itemsPerPage;
        vm.page = 0;
        vm.links = {
            last: 0
        };
        vm.predicate = 'id';
        vm.reset = reset;
        vm.reverse = true;
        vm.openFile =  vm.openFile = function(file, fileType){
           
            $scope.content = $sce.trustAsResourceUrl('data:' +fileType + ';base64,' + file);
            var link = document.createElement("a");
            link.setAttribute("href", $scope.content );
            link.setAttribute("target", "_blank");
             link.setAttribute("download", "picture");
            console.log(link);
    
             document.body.appendChild(link); // Required for FF
            link.click(); // This will download the data file named "download_name.pdf"
           
        };
        vm.byteSize = DataUtils.byteSize;

        loadAll();

        vm.getAge = function (enfant) {
            var diff = currentDate - new Date(enfant.dateDeNaissance); // This is the difference in milliseconds
            return Math.floor(diff / 31557600000); // Divide by 1000*60*60*24*365.25F
        };


        function loadAll() {
            Enfant.query({
                page: vm.page,
                size: vm.itemsPerPage,
				familleId: vm.familleFilterId,
                statut: $stateParams.statut,
                sort: sort()
            }, onSuccess, onError);
            function sort() {
                var result = [vm.predicate + ',' + (vm.reverse ? 'asc' : 'desc')];
                if (vm.predicate !== 'id') {
                    result.push('id');
                }
                return result;
            }

            function onSuccess(data, headers) {
                vm.links = ParseLinks.parse(headers('link'));
                vm.totalItems = headers('X-Total-Count');
                for (var i = 0; i < data.length; i++) {
                    vm.enfants.push(data[i]);
                }
            }

            function onError(error) {
                AlertService.error(error.data.message);
            }
        }

        function reset() {
            vm.page = 0;
            vm.enfants = [];
            loadAll();
        }

        function loadPage(page) {
            vm.page = page;
            loadAll();
        }
    }
})();
