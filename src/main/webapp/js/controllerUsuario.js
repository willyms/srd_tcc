angular.module('mySRD').controller('ctrlUsuario',function($scope){
	$scope.count = 0;
	$scope.selecionaSetor = true;
	$scope.soma = function() {
		$scope.count = $scope.count + 1;
		$scope.selecionaSetor = !$scope.selecionaSetor;
		console.log($scope.count);
	}
	
	$scope.perfil = function(params) {
		console.log(params);		
	}
});