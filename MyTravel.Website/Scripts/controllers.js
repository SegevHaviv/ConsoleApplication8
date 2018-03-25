var myApp = angular.module("myApp", []);

myApp.controller("likesController",
    function ($scope, $http) {
        $scope.Model = { Step: 0, Options: [], Shown: [], Vector: [0, 0, 0, 0] };
        $scope.GetOptions = function () {
            $scope.CheckedCount = 0;
            $scope.Model.Step++;
            var checkeds = [];
            $scope.Model.Options.forEach(function (val) {
                if (val.IsChecked) {
                    checkeds.push(val.Id);
                }
            });
            $scope.Model.Choose = checkeds;
            if ($scope.Model.Step <= 3) {
                $http.get("/Likes/GetOptions",
                        { params: { Step: $scope.Model.Step, Shown: JSON.stringify($scope.Model.Shown), Choose: JSON.stringify($scope.Model.Choose), Vector: JSON.stringify($scope.Model.Vector) } })
                    .then(function (resp) {
                        $scope.Model = resp.data;
                    });
            } else {
                $http.get("/Likes/GetResults", { params: { Step: $scope.Model.Step, Shown: JSON.stringify($scope.Model.Shown), Choose: JSON.stringify($scope.Model.Choose), Vector: JSON.stringify($scope.Model.Vector) } }).then(function (re) {
                    $scope.Result = re.data;
                });
            }
        }
        $scope.itemClick = function (item) {
            item.IsChecked = !item.IsChecked;
            $scope.CheckedCount += item.IsChecked ? 1 : -1;
        }
        $scope.GetOptions();
    });

myApp.controller("placeController", function ($scope, $http) {

});