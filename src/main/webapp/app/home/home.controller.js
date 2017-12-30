(function() {
    'use strict';

    angular
        .module('assalamApp')
        .controller('HomeController', HomeController);

    HomeController.$inject = ['$scope', 'Principal', 'LoginService', '$state','$http','$interval'];

    function HomeController ($scope, Principal, LoginService, $state,$http,$interval) {
        var vm = this;

        vm.account = null;
        vm.isAuthenticated = null;
        vm.login = LoginService.open;
        vm.register = register;
        $scope.$on('authenticationSuccess', function() {
            getAccount();
        });

        getAccount();

        function getAccount() {
            Principal.identity().then(function(account) {
                vm.account = account;
                vm.isAuthenticated = Principal.isAuthenticated;
            });
        }
        function register () {
            $state.go('register');
        }

         $interval(wakeUp, 300000);// keep heroku app awake
         function wakeUp(){
             $http.get("http://assalamapp2.herokuapp.com");
           
         }
       

        
    }
})();
