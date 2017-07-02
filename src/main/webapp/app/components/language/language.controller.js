(function() {
    'use strict';

    angular
        .module('assalamApp')
        .controller('JhiLanguageController', JhiLanguageController);

    JhiLanguageController.$inject = ['$translate', 'JhiLanguageService', 'tmhDynamicLocale','$scope'];

    function JhiLanguageController ($translate, JhiLanguageService, tmhDynamicLocale, $scope) {
        var vm = this;

        vm.changeLanguage = changeLanguage;
        vm.languages = null;

        JhiLanguageService.getAll().then(function (languages) {
            vm.languages = languages;
        });

        function changeLanguage (languageKey) {
            $translate.use(languageKey);
            tmhDynamicLocale.set(languageKey);
            $scope.$emit('langChanged', {});
        }
    }
})();
