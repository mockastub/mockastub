var app = angular.module('myApp',['ngRoute'])

app.config(($routeProvider) => {
    $routeProvider

    .when('/', {
        templateUrl: 'views/main.html',
        controller: 'MainCtrl'
    })
    .otherwise({
        redirectTo: '/'
    })
})

app.controller('MainCtrl', ($scope, $http) => {
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
            $scope.oblitiration = response.data.message
            console.log(response)
        })
    }
})
