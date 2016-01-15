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
    $http.get('/view')
    .then((response) => {
        $scope.messages = response.data
        $scope.messages.forEach((item) => {
            item.date = new Date(item.updated)
        })
    })
})
