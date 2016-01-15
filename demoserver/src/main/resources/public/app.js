var app = angular.module('myApp',['ngRoute'])

app.config(function ($routeProvider) {
    $routeProvider

    .when('/', {
        templateUrl: 'views/main.html',
        controller: 'MainCtrl'
    })
    .otherwise({
        redirectTo: '/'
    })
})

app.controller('MainCtrl', function($scope, $http){
    $scope.getHostilePlanets = () => {
        $http.get('/planets?hostile=true&location=1,2,3')
        .then((response) => {
            $scope.planets = response.data
        })
    }

    $scope.setTarget = (target) => {
        $scope.target = target
    }

    $scope.obliterate = (target) => {
        $http.delete('/planet/' + target.name)
        .then((response) => {
            console.log(response)
        }, (response) => {
            console.err(response)
        })
    }
})