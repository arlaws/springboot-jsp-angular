var app = angular.module("HomeManagement", []);

//Controller Part
app.controller("HomeApiController", function ($scope, $http, $location) {

    /* Référentiel */
    $scope.users = [];
    $scope.usersForm = {
        id: -1,
        nom: "",
        prenoms: "",
        email: "",
        contact: "",
        username: "",
        activated: false,
        validated: false
    };

    $scope.addNewUser = function () {
        var method = "";
        var url = "";

        if ($scope.usersForm.id === -1) {
            //Id is absent in form data, it is create new customer operation
            method = "POST";
            url = '/user/add';
        } else {
            //Id is present in form data, it is edit customer operation
            method = "PUT";
            url = '/user/edit/' + $scope.usersForm.id;
        }

        $http({
            method: method,
            url: url,
            data: angular.toJson($scope.usersForm),
            headers: {
                'Content-Type': 'application/json'
            }
        }).then(function successCallback(response) {
            _refreshUserData();
            _clearUserData();
            _getNotificationMessage('success', 'Utilisateur enregistré avec succès !');
        }, function errorCallback(response) {
            _getNotificationMessage('error', 'Echèc de l\'enregistrement. Veuillez reesayer ultérieurement !!!');
        });
    };

    $scope.loadUser = function (user) {
        $scope.usersForm.id = user.id;
        $scope.usersForm.nom = user.nom;
        $scope.usersForm.prenoms = user.prenoms;
        $scope.usersForm.email = user.email;
        $scope.usersForm.contact = user.contact;
        $scope.usersForm.activated = user.activated;
        $scope.usersForm.validated = user.validated;
    };

    function _refreshUserData() {
        $http({
            method: 'GET',
            url: '/user/all'
        }).then(function successCallback(response) {
            $scope.users = response.data;
        }, function errorCallback(response) {
            console.log('Erreur de chargement des utilisateurs ... ' + response);
        });
    }

    function _clearUserData() {
        $scope.usersForm.id = -1;
        $scope.usersForm.nom = "";
        $scope.usersForm.prenoms = "";
        $scope.usersForm.email = "";
        $scope.usersForm.contact = "";
        $scope.usersForm.activated = false;
        $scope.usersForm.validated = false;
    }

    function _initVariables() {
        _clearUserData();
    }

    $scope.currentUrl = "defaul_page";
    $scope.hideAndShow = function (toBeShown, newLink) {
        _initVariables();
        chooseLoading(toBeShown);
        if ($scope.currentUrl !== toBeShown) {
            $('#' + toBeShown).show();
            $('#' + $scope.currentUrl).hide();
            _loadCommonContent(newLink);
            $scope.currentUrl = toBeShown;
        }
    };

    $scope.goToPage = function (url) {
        window.location.href = url;
    };

    function _loadCommonContent(url) {
        $location.path(url);
    }

    if ($scope.currentUrl === "defaul_page") {
        _refreshUserData();
    }

    function chooseLoading(entree) {

        switch (entree) {
            case 'defaul_page' :
                _refreshUserData();
                break;
        }
    }

    function _getNotificationMessage(entree, libelle) {
        switch (entree) {
            case 'info':
                toastr.info('' + libelle);
                break;
            case 'warning':
                toastr.warning('' + libelle);
                break;
            case 'success':
                toastr.success('' + libelle);
                break;
            case 'error':
                toastr.error('' + libelle);
                break;
        }
    }
});