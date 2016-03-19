dependencies = [
    'ngRoute',
    'ui.bootstrap',
    'demoApp.routeConfig'
    'demoApp.services',
    'demoApp.controllers',
]

app = angular.module('demoApp', dependencies)

angular.module('demoApp.routeConfig', ['ngRoute'])
    .config(['$routeProvider', ($routeProvider) ->
        $routeProvider
            .when('/', {
                templateUrl: '/assets/html/products.html'
            })
            .when('/product/:slug', {
                templateUrl: '/assets/html/details.html'
            })
            .otherwise({redirectTo: '/'})])
    .config(['$locationProvider', ($locationProvider) ->
        $locationProvider.html5Mode({
            enabled: true,
            requireBase: false
        })])

@controllersModule = angular.module('demoApp.controllers', [])
@servicesModule = angular.module('demoApp.services', [])
